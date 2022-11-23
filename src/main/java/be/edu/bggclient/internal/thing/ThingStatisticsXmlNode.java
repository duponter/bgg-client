package be.edu.bggclient.internal.thing;

import java.util.stream.Collectors;

import be.edu.bggclient.internal.Builder;
import be.edu.bggclient.internal.xml.XmlNode;
import be.edu.bggclient.thing.ThingStatistics;
import org.w3c.dom.Node;

public class ThingStatisticsXmlNode extends XmlNode implements Builder<ThingStatistics> {
    public ThingStatisticsXmlNode(Node node) {
        super(node);
    }

    @Override
    public ThingStatistics build() {
        return new ThingStatistics.Builder()
                .withRatingCount(this.numericValueAttribute("usersrated").intValue())
                .withAverage(this.numericValueAttribute("average").doubleValue())
                .withBayesianAverage(this.numericValueAttribute("bayesaverage").doubleValue())
                .withRanks(
                        this.nodes("ranks/rank")
                                .map(RankXmlNode::new)
                                .map(Builder::build)
                                .collect(Collectors.toList())
                )
                .withStandardDeviation(this.numericValueAttribute("stddev").doubleValue())
                .withMedian(this.numericValueAttribute("median").doubleValue())
                .withOwnCount(this.numericValueAttribute("owned").intValue())
                .withTradeCount(this.numericValueAttribute("trading").intValue())
                .withWantCount(this.numericValueAttribute("wanting").intValue())
                .withWishCount(this.numericValueAttribute("wishing").intValue())
                .withCommentCount(this.numericValueAttribute("numcomments").intValue())
                .withWeightCount(this.numericValueAttribute("numweights").intValue())
                .withAverageWeight(this.numericValueAttribute("averageweight").doubleValue())
                .build();
    }
}
