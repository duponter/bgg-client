package be.edu.bggclient.internal.play;

import java.util.stream.Collectors;

import be.edu.bggclient.internal.Builder;
import be.edu.bggclient.internal.xml.XmlNode;
import be.edu.bggclient.play.Plays;
import org.w3c.dom.Node;

public class PlaysXmlNode extends XmlNode implements Builder<Plays> {
    public PlaysXmlNode(Node node) {
        super(node);
    }

    @Override
    public Plays build() {
        return new Plays.Builder()
                .withUsername(this.string("@username"))
                .withUserId(this.string("@userid"))
                .withTotal(this.number("@total").intValue())
                .withPage(this.number("@page").intValue())
                .withPlays(
                        this.nodes("play")
                                .map(PlayXmlNode::new)
                                .map(Builder::build)
                                .collect(Collectors.toList())
                )
                .build();
    }
}
