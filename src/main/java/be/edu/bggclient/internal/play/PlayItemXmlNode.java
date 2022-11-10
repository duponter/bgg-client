package be.edu.bggclient.internal.play;

import java.util.stream.Collectors;

import be.edu.bggclient.internal.Builder;
import be.edu.bggclient.internal.xml.XmlNode;
import be.edu.bggclient.play.PlayItem;
import org.w3c.dom.Node;

public class PlayItemXmlNode extends XmlNode implements Builder<PlayItem> {
    public PlayItemXmlNode(Node node) {
        super(node);
    }

    @Override
    public PlayItem build() {
        return new PlayItem.Builder()
                .withName(this.string("@name"))
                .withObjectType(this.string("@objecttype"))
                .withObjectId(this.string("@objectid"))
                .withSubTypes(
                        this.nodes("subtypes/subtype")
                                .map(SubTypeXmlNode::new)
                                .map(SubTypeXmlNode::getValue)
                                .collect(Collectors.toList())
                )
                .build();
    }

    private static class SubTypeXmlNode extends XmlNode {
        private SubTypeXmlNode(Node node) {
            super(node);
        }

        private String getValue() {
            return this.stringValueAttribute(".");
        }
    }
}
