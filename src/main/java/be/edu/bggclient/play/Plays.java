package be.edu.bggclient.play;

import java.util.List;

public final class Plays {
    private final String username;
    private final String userId;
    private final int total;
    private final int page;
    private final List<Play> plays;

    private Plays(Builder builder) {
        this.username = builder.username;
        this.userId = builder.userId;
        this.total = builder.total;
        this.page = builder.page;
        this.plays = List.copyOf(builder.plays);
    }

    public String getUsername() {
        return username;
    }

    public String getUserId() {
        return userId;
    }

    public int getTotal() {
        return total;
    }

    public int getPage() {
        return page;
    }

    public List<Play> getPlays() {
        return plays;
    }

    public static final class Builder {
        private String username;
        private String userId;
        private int total;
        private int page;
        private List<Play> plays;

        public Builder withUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder withTotal(int total) {
            this.total = total;
            return this;
        }

        public Builder withPage(int page) {
            this.page = page;
            return this;
        }

        public Builder withPlays(List<Play> plays) {
            this.plays = plays;
            return this;
        }

        public Plays build() {
            return new Plays(this);
        }
    }
}
