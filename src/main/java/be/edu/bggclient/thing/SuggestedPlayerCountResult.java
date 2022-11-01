package be.edu.bggclient.thing;

public class SuggestedPlayerCountResult implements PollResult {
    private final String playerCount;
    private final String value;
    private final int voteCount;

    private SuggestedPlayerCountResult(Builder builder) {
        this.playerCount = builder.playerCount;
        this.value = builder.value;
        this.voteCount = builder.voteCount;
    }

    public String getPlayerCount() {
        return playerCount;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public int getVoteCount() {
        return voteCount;
    }

    public static final class Builder {
        private String playerCount;
        private String value;
        private int voteCount;

        public Builder withPlayerCount(String playerCount) {
            this.playerCount = playerCount;
            return this;
        }

        public Builder withValue(String value) {
            this.value = value;
            return this;
        }

        public Builder withVoteCount(int voteCount) {
            this.voteCount = voteCount;
            return this;
        }

        public SuggestedPlayerCountResult build() {
            return new SuggestedPlayerCountResult(this);
        }
    }
}
