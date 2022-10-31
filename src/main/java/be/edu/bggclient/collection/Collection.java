package be.edu.bggclient.collection;

import java.time.LocalDateTime;
import java.util.List;

public final class Collection {
    private final int itemCount;
    private final LocalDateTime publicationDate;
    private final List<CollectionItem> items;

    private Collection(Builder builder) {
        this.itemCount = builder.itemCount;
        this.publicationDate = builder.publicationDate;
        this.items = List.copyOf(builder.items);
    }

    public int getItemCount() {
        return itemCount;
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public List<CollectionItem> getItems() {
        return items;
    }

    public static final class Builder {
        private int itemCount;
        private LocalDateTime publicationDate;
        private List<CollectionItem> items;

        public Builder withItemCount(int itemCount) {
            this.itemCount = itemCount;
            return this;
        }

        public Builder withPublicationDate(LocalDateTime publicationDate) {
            this.publicationDate = publicationDate;
            return this;
        }

        public Builder withItems(List<CollectionItem> items) {
            this.items = items;
            return this;
        }

        public Collection build() {
            return new Collection(this);
        }
    }
}
