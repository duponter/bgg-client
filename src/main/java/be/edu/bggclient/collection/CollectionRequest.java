package be.edu.bggclient.collection;

import be.edu.bggclient.BggRequest;

public final class CollectionRequest extends BggRequest {
    public CollectionRequest(String username) {
        this.addOption("username", username);
        this.addOption("subtype", "boardgame");
    }

    public CollectionRequest owned() {
        this.enableOption("own");
        return this;
    }

    public CollectionRequest preordered() {
        this.enableOption("preordered");
        return this;
    }

    public CollectionRequest wantToPlay() {
        this.enableOption("wanttoplay");
        return this;
    }

    public CollectionRequest played() {
        this.enableOption("played");
        return this;
    }

    public CollectionRequest rated() {
        this.enableOption("rated");
        return this;
    }

    public CollectionRequest abbreviatedResults() {
        this.enableOption("brief");
        return this;
    }

    public CollectionRequest showPrivateInfo() {
        this.enableOption("showprivate");
        return this;
    }

    public CollectionRequest withStats() {
        this.enableOption("stats");
        return this;
    }

    public CollectionRequest withoutExpansions() {
        this.addOption("excludesubtype", "boardgameexpansion");
        return this;
    }

    public CollectionRequest minimallyRated(int minrating) {
        if (minrating < 1 || minrating > 10) {
            throw new IllegalArgumentException(String.format("The value %d is not in the specified inclusive range of %d to %d", minrating, 1, 10));
        }
        this.addOption("minrating", minrating);
        return this;
    }
}
