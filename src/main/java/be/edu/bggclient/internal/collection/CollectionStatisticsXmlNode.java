package be.edu.bggclient.internal.collection;

import be.edu.bggclient.collection.CollectionStatistics;
import be.edu.bggclient.internal.Builder;
import be.edu.bggclient.internal.xml.XmlNode;
import org.w3c.dom.Node;

public class CollectionStatisticsXmlNode extends XmlNode implements Builder<CollectionStatistics> {
    public CollectionStatisticsXmlNode(Node node) {
        super(node);
    }

    @Override
    public CollectionStatistics build() {
        return new CollectionStatistics.Builder()
                .withMinPlayerCount(this.string("@minplayers"))
                .withMaxPlayerCount(this.string("@maxplayers"))
                .withMinPlayTime(this.string("@minplaytime"))
                .withMaxPlayTime(this.string("@maxplaytime"))
                .withPlayingTime(this.string("@playingtime"))
                .withOwnCount(this.number("@numowned").intValue())
                .withRating(
                        this.nodes("rating")
                                .map(RatingXmlNode::new)
                                .map(Builder::build)
                                .findFirst().orElseThrow()
                )
                .build();
    }
}
