package be.edu.bggclient.internal.thing;

import be.edu.bggclient.internal.Builder;
import be.edu.bggclient.internal.xml.XmlNode;
import be.edu.bggclient.thing.Name;
import org.w3c.dom.Node;

public class NameXmlNode extends XmlNode implements Builder<Name> {
    public NameXmlNode(Node node) {
        super(node);
    }

    @Override
    public Name build() {
        return new Name.Builder()
                .withType(this.string("@type"))
                .withSortIndex(this.number("@sortindex").intValue())
                .withValue(this.string("@value"))
                .build();
    }
}
