package be.edu.bggclient.collection;

import be.edu.bggclient.BggClientException;

@FunctionalInterface
public interface CollectionEndpoint {
    Collection send(CollectionRequest request) throws BggClientException;
}
