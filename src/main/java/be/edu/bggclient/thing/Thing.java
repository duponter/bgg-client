package be.edu.bggclient.thing;

import java.util.List;

public final class Thing {
    private final String type;
    private final String id;
    private final String imageUrl;
    private final String thumbnailUrl;
    private final List<Name> names;
    private final String description;
    private final int yearPublished;
    private final String minPlayers;
    private final String maxPlayers;
    private final Poll<SuggestedPlayerCountResult> suggestedNumberOfPlayers;
    private final String playingTime;
    private final String minPlayTime;
    private final String maxPlayTime;
    private final String minAge;
    private final Poll<SuggestedPlayerAgeResult> suggestedPlayerAge;
    private final Poll<LanguageDependenceResult> languageDependence;
    private final List<Link> links;
    private final Statistics statistics;

    private Thing(Builder builder) {
        this.type = builder.type;
        this.id = builder.id;
        this.imageUrl = builder.imageUrl;
        this.thumbnailUrl = builder.thumbnailUrl;
        this.names = List.copyOf(builder.names);
        this.description = builder.description;
        this.yearPublished = builder.yearPublished;
        this.minPlayers = builder.minPlayers;
        this.maxPlayers = builder.maxPlayers;
        this.suggestedNumberOfPlayers = builder.suggestedNumberOfPlayers;
        this.playingTime = builder.playingTime;
        this.minPlayTime = builder.minPlayTime;
        this.maxPlayTime = builder.maxPlayTime;
        this.minAge = builder.minAge;
        this.suggestedPlayerAge = builder.suggestedPlayerAge;
        this.languageDependence = builder.languageDependence;
        this.links = List.copyOf(builder.links);
        this.statistics = builder.statistics;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public List<Name> getNames() {
        return names;
    }

    public String getDescription() {
        return description;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public String getMinPlayers() {
        return minPlayers;
    }

    public String getMaxPlayers() {
        return maxPlayers;
    }

    public Poll<SuggestedPlayerCountResult> getSuggestedNumberOfPlayers() {
        return suggestedNumberOfPlayers;
    }

    public String getPlayingTime() {
        return playingTime;
    }

    public String getMinPlayTime() {
        return minPlayTime;
    }

    public String getMaxPlayTime() {
        return maxPlayTime;
    }

    public String getMinAge() {
        return minAge;
    }

    public Poll<SuggestedPlayerAgeResult> getSuggestedPlayerAge() {
        return suggestedPlayerAge;
    }

    public Poll<LanguageDependenceResult> getLanguageDependence() {
        return languageDependence;
    }

    public List<Link> getLinks() {
        return links;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public static final class Builder {
        private String type;
        private String id;
        private String imageUrl;
        private String thumbnailUrl;
        private List<Name> names;
        private String description;
        private int yearPublished;
        private String minPlayers;
        private String maxPlayers;
        private Poll<SuggestedPlayerCountResult> suggestedNumberOfPlayers;
        private String playingTime;
        private String minPlayTime;
        private String maxPlayTime;
        private String minAge;
        private Poll<SuggestedPlayerAgeResult> suggestedPlayerAge;
        private Poll<LanguageDependenceResult> languageDependence;
        private List<Link> links;
        private Statistics statistics;

        public Builder withType(String type) {
            this.type = type;
            return this;
        }

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder withThumbnailUrl(String thumbnailUrl) {
            this.thumbnailUrl = thumbnailUrl;
            return this;
        }

        public Builder withNames(List<Name> names) {
            this.names = names;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withYearPublished(int yearPublished) {
            this.yearPublished = yearPublished;
            return this;
        }

        public Builder withMinPlayers(String minPlayers) {
            this.minPlayers = minPlayers;
            return this;
        }

        public Builder withMaxPlayers(String maxPlayers) {
            this.maxPlayers = maxPlayers;
            return this;
        }

        public Builder withSuggestedNumberOfPlayers(Poll<SuggestedPlayerCountResult> suggestedNumberOfPlayers) {
            this.suggestedNumberOfPlayers = suggestedNumberOfPlayers;
            return this;
        }

        public Builder withPlayingTime(String playingTime) {
            this.playingTime = playingTime;
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

        public Builder withMinAge(String minAge) {
            this.minAge = minAge;
            return this;
        }

        public Builder withSuggestedPlayerAge(Poll<SuggestedPlayerAgeResult> suggestedPlayerAge) {
            this.suggestedPlayerAge = suggestedPlayerAge;
            return this;
        }

        public Builder withLanguageDependence(Poll<LanguageDependenceResult> languageDependence) {
            this.languageDependence = languageDependence;
            return this;
        }

        public Builder withLinks(List<Link> links) {
            this.links = links;
            return this;
        }

        public Builder withStatistics(Statistics statistics) {
            this.statistics = statistics;
            return this;
        }

        public Thing build() {
            return new Thing(this);
        }
    }
}
