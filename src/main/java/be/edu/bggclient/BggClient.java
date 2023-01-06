package be.edu.bggclient;

import java.util.ServiceLoader;
import java.util.function.Supplier;

import be.edu.bggclient.collection.CollectionEndpoint;

public final class BggClient {
    private final Supplier<CollectionEndpoint> collectionEndpoint = loadService(CollectionEndpoint.class);

    public CollectionEndpoint toCollectionEndpoint() {
        return collectionEndpoint.get();
    }

    private static <S> Supplier<S> loadService(Class<S> serviceClass) {
        return lazily(() -> ServiceLoader.load(serviceClass).findFirst().orElseThrow());
    }

    private static <T> Supplier<T> lazily(Supplier<T> supplier) {
        return new Supplier<>() {
            T value; // = null

            @Override
            public T get() {
                if (value == null) {
                    value = supplier.get();
                }
                return value;
            }
        };
    }
}
