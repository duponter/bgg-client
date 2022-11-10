package be.edu.bggclient.internal.geeklist;

import java.util.stream.Collectors;

import be.edu.bggclient.geeklist.Geeklist;
import be.edu.bggclient.internal.Builder;
import be.edu.bggclient.internal.xml.XmlFormatter;
import be.edu.bggclient.internal.xml.XmlNode;
import org.w3c.dom.Node;

public class GeeklistXmlNode extends XmlNode implements Builder<Geeklist> {
    public GeeklistXmlNode(Node node) {
        super(node);
    }

    @Override
    public Geeklist build() {
        return new Geeklist.Builder()
                .withId(this.string("@id"))
                .withPostDate(XmlFormatter.parseDateTime(this.string("postdate")))
                .withEditDate(XmlFormatter.parseDateTime(this.string("editdate")))
                .withThumbs(this.number("thumbs").intValue())
                .withItemCount(this.number("numitems").intValue())
                .withUsername(this.string("username"))
                .withTitle(this.string("title"))
                .withDescription(this.string("description"))
                .withItems(
                        this.nodes("item")
                                .map(GeeklistItemXmlNode::new)
                                .map(Builder::build)
                                .collect(Collectors.toList())
                )
                .build();
    }
}
