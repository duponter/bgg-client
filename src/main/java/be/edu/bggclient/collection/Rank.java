package be.edu.bggclient.collection;

public final class Rank {
    private final String type;
    private final String id;
    private final String name;
    private final String friendlyName;
    private final int value;
    private final double bayesianAverage;

    private Rank(Builder builder) {
        this.type = builder.type;
        this.id = builder.id;
        this.name = builder.name;
        this.friendlyName = builder.friendlyName;
        this.value = builder.value;
        this.bayesianAverage = builder.bayesianAverage;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFriendlyName() {
        return friendlyName;
    }

    public int getValue() {
        return value;
    }

    public double getBayesianAverage() {
        return bayesianAverage;
    }

    public static final class Builder {
        private String type;
        private String id;
        private String name;
        private String friendlyName;
        private int value;
        private double bayesianAverage;

        public Builder withType(String type) {
            this.type = type;
            return this;
        }

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withFriendlyName(String friendlyName) {
            this.friendlyName = friendlyName;
            return this;
        }

        public Builder withValue(int value) {
            this.value = value;
            return this;
        }

        public Builder withBayesianAverage(double bayesianAverage) {
            this.bayesianAverage = bayesianAverage;
            return this;
        }

        public Rank build() {
            return new Rank(this);
        }
    }
}
