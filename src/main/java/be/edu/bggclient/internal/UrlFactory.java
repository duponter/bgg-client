package be.edu.bggclient.internal;

import java.net.URI;
import java.util.function.Function;

@FunctionalInterface
public interface UrlFactory extends Function<String, URI> {
    default URI create(String queryParameters) {
        return this.apply(queryParameters);
    }
}
