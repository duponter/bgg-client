package be.edu.bggclient.internal.collection;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import be.edu.bggclient.BggClientError;
import be.edu.bggclient.BggClientException;
import be.edu.bggclient.collection.Collection;
import be.edu.bggclient.collection.CollectionEndpoint;
import be.edu.bggclient.collection.CollectionRequest;
import be.edu.bggclient.internal.BggApi;
import com.pgssoft.httpclient.HttpClientMock;
import com.pgssoft.httpclient.HttpClientMockBuilder;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class CollectionBggApiEndpointTest {

    @Test
    void returnsResultsWhenXmlCouldBeFetchedAndParsed() throws BggClientException {
        CollectionRequest request = new CollectionRequest("dummy").owned().withStats();

        HttpClientMock httpClient = new HttpClientMock();
        initHttpClientMockBuilder(httpClient, request)
                .doReturnXML(this.readXmlResponse());

        CollectionEndpoint endpoint = new CollectionBggApiEndpoint(httpClient);
        Collection result = endpoint.send(request);
        assertThat(result.getItemCount()).isEqualTo(122);
        assertThat(result.getItems()).hasSize(124);
    }

    @Test
    void retriesUpTo5TimesWhenRequestIsAccepted() throws BggClientException {
        CollectionRequest request = new CollectionRequest("dummy").owned().withStats();

        HttpClientMock httpClient = new HttpClientMock();
        initHttpClientMockBuilder(httpClient, request)
                .doReturnStatus(202)
                .doReturnStatus(202)
                .doReturnStatus(202)
                .doReturnStatus(202)
                .doReturnStatus(202)
                .doReturnXML(this.readXmlResponse());

        CollectionEndpoint endpoint = new CollectionBggApiEndpoint(httpClient);
        Collection result = endpoint.send(request);
        assertThat(result.getItemCount()).isEqualTo(122);
        assertThat(result.getItems()).hasSize(124);
    }

    @Test
    void throwsExceptionWhenNoOkResponse() {
        CollectionRequest request = new CollectionRequest("dummy").owned().withStats();

        HttpClientMock httpClient = new HttpClientMock();
        initHttpClientMockBuilder(httpClient, request).doReturnStatus(429);

        CollectionEndpoint endpoint = new CollectionBggApiEndpoint(httpClient);
        assertThatExceptionOfType(BggClientException.class)
                .isThrownBy(() -> endpoint.send(request))
                .withMessageStartingWith(BggClientError.NO_BGG_RESPONSE.name());
    }

    @Test
    void throwsExceptionWhenResponseContainsInvalidXml() {
        CollectionRequest request = new CollectionRequest("dummy").owned().withStats();

        HttpClientMock httpClient = new HttpClientMock();
        initHttpClientMockBuilder(httpClient, request)
                .doReturnXML("</invalid>");

        CollectionEndpoint endpoint = new CollectionBggApiEndpoint(httpClient);
        assertThatExceptionOfType(BggClientException.class)
                .isThrownBy(() -> endpoint.send(request))
                .withMessageStartingWith(BggClientError.INVALID_XML.name());
    }

    @Test
    void returnsIncompleteCollectionWhenResponseContainsUnsupportedXml() throws BggClientException {
        CollectionRequest request = new CollectionRequest("dummy").owned().withStats();

        HttpClientMock httpClient = new HttpClientMock();
        initHttpClientMockBuilder(httpClient, request)
                .doReturnXML("<unsupported></unsupported>");

        CollectionEndpoint endpoint = new CollectionBggApiEndpoint(httpClient);
        Collection result = endpoint.send(request);
        assertThat(result.getItemCount()).isZero();
        assertThat(result.getItems()).isEmpty();
    }

    private HttpClientMockBuilder initHttpClientMockBuilder(HttpClientMock httpClient, CollectionRequest request) {
        return httpClient.onGet(BggApi.V2.create("collection").create(request.buildQueryString()).toString());
    }

    private String readXmlResponse() {
        try (InputStream inputStream = CollectionBggApiEndpointTest.class.getResourceAsStream("collection.xml")) {
            assertThat(inputStream).isNotNull();
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException ioe) {
            throw new IllegalStateException(ioe);
        }
    }
}
