package be.edu.bggclient;

import be.edu.bggclient.collection.Collection;
import be.edu.bggclient.collection.CollectionRequest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BggClientIT {
    @Test
    void collectionRequestReturnsResults() throws BggClientException {
        CollectionRequest request = new CollectionRequest("bartie").owned().withStats();
        Collection result = new BggClient().toCollectionEndpoint().send(request);
        assertThat(result.getItemCount()).isPositive();
        assertThat(result.getItems()).hasSize(result.getItemCount());
    }
}
