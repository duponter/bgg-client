package be.edu.bggclient.internal.play;

import be.edu.bggclient.internal.Builder;
import be.edu.bggclient.internal.xml.XmlNode;
import be.edu.bggclient.play.Player;
import org.w3c.dom.Node;

public class PlayerXmlNode extends XmlNode implements Builder<Player> {
    public PlayerXmlNode(Node node) {
        super(node);
    }

    @Override
    public Player build() {
        return new Player.Builder()
                .withUsername(this.string("@username"))
                .withUserId(this.string("@userid"))
                .withName(this.string("@name"))
                .withStartPosition(this.number("@startposition").intValue())
                .withColor(this.string("@color"))
                .withScore(this.string("@score"))
                .withNewPlayer(this.toBoolean("@new"))
                .withRating(this.string("@rating"))
                .withWin(this.toBoolean("@win"))
                .build();
    }
}
