package be.edu.bggclient.collection;

import java.util.List;

public final class Rating {
    private final String userRating;
    private final int ratingCount;
    private final double average;
    private final double bayesianAverage;
    private final double standardDeviation;
    private final double median;
    private final List<Rank> ranks;

    private Rating(Builder builder) {
        this.userRating = builder.userRating;
        this.ratingCount = builder.ratingCount;
        this.average = builder.average;
        this.bayesianAverage = builder.bayesianAverage;
        this.standardDeviation = builder.standardDeviation;
        this.median = builder.median;
        this.ranks = List.copyOf(builder.ranks);
    }

    public String getUserRating() {
        return userRating;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public double getAverage() {
        return average;
    }

    public double getBayesianAverage() {
        return bayesianAverage;
    }

    public double getStandardDeviation() {
        return standardDeviation;
    }

    public double getMedian() {
        return median;
    }

    public List<Rank> getRanks() {
        return ranks;
    }

    public static final class Builder {
        private String userRating;
        private int ratingCount;
        private double average;
        private double bayesianAverage;
        private double standardDeviation;
        private double median;
        private List<Rank> ranks;

        public Builder withUserRating(String userRating) {
            this.userRating = userRating;
            return this;
        }

        public Builder withRatingCount(int ratingCount) {
            this.ratingCount = ratingCount;
            return this;
        }

        public Builder withAverage(double average) {
            this.average = average;
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

        public Builder withMedian(double median) {
            this.median = median;
            return this;
        }

        public Builder withRanks(List<Rank> ranks) {
            this.ranks = ranks;
            return this;
        }

        public Rating build() {
            return new Rating(this);
        }
    }
}
