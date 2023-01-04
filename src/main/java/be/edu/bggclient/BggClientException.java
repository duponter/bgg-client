package be.edu.bggclient;

import java.util.Objects;

public class BggClientException extends Exception {
    private final BggClientError error;

    public BggClientException(BggClientError error) {
        super(error.name());
        this.error = Objects.requireNonNull(error);
    }

    public BggClientException(BggClientError error, String message) {
        super(error.name() + ": " + message);
        this.error = Objects.requireNonNull(error);
    }

    public BggClientException(BggClientError error, Throwable cause) {
        super(error.name(), cause);
        this.error = Objects.requireNonNull(error);
    }

    public BggClientException(BggClientError error, String message, Throwable cause) {
        super(error.name() + ": " + message, cause);
        this.error = Objects.requireNonNull(error);
    }

    public BggClientError getError() {
        return error;
    }
}
