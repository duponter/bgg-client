package be.edu.bggclient.internal.thing;

import java.io.IOException;
import java.io.InputStream;

import be.edu.bggclient.internal.XmlPrinter;
import be.edu.bggclient.internal.xml.XmlFormatter;
import be.edu.bggclient.internal.xml.XmlInput;
import be.edu.bggclient.internal.xml.XmlNode;
import be.edu.bggclient.internal.xml.XslStylesheet;
import be.edu.bggclient.thing.LanguageDependenceResult;
import be.edu.bggclient.thing.Poll;
import be.edu.bggclient.thing.SuggestedPlayerAgeResult;
import be.edu.bggclient.thing.SuggestedPlayerCountResult;
import be.edu.bggclient.thing.Thing;
import org.approvaltests.JsonApprovals;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Node;

import static org.assertj.core.api.Assertions.assertThat;

class ThingXmlNodeTest {
    @Test
    void mapsXmlToPojo() {
        JsonApprovals.verifyAsJson(new ThingXmlNode(readThingXml()).build());
    }

    @Test
    void pojoToXmlMatchesCleanedOriginalXml() {
        Node xmlNode = readThingXml();

        String prettyPrint = XmlPrinter.prettyPrint(toXmlNode(new ThingXmlNode(xmlNode).build()));
        String cleanedXml = new XslStylesheet(ThingXmlNodeTest.class, "thing-xml-cleanup.xsl").apply(xmlNode);
        assertThat(prettyPrint).isEqualToIgnoringWhitespace(cleanedXml);
    }

    private Node readThingXml() {
        try (InputStream xml = ThingXmlNodeTest.class.getResourceAsStream("thing.xml")) {
            assertThat(xml).isNotNull();
            return XmlNode.nodes(new XmlInput().read(xml), "//item").findFirst().orElseThrow();
        } catch (IOException ioe) {
            throw new IllegalStateException(ioe);
        }
    }

    private Element toXmlNode(Thing thing) {
        Element thingNode = DocumentHelper.createElement("item");
        thingNode.addAttribute("type", XmlFormatter.format(thing.getType()));
        thingNode.addAttribute("id", XmlFormatter.format(thing.getId()));
        thingNode.addElement("thumbnail").addText(XmlFormatter.format(thing.getThumbnailUrl()));
        thingNode.addElement("image").addText(XmlFormatter.format(thing.getImageUrl()));
        thingNode.addElement("description").addText(XmlFormatter.format(thing.getDescription()));
        thingNode.addElement("yearpublished").addAttribute("value", XmlFormatter.format(thing.getYearPublished()));
        thingNode.addElement("minplayers").addAttribute("value", XmlFormatter.format(thing.getMinPlayers()));
        thingNode.addElement("maxplayers").addAttribute("value", XmlFormatter.format(thing.getMaxPlayers()));

        Element suggestedNumberOfPlayersNode = toXmlNode(thing.getSuggestedNumberOfPlayers());
        Element suggestedNumberOfPlayersResultsNode = suggestedNumberOfPlayersNode.addElement("results");
        thing.getSuggestedNumberOfPlayers().getResults().forEach(result -> suggestedNumberOfPlayersResultsNode.add(toXmlNode(result)));
        thingNode.add(suggestedNumberOfPlayersNode);

        thingNode.addElement("playingtime").addAttribute("value", XmlFormatter.format(thing.getPlayingTime()));
        thingNode.addElement("minplaytime").addAttribute("value", XmlFormatter.format(thing.getMinPlayTime()));
        thingNode.addElement("maxplaytime").addAttribute("value", XmlFormatter.format(thing.getMaxPlayTime()));
        thingNode.addElement("minage").addAttribute("value", XmlFormatter.format(thing.getMinAge()));

        Element suggestedPlayerAgeNode = toXmlNode(thing.getSuggestedPlayerAge());
        Element suggestedPlayerAgeResultsNode = suggestedPlayerAgeNode.addElement("results");
        thing.getSuggestedPlayerAge().getResults().forEach(result -> suggestedPlayerAgeResultsNode.add(toXmlNode(result)));
        thingNode.add(suggestedPlayerAgeNode);

        Element languageDependenceNode = toXmlNode(thing.getLanguageDependence());
        Element languageDependenceResultsNode = languageDependenceNode.addElement("results");
        ;
        thing.getLanguageDependence().getResults().forEach(result -> languageDependenceResultsNode.add(toXmlNode(result)));
        thingNode.add(languageDependenceNode);

        return thingNode;
    }

    private Element toXmlNode(Poll<?> poll) {
        Element pollNode = DocumentHelper.createElement("poll");
        pollNode.addAttribute("name", XmlFormatter.format(poll.getName()));
        pollNode.addAttribute("title", XmlFormatter.format(poll.getTitle()));
        pollNode.addAttribute("totalvotes", XmlFormatter.format(poll.getTotalVoteCount()));
        return pollNode;
    }

    private Element toXmlNode(SuggestedPlayerCountResult result) {
        Element resultNode = DocumentHelper.createElement("result");
        resultNode.addAttribute("numplayers", XmlFormatter.format(result.getPlayerCount()));
        resultNode.addAttribute("value", XmlFormatter.format(result.getValue()));
        resultNode.addAttribute("numvotes", XmlFormatter.format(result.getVoteCount()));
        return resultNode;
    }

    private Element toXmlNode(SuggestedPlayerAgeResult result) {
        Element resultNode = DocumentHelper.createElement("result");
        resultNode.addAttribute("value", XmlFormatter.format(result.getValue()));
        resultNode.addAttribute("numvotes", XmlFormatter.format(result.getVoteCount()));
        return resultNode;
    }

    private Element toXmlNode(LanguageDependenceResult result) {
        Element resultNode = DocumentHelper.createElement("result");
        resultNode.addAttribute("level", XmlFormatter.format(result.getLevel()));
        resultNode.addAttribute("value", XmlFormatter.format(result.getValue()));
        resultNode.addAttribute("numvotes", XmlFormatter.format(result.getVoteCount()));
        return resultNode;
    }
}
