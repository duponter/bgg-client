package be.edu.bggclient.collection;

public final class CollectionStatistics {
    private final String minPlayerCount;
    private final String maxPlayerCount;
    private final String minPlayTime;
    private final String maxPlayTime;
    private final String playingTime;
    private final int ownCount;
    private final Rating rating;

    private CollectionStatistics(Builder builder) {
        this.minPlayerCount = builder.minPlayerCount;
        this.maxPlayerCount = builder.maxPlayerCount;
        this.minPlayTime = builder.minPlayTime;
        this.maxPlayTime = builder.maxPlayTime;
        this.playingTime = builder.playingTime;
        this.ownCount = builder.ownCount;
        this.rating = builder.rating;
    }

    public String getMinPlayerCount() {
        return minPlayerCount;
    }

    public String getMaxPlayerCount() {
        return maxPlayerCount;
    }

    public String getMinPlayTime() {
        return minPlayTime;
    }

    public String getMaxPlayTime() {
        return maxPlayTime;
    }

    public String getPlayingTime() {
        return playingTime;
    }

    public int getOwnCount() {
        return ownCount;
    }

    public Rating getRating() {
        return rating;
    }

    public static final class Builder {
        private String minPlayerCount;
        private String maxPlayerCount;
        private String minPlayTime;
        private String maxPlayTime;
        private String playingTime;
        private int ownCount;
        private Rating rating;

        public Builder withMinPlayerCount(String minPlayerCount) {
            this.minPlayerCount = minPlayerCount;
            return this;
        }

        public Builder withMaxPlayerCount(String maxPlayerCount) {
            this.maxPlayerCount = maxPlayerCount;
            return this;
        }

        public Builder withMinPlayTime(String minPlayTime) {
            this.minPlayTime = minPlayTime;
            return this;
        }

        public Builder withMaxPlayTime(String maxPlayTime) {
            this.maxPlayTime = maxPlayTime;
            return this;
        }

        public Builder withPlayingTime(String playingTime) {
            this.playingTime = playingTime;
            return this;
        }

        public Builder withOwnCount(int ownCount) {
            this.ownCount = ownCount;
            return this;
        }

        public Builder withRating(Rating rating) {
            this.rating = rating;
            return this;
        }

        public CollectionStatistics build() {
            return new CollectionStatistics(this);
        }
    }
}
