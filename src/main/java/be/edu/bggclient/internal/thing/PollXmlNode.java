package be.edu.bggclient.internal.thing;

import java.util.function.Function;
import java.util.stream.Collectors;

import be.edu.bggclient.internal.Builder;
import be.edu.bggclient.internal.xml.XmlNode;
import be.edu.bggclient.thing.LanguageDependenceResult;
import be.edu.bggclient.thing.Poll;
import be.edu.bggclient.thing.PollResult;
import be.edu.bggclient.thing.SuggestedPlayerAgeResult;
import be.edu.bggclient.thing.SuggestedPlayerCountResult;
import org.w3c.dom.Node;

public class PollXmlNode extends XmlNode implements Builder<Poll<PollResult>> {
    public PollXmlNode(Node node) {
        super(node);
    }

    @Override
    public Poll<PollResult> build() {
        throw new UnsupportedOperationException("There is no generic Poll builder, take one of the specific build methods: buildPlayerCountPoll, buildPlayerAgePoll or buildLanguageDependencePoll");
    }

    public Poll<SuggestedPlayerCountResult> buildPlayerCountPoll() {
        return this.buildPoll(SuggestedPlayerCountResult.class, SuggestedPlayerCountResultXmlNode::new);
    }

    private static class SuggestedPlayerCountResultXmlNode extends XmlNode implements Builder<SuggestedPlayerCountResult> {

        private SuggestedPlayerCountResultXmlNode(Node node) {
            super(node);
        }

        @Override
        public SuggestedPlayerCountResult build() {
            return new SuggestedPlayerCountResult.Builder()
                    .withPlayerCount(this.string("../@numplayers"))
                    .withValue(this.string("@value"))
                    .withVoteCount(this.number("@numvotes").intValue())
                    .build();
        }

    }

    public Poll<SuggestedPlayerAgeResult> buildPlayerAgePoll() {
        return this.buildPoll(SuggestedPlayerAgeResult.class, SuggestedPlayerAgeResultXmlNode::new);
    }

    private static class SuggestedPlayerAgeResultXmlNode extends XmlNode implements Builder<SuggestedPlayerAgeResult> {
        private SuggestedPlayerAgeResultXmlNode(Node node) {
            super(node);
        }

        @Override
        public SuggestedPlayerAgeResult build() {
            return new SuggestedPlayerAgeResult.Builder()
                    .withValue(this.string("@value"))
                    .withVoteCount(this.number("@numvotes").intValue())
                    .build();
        }

    }

    public Poll<LanguageDependenceResult> buildLanguageDependencePoll() {
        return this.buildPoll(LanguageDependenceResult.class, LanguageDependenceResultXmlNode::new);
    }

    private static class LanguageDependenceResultXmlNode extends XmlNode implements Builder<LanguageDependenceResult> {
        private LanguageDependenceResultXmlNode(Node node) {
            super(node);
        }

        @Override
        public LanguageDependenceResult build() {
            return new LanguageDependenceResult.Builder()
                    .withLevel(this.string("@level"))
                    .withValue(this.string("@value"))
                    .withVoteCount(this.number("@numvotes").intValue())
                    .build();
        }
    }

    private <R extends PollResult> Poll<R> buildPoll(Class<R> type, Function<Node, Builder<R>> construction) {
        return new Poll.Builder<R>()
                .withName(this.string("@name"))
                .withTitle(this.string("@title"))
                .withTotalVoteCount(this.number("@totalvotes").intValue())
                .withResults(
                        this.nodes("results/result")
                                .map(construction)
                                .map(Builder::build)
                                .collect(Collectors.toList())
                )
                .build();
    }
}
