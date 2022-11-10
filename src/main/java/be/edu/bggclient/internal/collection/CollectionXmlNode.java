package be.edu.bggclient.internal.collection;

import java.util.stream.Collectors;

import be.edu.bggclient.collection.Collection;
import be.edu.bggclient.internal.Builder;
import be.edu.bggclient.internal.xml.XmlFormatter;
import be.edu.bggclient.internal.xml.XmlNode;
import org.w3c.dom.Node;

public class CollectionXmlNode extends XmlNode implements Builder<Collection> {
    public CollectionXmlNode(Node node) {
        super(node);
    }

    @Override
    public Collection build() {
        return new Collection.Builder()
                .withItemCount(this.number("@totalitems").intValue())
                .withPublicationDate(XmlFormatter.parseDateTime(this.string("@pubdate")))
                .withItems(
                        this.nodes("item")
                                .map(CollectionItemXmlNode::new)
                                .map(Builder::build)
                                .collect(Collectors.toList())
                )
                .build();
    }
}
