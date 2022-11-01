package be.edu.bggclient.play;

public final class Player {
    private final String username;
    private final String userId;
    private final String name;
    private final int startPosition;
    private final String color;
    private final String score;
    private final boolean newPlayer;
    private final String rating;
    private final boolean win;

    private Player(Builder builder) {
        this.username = builder.username;
        this.userId = builder.userId;
        this.name = builder.name;
        this.startPosition = builder.startPosition;
        this.color = builder.color;
        this.score = builder.score;
        this.newPlayer = builder.newPlayer;
        this.rating = builder.rating;
        this.win = builder.win;
    }

    public String getUsername() {
        return username;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public int getStartPosition() {
        return startPosition;
    }

    public String getColor() {
        return color;
    }

    public String getScore() {
        return score;
    }

    public boolean isNewPlayer() {
        return newPlayer;
    }

    public String getRating() {
        return rating;
    }

    public boolean isWin() {
        return win;
    }

    public static final class Builder {
        private String username;
        private String userId;
        private String name;
        private int startPosition;
        private String color;
        private String score;
        private boolean newPlayer;
        private String rating;
        private boolean win;

        public Builder withUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withStartPosition(int startPosition) {
            this.startPosition = startPosition;
            return this;
        }

        public Builder withColor(String color) {
            this.color = color;
            return this;
        }

        public Builder withScore(String score) {
            this.score = score;
            return this;
        }

        public Builder withNewPlayer(boolean newPlayer) {
            this.newPlayer = newPlayer;
            return this;
        }

        public Builder withRating(String rating) {
            this.rating = rating;
            return this;
        }

        public Builder withWin(boolean win) {
            this.win = win;
            return this;
        }

        public Player build() {
            return new Player(this);
        }
    }
}
