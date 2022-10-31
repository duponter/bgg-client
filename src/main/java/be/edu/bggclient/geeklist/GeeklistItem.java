package be.edu.bggclient.geeklist;

import java.time.LocalDateTime;

public class GeeklistItem {
    private final String id;
    private final String objectType;
    private final String subType;
    private final String objectId;
    private final String objectName;
    private final String username;
    private final LocalDateTime postDate;
    private final LocalDateTime editDate;
    private final int thumbs;
    private final String imageId;
    private final String comments;

    public String getId() {
        return id;
    }

    public String getObjectType() {
        return objectType;
    }

    public String getSubType() {
        return subType;
    }

    public String getObjectId() {
        return objectId;
    }

    public String getObjectName() {
        return objectName;
    }

    public String getUsername() {
        return username;
    }

    public LocalDateTime getPostDate() {
        return postDate;
    }

    public LocalDateTime getEditDate() {
        return editDate;
    }

    public int getThumbs() {
        return thumbs;
    }

    public String getImageId() {
        return imageId;
    }

    public String getComments() {
        return comments;
    }

    public GeeklistItem(Builder builder) {
        this.id = builder.id;
        this.objectType = builder.objectType;
        this.subType = builder.subType;
        this.objectId = builder.objectId;
        this.objectName = builder.objectName;
        this.username = builder.username;
        this.postDate = builder.postDate;
        this.editDate = builder.editDate;
        this.thumbs = builder.thumbs;
        this.imageId = builder.imageId;
        this.comments = builder.comments;
    }

    public static final class Builder {
        private String id;
        private String objectType;
        private String subType;
        private String objectId;
        private String objectName;
        private String username;
        private LocalDateTime postDate;
        private LocalDateTime editDate;
        private int thumbs;
        private String imageId;
        private String comments;

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withObjectType(String objectType) {
            this.objectType = objectType;
            return this;
        }

        public Builder withSubType(String subType) {
            this.subType = subType;
            return this;
        }

        public Builder withObjectId(String objectId) {
            this.objectId = objectId;
            return this;
        }

        public Builder withObjectName(String objectName) {
            this.objectName = objectName;
            return this;
        }

        public Builder withUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder withPostDate(LocalDateTime postDate) {
            this.postDate = postDate;
            return this;
        }

        public Builder withEditDate(LocalDateTime editDate) {
            this.editDate = editDate;
            return this;
        }

        public Builder withThumbs(int thumbs) {
            this.thumbs = thumbs;
            return this;
        }

        public Builder withImageId(String imageId) {
            this.imageId = imageId;
            return this;
        }

        public Builder withComments(String comments) {
            this.comments = comments;
            return this;
        }

        public GeeklistItem build() {
            return new GeeklistItem(this);
        }
    }
}
