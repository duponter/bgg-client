package be.edu.bggclient.internal.thing;

import be.edu.bggclient.internal.Builder;
import be.edu.bggclient.internal.xml.XmlNode;
import be.edu.bggclient.thing.Link;
import org.w3c.dom.Node;

public class LinkXmlNode extends XmlNode implements Builder<Link> {
    public LinkXmlNode(Node node) {
        super(node);
    }

    @Override
    public Link build() {
        return new Link.Builder()
                .withType(this.string("@type"))
                .withId(this.string("@id"))
                .withValue(this.string("@value"))
                .build();
    }
}
