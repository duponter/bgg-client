package be.edu.bggclient.thing;

import java.util.List;

public final class Poll<R extends PollResult> {
    private final String name;
    private final String title;
    private final int totalVoteCount;
    private final List<R> results;

    private Poll(Builder<R> builder) {
        this.name = builder.name;
        this.title = builder.title;
        this.totalVoteCount = builder.totalVoteCount;
        this.results = List.copyOf(builder.results);
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public int getTotalVoteCount() {
        return totalVoteCount;
    }

    public List<R> getResults() {
        return results;
    }

    public static final class Builder<R extends PollResult> {
        private String name;
        private String title;
        private int totalVoteCount;
        private List<R> results;

        public Builder<R> withName(String name) {
            this.name = name;
            return this;
        }

        public Builder<R> withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder<R> withTotalVoteCount(int totalVoteCount) {
            this.totalVoteCount = totalVoteCount;
            return this;
        }

        public Builder<R> withResults(List<R> results) {
            this.results = results;
            return this;
        }

        public Poll<R> build() {
            return new Poll<>(this);
        }
    }
}
