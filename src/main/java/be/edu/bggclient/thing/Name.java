package be.edu.bggclient.thing;

public final class Name {
    private final String type;
    private final int sortIndex;
    private final String value;

    private Name(Builder builder) {
        this.type = builder.type;
        this.sortIndex = builder.sortIndex;
        this.value = builder.value;
    }

    public String getType() {
        return type;
    }

    public int getSortIndex() {
        return sortIndex;
    }

    public String getValue() {
        return value;
    }

    public static final class Builder {
        private String type;
        private int sortIndex;
        private String value;

        public Builder withType(String type) {
            this.type = type;
            return this;
        }

        public Builder withSortIndex(int sortIndex) {
            this.sortIndex = sortIndex;
            return this;
        }

        public Builder withValue(String value) {
            this.value = value;
            return this;
        }

        public Name build() {
            return new Name(this);
        }
    }
}
