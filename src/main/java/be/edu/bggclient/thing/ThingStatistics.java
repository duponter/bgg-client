package be.edu.bggclient.thing;

import java.util.List;

public final class ThingStatistics {
    private final int ratingCount;
    private final double average;
    private final double bayesianAverage;
    private final List<Rank> ranks;
    private final double standardDeviation;
    private final double median;
    private final int ownCount;
    private final int tradeCount;
    private final int wantCount;
    private final int wishCount;
    private final int commentCount;
    private final int weightCount;
    private final double averageWeight;

    private ThingStatistics(Builder builder) {
        this.ratingCount = builder.ratingCount;
        this.average = builder.average;
        this.bayesianAverage = builder.bayesianAverage;
        this.ranks = List.copyOf(builder.ranks);
        this.standardDeviation = builder.standardDeviation;
        this.median = builder.median;
        this.ownCount = builder.ownCount;
        this.tradeCount = builder.tradeCount;
        this.wantCount = builder.wantCount;
        this.wishCount = builder.wishCount;
        this.commentCount = builder.commentCount;
        this.weightCount = builder.weightCount;
        this.averageWeight = builder.averageWeight;
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

    public List<Rank> getRanks() {
        return ranks;
    }

    public double getStandardDeviation() {
        return standardDeviation;
    }

    public double getMedian() {
        return median;
    }

    public int getOwnCount() {
        return ownCount;
    }

    public int getTradeCount() {
        return tradeCount;
    }

    public int getWantCount() {
        return wantCount;
    }

    public int getWishCount() {
        return wishCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public int getWeightCount() {
        return weightCount;
    }

    public double getAverageWeight() {
        return averageWeight;
    }

    public static final class Builder {
        private int ratingCount;
        private double average;
        private double bayesianAverage;
        private List<Rank> ranks;
        private double standardDeviation;
        private double median;
        private int ownCount;
        private int tradeCount;
        private int wantCount;
        private int wishCount;
        private int commentCount;
        private int weightCount;
        private double averageWeight;

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

        public Builder withRanks(List<Rank> ranks) {
            this.ranks = ranks;
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

        public Builder withOwnCount(int ownCount) {
            this.ownCount = ownCount;
            return this;
        }

        public Builder withTradeCount(int tradeCount) {
            this.tradeCount = tradeCount;
            return this;
        }

        public Builder withWantCount(int wantCount) {
            this.wantCount = wantCount;
            return this;
        }

        public Builder withWishCount(int wishCount) {
            this.wishCount = wishCount;
            return this;
        }

        public Builder withCommentCount(int commentCount) {
            this.commentCount = commentCount;
            return this;
        }

        public Builder withWeightCount(int weightCount) {
            this.weightCount = weightCount;
            return this;
        }

        public Builder withAverageWeight(double averageWeight) {
            this.averageWeight = averageWeight;
            return this;
        }

        public ThingStatistics build() {
            return new ThingStatistics(this);
        }
    }
}
