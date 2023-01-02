package be.edu.bggclient.internal.play;

import java.io.IOException;
import java.io.InputStream;

import be.edu.bggclient.internal.xml.XmlFormatter;
import be.edu.bggclient.internal.xml.XmlNode;
import be.edu.bggclient.internal.xml.XmlPrinter;
import be.edu.bggclient.internal.xml.XslStylesheet;
import be.edu.bggclient.play.Play;
import be.edu.bggclient.play.PlayItem;
import be.edu.bggclient.play.Player;
import be.edu.bggclient.play.Plays;
import org.approvaltests.JsonApprovals;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Node;
import org.xmlunit.builder.Input;
import org.xmlunit.util.Convert;

import static org.assertj.core.api.Assertions.assertThat;

class PlayXmlNodeTest {
    @Test
    void mapsXmlToPojo() {
        JsonApprovals.verifyAsJson(new PlaysXmlNode(readPlaysXml()).build());
    }

    @Test
    void pojoToXmlMatchesCleanedOriginalXml() {
        Node xmlNode = readPlaysXml();

        String prettyPrint = XmlPrinter.prettyPrint(toXmlNode(new PlaysXmlNode(xmlNode).build()));
        String cleanedXml = new XslStylesheet(PlayXmlNodeTest.class, "plays-xml-cleanup.xsl").apply(xmlNode);
        assertThat(prettyPrint).isEqualToIgnoringWhitespace(cleanedXml);
    }

    private Node readPlaysXml() {
        try (InputStream xml = PlayXmlNodeTest.class.getResourceAsStream("plays.xml")) {
            assertThat(xml).isNotNull();
            return XmlNode.nodes(Convert.toNode(Input.fromStream(xml).build()), "//plays").findFirst().orElseThrow();
        } catch (IOException ioe) {
            throw new IllegalStateException(ioe);
        }
    }

    private Element toXmlNode(Plays plays) {
        Element playsNode = DocumentHelper.createElement("plays");
        playsNode.addAttribute("username", XmlFormatter.format(plays.getUsername()));
        playsNode.addAttribute("userid", XmlFormatter.format(plays.getUserId()));
        playsNode.addAttribute("total", XmlFormatter.format(plays.getTotal()));
        playsNode.addAttribute("page", XmlFormatter.format(plays.getPage()));
        plays.getPlays().forEach(play -> playsNode.add(toXmlNode(play)));
        return playsNode;
    }

    private Element toXmlNode(Play play) {
        Element playNode = DocumentHelper.createElement("play");
        playNode.addAttribute("id", XmlFormatter.format(play.getId()));
        playNode.addAttribute("date", XmlFormatter.format(play.getDate()));
        playNode.addAttribute("quantity", XmlFormatter.format(play.getQuantity()));
        playNode.addAttribute("length", XmlFormatter.format(play.getLength()));
        playNode.addAttribute("incomplete", XmlFormatter.format(play.isIncomplete()));
        playNode.addAttribute("nowinstats", XmlFormatter.format(play.isNoWinStats()));
        playNode.addAttribute("location", XmlFormatter.format(play.getLocation()));
        playNode.add(this.toXmlNode(play.getItem()));
        if (play.getComments() != null && !play.getComments().isEmpty()) {
            playNode.addElement("comments").addText(play.getComments());
        }

        if (play.getPlayers() != null && !play.getPlayers().isEmpty()) {
            Element playersNode = playNode.addElement("players");
            play.getPlayers().forEach(player -> playersNode.add(toXmlNode(player)));
        }

        return playNode;
    }

    private Element toXmlNode(PlayItem item) {
        Element itemNode = DocumentHelper.createElement("item");
        itemNode.addAttribute("name", XmlFormatter.format(item.getName()));
        itemNode.addAttribute("objecttype", XmlFormatter.format(item.getObjectType()));
        itemNode.addAttribute("objectid", XmlFormatter.format(item.getObjectId()));

        if (item.getSubTypes() != null && !item.getSubTypes().isEmpty()) {
            Element subTypesNode = itemNode.addElement("subtypes");
            item.getSubTypes().forEach(subType -> subTypesNode.add(DocumentHelper.createElement("subtype").addAttribute("value", subType)));
        }

        return itemNode;
    }

    private Element toXmlNode(Player player) {
        Element playerNode = DocumentHelper.createElement("player");
        playerNode.addAttribute("username", XmlFormatter.format(player.getUsername()));
        playerNode.addAttribute("userid", XmlFormatter.format(player.getUserId()));
        playerNode.addAttribute("name", XmlFormatter.format(player.getName()));
        playerNode.addAttribute("startposition", XmlFormatter.format(player.getStartPosition()));
        playerNode.addAttribute("color", XmlFormatter.format(player.getColor()));
        playerNode.addAttribute("score", XmlFormatter.format(player.getScore()));
        playerNode.addAttribute("new", XmlFormatter.format(player.isNewPlayer()));
        playerNode.addAttribute("rating", XmlFormatter.format(player.getRating()));
        playerNode.addAttribute("win", XmlFormatter.format(player.isWin()));
        return playerNode;
    }
}
