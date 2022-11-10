package be.edu.bggclient.internal.collection;

import be.edu.bggclient.collection.PrivateInfo;
import be.edu.bggclient.internal.Builder;
import be.edu.bggclient.internal.xml.XmlFormatter;
import be.edu.bggclient.internal.xml.XmlNode;
import org.w3c.dom.Node;

public class PrivateInfoXmlNode extends XmlNode implements Builder<PrivateInfo> {
    public PrivateInfoXmlNode(Node node) {
        super(node);
    }

    @Override
    public PrivateInfo build() {
        return new PrivateInfo.Builder()
                .withPricePaidCurrency(this.string("@pp_currency"))
                .withPricePaid(this.string("@pricepaid"))
                .withCurrentValueCurrency(this.string("@cv_currency"))
                .withCurrentValue(this.string("@currvalue"))
                .withQuantity(this.number("@quantity").intValue())
                .withAcquisitionDate(XmlFormatter.parseDate(this.string("@acquisitiondate")))
                .withAcquiredFrom(this.string("@acquiredfrom"))
                .withInventoryDate(XmlFormatter.parseDate(this.string("@inventorydate")))
                .withInventoryLocation(this.string("@inventorylocation"))
                .withComment(this.string("privatecomment"))
                .build();
    }
}
