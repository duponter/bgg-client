package be.edu.bggclient.collection;

@FunctionalInterface
public interface CollectionEndpoint {
    Collection send(CollectionRequest request);
}
