package be.edu.bggclient.internal.collection;

import java.util.stream.Collectors;

import be.edu.bggclient.collection.Rating;
import be.edu.bggclient.internal.Builder;
import be.edu.bggclient.internal.xml.XmlNode;
import org.w3c.dom.Node;

public class RatingXmlNode extends XmlNode implements Builder<Rating> {
    public RatingXmlNode(Node node) {
        super(node);
    }

    @Override
    public Rating build() {
        return new Rating.Builder()
                .withUserRating(this.stringValueAttribute("."))
                .withRatingCount(this.numericValueAttribute("usersrated").intValue())
                .withAverage(this.numericValueAttribute("average").doubleValue())
                .withBayesianAverage(this.numericValueAttribute("bayesaverage").doubleValue())
                .withStandardDeviation(this.numericValueAttribute("stddev").doubleValue())
                .withMedian(this.numericValueAttribute("median").doubleValue())
                .withRanks(
                        this.nodes("ranks/rank")
                                .map(RankXmlNode::new)
                                .map(Builder::build)
                                .collect(Collectors.toList())
                )
                .build();
    }
}
