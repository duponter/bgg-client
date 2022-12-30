package be.edu.bggclient.internal.collection;

import be.edu.bggclient.collection.Collection;
import be.edu.bggclient.collection.CollectionEndpoint;
import be.edu.bggclient.collection.CollectionItem;
import be.edu.bggclient.collection.CollectionRequest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CollectionBggApiEndpointIT {
    @Test
    void returnsResults() {
        CollectionRequest request = new CollectionRequest("duponter").preordered().withStats();
        CollectionEndpoint endpoint = new CollectionBggApiEndpoint();
        Collection result = endpoint.send(request);
        assertThat(result.getItemCount()).isEqualTo(2);
        assertThat(result.getItems()).extracting(CollectionItem::getName).containsExactlyInAnyOrder("Unconscious Mind", "Dune: Imperium – Immortality");
    }
}
