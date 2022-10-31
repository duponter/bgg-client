package be.edu.bggclient.collection;

import java.time.LocalDateTime;

public final class Status {
    private final boolean own;
    private final boolean previouslyOwned;
    private final boolean forTrade;
    private final boolean want;
    private final boolean wantToPlay;
    private final boolean wantToBuy;
    private final boolean wishlist;
    private final int wishlistPriority;
    private final boolean preordered;
    private final LocalDateTime lastModified;

    private Status(Builder builder) {
        this.own = builder.own;
        this.previouslyOwned = builder.previouslyOwned;
        this.forTrade = builder.forTrade;
        this.want = builder.want;
        this.wantToPlay = builder.wantToPlay;
        this.wantToBuy = builder.wantToBuy;
        this.wishlist = builder.wishlist;
        this.wishlistPriority = builder.wishlistPriority;
        this.preordered = builder.preordered;
        this.lastModified = builder.lastModified;
    }

    public boolean isOwn() {
        return own;
    }

    public boolean isPreviouslyOwned() {
        return previouslyOwned;
    }

    public boolean isForTrade() {
        return forTrade;
    }

    public boolean isWant() {
        return want;
    }

    public boolean isWantToPlay() {
        return wantToPlay;
    }

    public boolean isWantToBuy() {
        return wantToBuy;
    }

    public boolean isWishlist() {
        return wishlist;
    }

    public int getWishlistPriority() {
        return wishlistPriority;
    }

    public boolean isPreordered() {
        return preordered;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public static final class Builder {
        private boolean own;
        private boolean previouslyOwned;
        private boolean forTrade;
        private boolean want;
        private boolean wantToPlay;
        private boolean wantToBuy;
        private boolean wishlist;
        private int wishlistPriority;
        private boolean preordered;
        private LocalDateTime lastModified;

        public Builder withOwn(boolean own) {
            this.own = own;
            return this;
        }

        public Builder withPreviouslyOwned(boolean previouslyOwned) {
            this.previouslyOwned = previouslyOwned;
            return this;
        }

        public Builder withForTrade(boolean forTrade) {
            this.forTrade = forTrade;
            return this;
        }

        public Builder withWant(boolean want) {
            this.want = want;
            return this;
        }

        public Builder withWantToPlay(boolean wantToPlay) {
            this.wantToPlay = wantToPlay;
            return this;
        }

        public Builder withWantToBuy(boolean wantToBuy) {
            this.wantToBuy = wantToBuy;
            return this;
        }

        public Builder withWishlist(boolean wishlist) {
            this.wishlist = wishlist;
            return this;
        }

        public Builder withWishlistPriority(int wishlistPriority) {
            this.wishlistPriority = wishlistPriority;
            return this;
        }

        public Builder withPreordered(boolean preordered) {
            this.preordered = preordered;
            return this;
        }

        public Builder withLastModified(LocalDateTime lastModified) {
            this.lastModified = lastModified;
            return this;
        }

        public Status build() {
            return new Status(this);
        }
    }
}
