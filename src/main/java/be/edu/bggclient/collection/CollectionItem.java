package be.edu.bggclient.collection;

public final class CollectionItem {
    private final String objectType;
    private final String objectId;
    private final String subType;
    private final String collectionId;
    private final String name;
    private final String originalName;
    private final int yearPublished;
    private final String imageUrl;
    private final String thumbnailUrl;
    private final CollectionStatistics statistics;
    private final Status status;
    private final int playCount;
    private final PrivateInfo privateInfo;
    private final String comment;

    private CollectionItem(Builder builder) {
        this.objectType = builder.objectType;
        this.objectId = builder.objectId;
        this.subType = builder.subType;
        this.collectionId = builder.collectionId;
        this.name = builder.name;
        this.originalName = builder.originalName;
        this.yearPublished = builder.yearPublished;
        this.imageUrl = builder.imageUrl;
        this.thumbnailUrl = builder.thumbnailUrl;
        this.statistics = builder.statistics;
        this.status = builder.status;
        this.playCount = builder.playCount;
        this.privateInfo = builder.privateInfo;
        this.comment = builder.comment;
    }

    public String getObjectType() {
        return objectType;
    }

    public String getObjectId() {
        return objectId;
    }

    public String getSubType() {
        return subType;
    }

    public String getCollectionId() {
        return collectionId;
    }

    public String getName() {
        return name;
    }

    public String getOriginalName() {
        return originalName;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public CollectionStatistics getStatistics() {
        return statistics;
    }

    public Status getStatus() {
        return status;
    }

    public int getPlayCount() {
        return playCount;
    }

    public PrivateInfo getPrivateInfo() {
        return privateInfo;
    }

    public String getComment() {
        return comment;
    }

    public static final class Builder {
        private String objectType;
        private String objectId;
        private String subType;
        private String collectionId;
        private String name;
        private String originalName;
        private int yearPublished;
        private String imageUrl;
        private String thumbnailUrl;
        private CollectionStatistics statistics;
        private Status status;
        private int playCount;
        private PrivateInfo privateInfo;
        private String comment;

        public Builder withObjectType(String objectType) {
            this.objectType = objectType;
            return this;
        }

        public Builder withObjectId(String objectId) {
            this.objectId = objectId;
            return this;
        }

        public Builder withSubType(String subType) {
            this.subType = subType;
            return this;
        }

        public Builder withCollectionId(String collectionId) {
            this.collectionId = collectionId;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withOriginalName(String originalName) {
            this.originalName = originalName;
            return this;
        }

        public Builder withYearPublished(int yearPublished) {
            this.yearPublished = yearPublished;
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

        public Builder withStatistics(CollectionStatistics statistics) {
            this.statistics = statistics;
            return this;
        }

        public Builder withStatus(Status status) {
            this.status = status;
            return this;
        }

        public Builder withPlayCount(int playCount) {
            this.playCount = playCount;
            return this;
        }

        public Builder withPrivateInfo(PrivateInfo privateInfo) {
            this.privateInfo = privateInfo;
            return this;
        }

        public Builder withComment(String comment) {
            this.comment = comment;
            return this;
        }

        public CollectionItem build() {
            return new CollectionItem(this);
        }
    }
}
