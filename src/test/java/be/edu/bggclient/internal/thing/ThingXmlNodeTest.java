package be.edu.bggclient.internal.thing;

import java.io.IOException;
import java.io.InputStream;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import be.edu.bggclient.internal.xml.XmlFormatter;
import be.edu.bggclient.internal.xml.XmlInput;
import be.edu.bggclient.internal.xml.XmlNode;
import be.edu.bggclient.internal.xml.XmlPrinter;
import be.edu.bggclient.internal.xml.XslStylesheet;
import be.edu.bggclient.thing.LanguageDependenceResult;
import be.edu.bggclient.thing.Link;
import be.edu.bggclient.thing.Name;
import be.edu.bggclient.thing.Poll;
import be.edu.bggclient.thing.Rank;
import be.edu.bggclient.thing.SuggestedPlayerAgeResult;
import be.edu.bggclient.thing.SuggestedPlayerCountResult;
import be.edu.bggclient.thing.Thing;
import be.edu.bggclient.thing.ThingStatistics;
import org.approvaltests.JsonApprovals;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Node;

import static org.assertj.core.api.Assertions.assertThat;

class ThingXmlNodeTest {
    @Test
    void mapsXmlToPojo() {
        JsonApprovals.verifyAsJson(readThingXmls().map(ThingXmlNode::new).map(ThingXmlNode::build).collect(Collectors.toList()));
    }

    @Test
    void pojoToXmlMatchesCleanedOriginalXml() {
        assertThat(readThingXmls().collect(Collectors.toList()))
                .isNotEmpty()
                .allSatisfy(this::pojoToXmlMatchesCleanedOriginalXml);
    }

    private void pojoToXmlMatchesCleanedOriginalXml(Node xmlNode) {
        String prettyPrint = XmlPrinter.prettyPrint(toXmlNode(new ThingXmlNode(xmlNode).build()));
        String cleanedXml = new XslStylesheet(ThingXmlNodeTest.class, "thing-xml-cleanup.xsl").apply(xmlNode);
        assertThat(prettyPrint).isEqualToIgnoringWhitespace(cleanedXml);
    }

    private Stream<Node> readThingXmls() {
        try (InputStream xml = ThingXmlNodeTest.class.getResourceAsStream("thing.xml")) {
            assertThat(xml).isNotNull();
            return XmlNode.nodes(new XmlInput().read(xml), "//item");
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
        thing.getNames().forEach(name -> thingNode.add(toXmlNode(name)));
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
        thing.getLanguageDependence().getResults().forEach(result -> languageDependenceResultsNode.add(toXmlNode(result)));
        thingNode.add(languageDependenceNode);

        thing.getLinks().forEach(link -> thingNode.add(toXmlNode(link)));
        if (thing.getStatistics() != null) {
            thingNode.add(toXmlNode(thing.getStatistics()));
        }

        return thingNode;
    }

    private Element toXmlNode(Name name) {
        Element nameNode = DocumentHelper.createElement("name");
        nameNode.addAttribute("type", XmlFormatter.format(name.getType()));
        nameNode.addAttribute("sortindex", XmlFormatter.format(name.getSortIndex()));
        nameNode.addAttribute("value", XmlFormatter.format(name.getValue()));
        return nameNode;
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

    private Element toXmlNode(Link link) {
        Element linkNode = DocumentHelper.createElement("link");
        linkNode.addAttribute("type", XmlFormatter.format(link.getType()));
        linkNode.addAttribute("id", XmlFormatter.format(link.getId()));
        linkNode.addAttribute("value", XmlFormatter.format(link.getValue()));
        return linkNode;
    }

    private Element toXmlNode(ThingStatistics statistics) {
        Element statisticsNode = DocumentHelper.createElement("statistics");

        Element ratingNode = DocumentHelper.createElement("ratings");
        ratingNode.addElement("usersrated").addAttribute("value", XmlFormatter.format(statistics.getRatingCount()));
        ratingNode.addElement("average").addAttribute("value", XmlFormatter.format(statistics.getAverage()));
        ratingNode.addElement("bayesaverage").addAttribute("value", XmlFormatter.format(statistics.getBayesianAverage()));
        Element ranksNode = ratingNode.addElement("ranks");
        statistics.getRanks().forEach(rank -> ranksNode.add(toXmlNode(rank)));
        ratingNode.addElement("stddev").addAttribute("value", XmlFormatter.format(statistics.getStandardDeviation()));
        ratingNode.addElement("median").addAttribute("value", XmlFormatter.format(statistics.getMedian()));
        ratingNode.addElement("owned").addAttribute("value", XmlFormatter.format(statistics.getOwnCount()));
        ratingNode.addElement("trading").addAttribute("value", XmlFormatter.format(statistics.getTradeCount()));
        ratingNode.addElement("wanting").addAttribute("value", XmlFormatter.format(statistics.getWantCount()));
        ratingNode.addElement("wishing").addAttribute("value", XmlFormatter.format(statistics.getWishCount()));
        ratingNode.addElement("numcomments").addAttribute("value", XmlFormatter.format(statistics.getCommentCount()));
        ratingNode.addElement("numweights").addAttribute("value", XmlFormatter.format(statistics.getWeightCount()));
        ratingNode.addElement("averageweight").addAttribute("value", XmlFormatter.format(statistics.getAverageWeight()));

        statisticsNode.add(ratingNode);
        return statisticsNode;
    }

    private Element toXmlNode(Rank rank) {
        Element rankNode = DocumentHelper.createElement("rank");
        rankNode.addAttribute("type", XmlFormatter.format(rank.getType()));
        rankNode.addAttribute("id", XmlFormatter.format(rank.getId()));
        rankNode.addAttribute("name", XmlFormatter.format(rank.getName()));
        rankNode.addAttribute("friendlyname", XmlFormatter.format(rank.getFriendlyName()));
        rankNode.addAttribute("value", XmlFormatter.format(rank.getValue()));
        rankNode.addAttribute("bayesaverage", XmlFormatter.format(rank.getBayesianAverage()));
        return rankNode;
    }
}
