package be.edu.bggclient.thing;

public class SuggestedPlayerAgeResult implements PollResult {
    private final String value;
    private final int voteCount;

    private SuggestedPlayerAgeResult(Builder builder) {
        this.value = builder.value;
        this.voteCount = builder.voteCount;
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
        private String value;
        private int voteCount;

        public Builder withValue(String value) {
            this.value = value;
            return this;
        }

        public Builder withVoteCount(int voteCount) {
            this.voteCount = voteCount;
            return this;
        }

        public SuggestedPlayerAgeResult build() {
            return new SuggestedPlayerAgeResult(this);
        }
    }
}
