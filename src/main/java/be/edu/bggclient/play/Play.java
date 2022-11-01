package be.edu.bggclient.play;

import java.time.LocalDate;
import java.util.List;

public final class Play {
    private final String id;
    private final LocalDate date;
    private final int quantity;
    private final int length;
    private final boolean incomplete;
    private final boolean noWinStats;
    private final String location;
    private final PlayItem item;
    private final String comments;
    private final List<Player> players;

    private Play(Builder builder) {
        this.id = builder.id;
        this.date = builder.date;
        this.quantity = builder.quantity;
        this.length = builder.length;
        this.incomplete = builder.incomplete;
        this.noWinStats = builder.noWinStats;
        this.location = builder.location;
        this.item = builder.item;
        this.comments = builder.comments;
        this.players = List.copyOf(builder.players);
    }

    public String getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getLength() {
        return length;
    }

    public boolean isIncomplete() {
        return incomplete;
    }

    public boolean isNoWinStats() {
        return noWinStats;
    }

    public String getLocation() {
        return location;
    }

    public PlayItem getItem() {
        return item;
    }

    public String getComments() {
        return comments;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public static final class Builder {
        private String id;
        private LocalDate date;
        private int quantity;
        private int length;
        private boolean incomplete;
        private boolean noWinStats;
        private String location;
        private PlayItem item;
        private String comments;
        private List<Player> players;

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withDate(LocalDate date) {
            this.date = date;
            return this;
        }

        public Builder withQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder withLength(int length) {
            this.length = length;
            return this;
        }

        public Builder withIncomplete(boolean incomplete) {
            this.incomplete = incomplete;
            return this;
        }

        public Builder withNoWinStats(boolean noWinStats) {
            this.noWinStats = noWinStats;
            return this;
        }

        public Builder withLocation(String location) {
            this.location = location;
            return this;
        }

        public Builder withItem(PlayItem item) {
            this.item = item;
            return this;
        }

        public Builder withComments(String comments) {
            this.comments = comments;
            return this;
        }

        public Builder withPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Play build() {
            return new Play(this);
        }
    }
}
