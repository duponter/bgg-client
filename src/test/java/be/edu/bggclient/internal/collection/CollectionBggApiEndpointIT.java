package be.edu.bggclient.internal.collection;

import java.net.http.HttpClient;

import be.edu.bggclient.BggClientException;
import be.edu.bggclient.collection.Collection;
import be.edu.bggclient.collection.CollectionEndpoint;
import be.edu.bggclient.collection.CollectionRequest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CollectionBggApiEndpointIT {
    @Test
    void returnsResults() throws BggClientException {
        CollectionRequest request = new CollectionRequest("duponter").preordered().withStats();
        CollectionEndpoint endpoint = new CollectionBggApiEndpoint(HttpClient.newHttpClient());
        Collection result = endpoint.send(request);
        assertThat(result.getItemCount()).isPositive();
        assertThat(result.getItems()).hasSize(result.getItemCount());
    }
}
