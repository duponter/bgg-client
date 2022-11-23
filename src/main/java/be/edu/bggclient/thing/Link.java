package be.edu.bggclient.thing;

public final class Link {
    private final String type;
    private final String id;
    private final String value;

    private Link(Builder builder) {
        this.type = builder.type;
        this.id = builder.id;
        this.value = builder.value;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public static final class Builder {
        private String type;
        private String id;
        private String value;

        public Builder withType(String type) {
            this.type = type;
            return this;
        }

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withValue(String value) {
            this.value = value;
            return this;
        }

        public Link build() {
            return new Link(this);
        }
    }
}
