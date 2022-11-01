package be.edu.bggclient.play;

import java.util.List;

public final class PlayItem {
    private final String name;
    private final String objectType;
    private final String objectId;
    private final List<String> subTypes;

    private PlayItem(Builder builder) {
        this.name = builder.name;
        this.objectType = builder.objectType;
        this.objectId = builder.objectId;
        this.subTypes = List.copyOf(builder.subTypes);
    }

    public String getName() {
        return name;
    }

    public String getObjectType() {
        return objectType;
    }

    public String getObjectId() {
        return objectId;
    }

    public List<String> getSubTypes() {
        return subTypes;
    }

    public static final class Builder {
        private String name;
        private String objectType;
        private String objectId;
        private List<String> subTypes;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withObjectType(String objectType) {
            this.objectType = objectType;
            return this;
        }

        public Builder withObjectId(String objectId) {
            this.objectId = objectId;
            return this;
        }

        public Builder withSubTypes(List<String> subTypes) {
            this.subTypes = subTypes;
            return this;
        }

        public PlayItem build() {
            return new PlayItem(this);
        }
    }
}
