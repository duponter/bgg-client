package be.edu.bggclient.thing;

public class LanguageDependenceResult implements PollResult {
    private final String level;
    private final String value;
    private final int voteCount;

    private LanguageDependenceResult(Builder builder) {
        this.level = builder.level;
        this.value = builder.value;
        this.voteCount = builder.voteCount;
    }

    public String getLevel() {
        return level;
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
        private String level;
        private String value;
        private int voteCount;

        public Builder withLevel(String level) {
            this.level = level;
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

        public LanguageDependenceResult build() {
            return new LanguageDependenceResult(this);
        }
    }
}
