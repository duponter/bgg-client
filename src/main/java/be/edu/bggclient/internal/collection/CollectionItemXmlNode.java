package be.edu.bggclient.internal.collection;

import be.edu.bggclient.collection.CollectionItem;
import be.edu.bggclient.internal.Builder;
import be.edu.bggclient.internal.xml.XmlNode;
import org.w3c.dom.Node;

public class CollectionItemXmlNode extends XmlNode implements Builder<CollectionItem> {
    public CollectionItemXmlNode(Node node) {
        super(node);
    }

    @Override
    public CollectionItem build() {
        return new CollectionItem.Builder()
                .withObjectType(this.string("@objecttype"))
                .withObjectId(this.string("@objectid"))
                .withSubType(this.string("@subtype"))
                .withCollectionId(this.string("@collid"))
                .withName(this.string("name"))
                .withOriginalName(this.string("originalname"))
                .withYearPublished(this.number("yearpublished").intValue())
                .withImageUrl(this.string("image"))
                .withThumbnailUrl(this.string("thumbnail"))
                .withStatistics(
                        this.nodes("stats")
                                .map(CollectionStatisticsXmlNode::new)
                                .map(Builder::build)
                                .findFirst().orElse(null)
                )
                .withStatus(
                        this.nodes("status")
                                .map(StatusXmlNode::new)
                                .map(Builder::build)
                                .findFirst().orElseThrow()
                )
                .withPlayCount(this.number("numplays").intValue())
                .withPrivateInfo(
                        this.nodes("privateinfo")
                                .map(PrivateInfoXmlNode::new)
                                .map(Builder::build)
                                .findFirst().orElse(null)
                )
                .withComment(this.string("comment"))
                .build();
    }
}
