package be.edu.bggclient.internal.play;

import java.util.stream.Collectors;

import be.edu.bggclient.internal.Builder;
import be.edu.bggclient.internal.xml.XmlFormatter;
import be.edu.bggclient.internal.xml.XmlNode;
import be.edu.bggclient.play.Play;
import org.w3c.dom.Node;

public class PlayXmlNode extends XmlNode implements Builder<Play> {
    public PlayXmlNode(Node node) {
        super(node);
    }

    @Override
    public Play build() {
        return new Play.Builder()
                .withId(this.string("@id"))
                .withDate(XmlFormatter.parseDate(this.string("@date")))
                .withQuantity(this.number("@quantity").intValue())
                .withLength(this.number("@length").intValue())
                .withIncomplete(this.toBoolean("@incomplete"))
                .withNoWinStats(this.toBoolean("@nowinstats"))
                .withLocation(this.string("@location"))
                .withItem(
                        this.nodes("item")
                                .map(PlayItemXmlNode::new)
                                .map(Builder::build)
                                .findFirst().orElseThrow()
                )
                .withComments(this.string("comments"))
                .withPlayers(
                        this.nodes("players/player")
                                .map(PlayerXmlNode::new)
                                .map(Builder::build)
                                .collect(Collectors.toList())
                )
                .build();
    }
}
