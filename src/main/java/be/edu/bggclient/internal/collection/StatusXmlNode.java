package be.edu.bggclient.internal.collection;

import be.edu.bggclient.collection.Status;
import be.edu.bggclient.internal.Builder;
import be.edu.bggclient.internal.xml.XmlFormatter;
import be.edu.bggclient.internal.xml.XmlNode;
import org.w3c.dom.Node;

public class StatusXmlNode extends XmlNode implements Builder<Status> {
    public StatusXmlNode(Node node) {
        super(node);
    }

    @Override
    public Status build() {
        return new Status.Builder()
                .withOwn(this.toBoolean("@own"))
                .withPreviouslyOwned(this.toBoolean("@prevowned"))
                .withForTrade(this.toBoolean("@fortrade"))
                .withWant(this.toBoolean("@want"))
                .withWantToPlay(this.toBoolean("@wanttoplay"))
                .withWantToBuy(this.toBoolean("@wanttobuy"))
                .withWishlist(this.toBoolean("@wishlist"))
                .withWishlistPriority(this.number("@wishlistpriority").intValue())
                .withPreordered(this.toBoolean("@preordered"))
                .withLastModified(XmlFormatter.parseIsoDateTime(this.string("@lastmodified")))
                .build();
    }
}
