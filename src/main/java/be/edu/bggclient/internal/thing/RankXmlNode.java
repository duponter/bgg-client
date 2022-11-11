package be.edu.bggclient.internal.thing;

import be.edu.bggclient.internal.Builder;
import be.edu.bggclient.internal.xml.XmlNode;
import be.edu.bggclient.thing.Rank;
import org.w3c.dom.Node;

public class RankXmlNode extends XmlNode implements Builder<Rank> {
    public RankXmlNode(Node node) {
        super(node);
    }

    @Override
    public Rank build() {
        return new Rank.Builder()
                .withType(this.string("@type"))
                .withId(this.string("@id"))
                .withName(this.string("@name"))
                .withFriendlyName(this.string("@friendlyname"))
                .withValue(this.string("@value"))
                .withBayesianAverage(this.string("@bayesaverage"))
                .build();
    }
}
