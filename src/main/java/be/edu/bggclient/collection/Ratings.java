package be.edu.bggclient.collection;

import java.util.List;

public final class Ratings {
    private final String userRating;
    private final int ratingCount;
    private final double bayesianAverage;
    private final double standardDeviation;
    private final List<Rank> ranks;

    private Ratings(Builder builder) {
        this.userRating = builder.userRating;
        this.ratingCount = builder.ratingCount;
        this.bayesianAverage = builder.bayesianAverage;
        this.standardDeviation = builder.standardDeviation;
        this.ranks = List.copyOf(builder.ranks);
    }

    public String getUserRating() {
        return userRating;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public double getBayesianAverage() {
        return bayesianAverage;
    }

    public double getStandardDeviation() {
        return standardDeviation;
    }

    public List<Rank> getRanks() {
        return ranks;
    }

    public static final class Builder {
        private String userRating;
        private int ratingCount;
        private double bayesianAverage;
        private double standardDeviation;
        private List<Rank> ranks;

        public Builder withUserRating(String userRating) {
            this.userRating = userRating;
            return this;
        }

        public Builder withRatingCount(int ratingCount) {
            this.ratingCount = ratingCount;
            return this;
        }

        public Builder withBayesianAverage(double bayesianAverage) {
            this.bayesianAverage = bayesianAverage;
            return this;
        }

        public Builder withStandardDeviation(double standardDeviation) {
            this.standardDeviation = standardDeviation;
            return this;
        }

        public Builder withRanks(List<Rank> ranks) {
            this.ranks = ranks;
            return this;
        }

        public Ratings build() {
            return new Ratings(this);
        }
    }
}
