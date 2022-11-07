package be.edu.bggclient.internal.geeklist;

import be.edu.bggclient.geeklist.GeeklistItem;
import be.edu.bggclient.internal.Builder;
import be.edu.bggclient.internal.xml.XmlNode;
import org.w3c.dom.Node;

public class GeeklistItemXmlNode extends XmlNode implements Builder<GeeklistItem> {
    public GeeklistItemXmlNode(Node node) {
        super(node);
    }

    @Override
    public GeeklistItem build() {
        return new GeeklistItem.Builder()
                .withId(this.string("@id"))
                .withObjectType(this.string("@objecttype"))
                .withSubType(this.string("@subtype"))
                .withObjectId(this.string("@objectid"))
                .withObjectName(this.string("@objectname"))
                .withUsername(this.string("@username"))
                .withPostDate(this.dateTime("@postdate"))
                .withEditDate(this.dateTime("@editdate"))
                .withThumbs(this.number("@thumbs").intValue())
                .withImageId(this.string("@imageid"))
                .withComments(this.string("body"))
                .build();
    }
}
