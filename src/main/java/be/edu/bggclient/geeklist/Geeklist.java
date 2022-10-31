package be.edu.bggclient.geeklist;

import java.time.LocalDateTime;
import java.util.List;

public class Geeklist {
    private final LocalDateTime postDate;
    private final LocalDateTime editDate;
    private final int thumbs;
    private final int itemCount;
    private final String username;
    private final String title;
    private final String description;
    private final List<GeeklistItem> items;

    private Geeklist(Builder builder) {
        this.postDate = builder.postDate;
        this.editDate = builder.editDate;
        this.thumbs = builder.thumbs;
        this.itemCount = builder.itemCount;
        this.username = builder.username;
        this.title = builder.title;
        this.description = builder.description;
        this.items = List.copyOf(builder.items);
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

    public int getItemCount() {
        return itemCount;
    }

    public String getUsername() {
        return username;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<GeeklistItem> getItems() {
        return items;
    }

    public static final class Builder {
        private LocalDateTime postDate;
        private LocalDateTime editDate;
        private int thumbs;
        private int itemCount;
        private String username;
        private String title;
        private String description;
        private List<GeeklistItem> items;

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

        public Builder withItemCount(int itemCount) {
            this.itemCount = itemCount;
            return this;
        }

        public Builder withUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withItems(List<GeeklistItem> items) {
            this.items = items;
            return this;
        }

        public Geeklist build() {
            return new Geeklist(this);
        }
    }
}
