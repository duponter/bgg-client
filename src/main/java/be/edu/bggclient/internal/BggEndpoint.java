package be.edu.bggclient.internal;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import be.edu.bggclient.BggRequest;
import be.edu.bggclient.internal.xml.XmlInput;
import dev.failsafe.CircuitBreaker;
import dev.failsafe.Failsafe;
import dev.failsafe.RetryPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;

public class BggEndpoint {
    private static final Logger LOGGER = LoggerFactory.getLogger(BggEndpoint.class.getName());

    private final UrlFactory urlFactory;
    private final HttpClient httpClient;

    public BggEndpoint(UrlFactory urlFactory) {
        this(urlFactory, HttpClient.newBuilder().build());
    }

    public BggEndpoint(UrlFactory urlFactory, HttpClient httpClient) {
        this.urlFactory = Objects.requireNonNull(urlFactory);
        this.httpClient = Objects.requireNonNull(httpClient);
    }

    public Node asNode(BggRequest request) {
        return new XmlInput().read(this.send(request, HttpResponse.BodyHandlers.ofInputStream()));
    }

    private <T> T send(BggRequest bggRequest, HttpResponse.BodyHandler<T> bodyHandler) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(this.urlFactory.create(bggRequest.buildQueryString()))
                .version(HttpClient.Version.HTTP_2)
                .GET()
                .build();
        LOGGER.info("Sending {} to BGG", request.uri());

        String abbreviatedRequest = request.uri().toString().replaceFirst("\\?.*$", "");
        RetryPolicy<HttpResponse<T>> retryPolicy = RetryPolicy.<HttpResponse<T>>builder()
                .abortIf(response -> response.statusCode() == 200)
                .handleResultIf(response -> response.statusCode() != 200)
                .withBackoff(1L, 100L, ChronoUnit.SECONDS)
                .withMaxRetries(5)
                .onFailedAttempt(e -> LOGGER.trace("Failed to get a response from {}", abbreviatedRequest))
                .onRetry(e -> LOGGER.trace("Retrying to get a response from {}", abbreviatedRequest))
                .onSuccess(e -> LOGGER.debug("Got {} response", e.getResult()))
                .onFailure(e -> LOGGER.warn("Request {} failed with response {}", abbreviatedRequest, e.getResult()))
                .build();

        CircuitBreaker<HttpResponse<T>> breaker = CircuitBreaker.<HttpResponse<T>>builder()
                .handleResultIf(response -> response.statusCode() != 200)
                .withFailureThreshold(3)
                .withDelay(Duration.ofMinutes(1))
                .withSuccessThreshold(1)
                .onOpen(e -> LOGGER.warn("Circuit breaker opened"))
                .onClose(e -> LOGGER.debug("Circuit breaker closed"))
                .onHalfOpen(e -> LOGGER.trace("Circuit breaker half-opened"))
                .build();

        return Failsafe.with(retryPolicy).compose(breaker).get(() -> httpClient.send(request, bodyHandler)).body();
    }
}
