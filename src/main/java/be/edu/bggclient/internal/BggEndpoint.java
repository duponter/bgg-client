package be.edu.bggclient.internal;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import be.edu.bggclient.BggClientError;
import be.edu.bggclient.BggClientException;
import be.edu.bggclient.BggRequest;
import be.edu.bggclient.internal.xml.XmlInput;
import dev.failsafe.Failsafe;
import dev.failsafe.RetryPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class BggEndpoint {
    private static final Logger LOGGER = LoggerFactory.getLogger(BggEndpoint.class.getName());

    private final UrlFactory urlFactory;
    private final HttpClient httpClient;

    public BggEndpoint(UrlFactory urlFactory, HttpClient httpClient) {
        this.urlFactory = Objects.requireNonNull(urlFactory);
        this.httpClient = Objects.requireNonNull(httpClient);
    }

    public Node asNode(BggRequest request) throws BggClientException {
        try {
            return XmlInput.newDocumentBuilder().parse(this.send(request, HttpResponse.BodyHandlers.ofInputStream()));
        } catch (SAXException | IOException e) {
            throw new BggClientException(BggClientError.INVALID_XML, e);
        }
    }

    private <T> T send(BggRequest bggRequest, HttpResponse.BodyHandler<T> bodyHandler) throws BggClientException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(this.urlFactory.create(bggRequest.buildQueryString()))
                .version(HttpClient.Version.HTTP_2)
                .GET()
                .build();
        LOGGER.info("Sending {} to BGG", request.uri());

        String abbreviatedRequest = request.uri().toString().replaceFirst("\\?.*$", "");
        RetryPolicy<HttpResponse<T>> retryPolicy = RetryPolicy.<HttpResponse<T>>builder()
                .handleResultIf(response -> response.statusCode() == 202)
                .withBackoff(1L, 100L, ChronoUnit.SECONDS)
                .withMaxRetries(5)
                .onFailedAttempt(e -> LOGGER.trace("Failed to get a response from {}", abbreviatedRequest))
                .onRetry(e -> LOGGER.trace("Retrying to get a response from {}", abbreviatedRequest))
                .onSuccess(e -> LOGGER.debug("Got {} response", e.getResult()))
                .onFailure(e -> LOGGER.warn("Request {} failed with response {}", abbreviatedRequest, e.getResult()))
                .build();

        HttpResponse<T> response = Failsafe.with(retryPolicy).get(() -> httpClient.send(request, bodyHandler));
        if (response.statusCode() != 200) {
            throw new BggClientException(BggClientError.NO_BGG_RESPONSE);
        }
        return response.body();
    }
}
