package be.edu.bggclient.internal.collection;

import java.io.IOException;
import java.io.InputStream;

import be.edu.bggclient.collection.Collection;
import be.edu.bggclient.collection.CollectionItem;
import be.edu.bggclient.collection.CollectionStatistics;
import be.edu.bggclient.collection.PrivateInfo;
import be.edu.bggclient.collection.Rating;
import be.edu.bggclient.collection.Status;
import be.edu.bggclient.internal.xml.XmlFormatter;
import be.edu.bggclient.internal.xml.XmlInput;
import be.edu.bggclient.internal.xml.XmlNode;
import be.edu.bggclient.internal.xml.XmlPrinter;
import be.edu.bggclient.internal.xml.XslStylesheet;
import be.edu.bggclient.thing.Rank;
import org.approvaltests.JsonApprovals;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Node;

import static org.assertj.core.api.Assertions.assertThat;

class CollectionXmlNodeTest {
    @Test
    void mapsXmlToPojo() {
        JsonApprovals.verifyAsJson(new CollectionXmlNode(readCollectionXml()).build());
    }

    @Test
    void pojoToXmlMatchesCleanedOriginalXml() {
        Node xmlNode = readCollectionXml();

        String prettyPrint = XmlPrinter.prettyPrint(toXmlNode(new CollectionXmlNode(xmlNode).build()));
        String cleanedXml = new XslStylesheet(CollectionXmlNodeTest.class, "collection-xml-cleanup.xsl").apply(xmlNode);
        assertThat(prettyPrint).isEqualToIgnoringWhitespace(cleanedXml);
    }

    private Node readCollectionXml() {
        try (InputStream xml = CollectionXmlNodeTest.class.getResourceAsStream("collection.xml")) {
            assertThat(xml).isNotNull();
            return XmlNode.nodes(new XmlInput().read(xml), "//items").findFirst().orElseThrow();
        } catch (IOException ioe) {
            throw new IllegalStateException(ioe);
        }
    }

    private Element toXmlNode(Collection collection) {
        Element collectionNode = DocumentHelper.createElement("items");
        collectionNode.addAttribute("totalitems", XmlFormatter.format(collection.getItemCount()));
        collectionNode.addAttribute("pubdate", XmlFormatter.format(collection.getPublicationDate()));
        collection.getItems().forEach(collectionItem -> collectionNode.add(toXmlNode(collectionItem)));
        return collectionNode;
    }

    private Element toXmlNode(CollectionItem item) {
        Element itemNode = DocumentHelper.createElement("item");
        itemNode.addAttribute("objecttype", XmlFormatter.format(item.getObjectType()));
        itemNode.addAttribute("objectid", XmlFormatter.format(item.getObjectId()));
        itemNode.addAttribute("subtype", XmlFormatter.format(item.getSubType()));
        itemNode.addAttribute("collid", XmlFormatter.format(item.getCollectionId()));
        itemNode.addElement("name").addText(XmlFormatter.format(item.getName()));
        if (item.getOriginalName() != null && !item.getOriginalName().isEmpty()) {
            itemNode.addElement("originalname").addText(item.getOriginalName());
        }
        if (item.getYearPublished() > 0) {
            itemNode.addElement("yearpublished").addText(XmlFormatter.format(item.getYearPublished()));
        }
        itemNode.addElement("image").addText(XmlFormatter.format(item.getImageUrl()));
        itemNode.addElement("thumbnail").addText(XmlFormatter.format(item.getThumbnailUrl()));
        if (item.getStatistics() != null) {
            itemNode.add(toXmlNode(item.getStatistics()));
        }
        itemNode.add(toXmlNode(item.getStatus()));
        itemNode.addElement("numplays").addText(XmlFormatter.format(item.getPlayCount()));
        if (item.getPrivateInfo() != null) {
            itemNode.add(toXmlNode(item.getPrivateInfo()));
        }
        if (item.getComment() != null && !item.getComment().isEmpty()) {
            itemNode.addElement("comment").addText(item.getComment());
        }
        return itemNode;
    }

    private Element toXmlNode(CollectionStatistics stats) {
        Element statsNode = DocumentHelper.createElement("stats");
        statsNode.addAttribute("minplayers", XmlFormatter.format(stats.getMinPlayerCount()));
        statsNode.addAttribute("maxplayers", XmlFormatter.format(stats.getMaxPlayerCount()));
        if (stats.getMinPlayTime() != null && !stats.getMinPlayTime().isEmpty()) {
            statsNode.addAttribute("minplaytime", XmlFormatter.format(stats.getMinPlayTime()));
        }
        if (stats.getMaxPlayTime() != null && !stats.getMaxPlayTime().isEmpty()) {
            statsNode.addAttribute("maxplaytime", XmlFormatter.format(stats.getMaxPlayTime()));
        }
        if (stats.getPlayingTime() != null && !stats.getPlayingTime().isEmpty()) {
            statsNode.addAttribute("playingtime", XmlFormatter.format(stats.getPlayingTime()));
        }
        statsNode.addAttribute("numowned", XmlFormatter.format(stats.getOwnCount()));
        statsNode.add(toXmlNode(stats.getRating()));
        return statsNode;
    }

    private Element toXmlNode(Rating rating) {
        Element ratingNode = DocumentHelper.createElement("rating");
        ratingNode.addAttribute("value", XmlFormatter.format(rating.getUserRating()));
        ratingNode.addElement("usersrated").addAttribute("value", XmlFormatter.format(rating.getRatingCount()));
        ratingNode.addElement("average").addAttribute("value", XmlFormatter.format(rating.getAverage()));
        ratingNode.addElement("bayesaverage").addAttribute("value", XmlFormatter.format(rating.getBayesianAverage()));
        ratingNode.addElement("stddev").addAttribute("value", XmlFormatter.format(rating.getStandardDeviation()));
        ratingNode.addElement("median").addAttribute("value", XmlFormatter.format(rating.getMedian()));
        Element ranksNode = ratingNode.addElement("ranks");
        rating.getRanks().forEach(rank -> ranksNode.add(toXmlNode(rank)));
        return ratingNode;
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

    private Element toXmlNode(Status status) {
        Element statusNode = DocumentHelper.createElement("status");
        statusNode.addAttribute("own", XmlFormatter.format(status.isOwn()));
        statusNode.addAttribute("prevowned", XmlFormatter.format(status.isPreviouslyOwned()));
        statusNode.addAttribute("fortrade", XmlFormatter.format(status.isForTrade()));
        statusNode.addAttribute("want", XmlFormatter.format(status.isWant()));
        statusNode.addAttribute("wanttoplay", XmlFormatter.format(status.isWantToPlay()));
        statusNode.addAttribute("wanttobuy", XmlFormatter.format(status.isWantToBuy()));
        statusNode.addAttribute("wishlist", XmlFormatter.format(status.isWishlist()));
        if (status.getWishlistPriority() > 0) {
            statusNode.addAttribute("wishlistpriority", XmlFormatter.format(status.getWishlistPriority()));
        }
        statusNode.addAttribute("preordered", XmlFormatter.format(status.isPreordered()));
        statusNode.addAttribute("lastmodified", XmlFormatter.formatIso(status.getLastModified()));
        return statusNode;
    }

    private Element toXmlNode(PrivateInfo privateInfo) {
        Element privateInfoNode = DocumentHelper.createElement("privateinfo");
        privateInfoNode.addAttribute("pp_currency", XmlFormatter.format(privateInfo.getPricePaidCurrency()));
        privateInfoNode.addAttribute("pricepaid", XmlFormatter.format(privateInfo.getPricePaid()));
        privateInfoNode.addAttribute("cv_currency", XmlFormatter.format(privateInfo.getCurrentValueCurrency()));
        privateInfoNode.addAttribute("currvalue", XmlFormatter.format(privateInfo.getCurrentValue()));
        privateInfoNode.addAttribute("quantity", privateInfo.getQuantity() > 0 ? XmlFormatter.format(privateInfo.getQuantity()) : "");
        privateInfoNode.addAttribute("acquisitiondate", XmlFormatter.format(privateInfo.getAcquisitionDate()));
        privateInfoNode.addAttribute("acquiredfrom", XmlFormatter.format(privateInfo.getAcquiredFrom()));
        if (privateInfo.getInventoryDate() != null) {
            privateInfoNode.addAttribute("inventorydate", XmlFormatter.format(privateInfo.getInventoryDate()));
        }
        privateInfoNode.addAttribute("inventorylocation", XmlFormatter.format(privateInfo.getInventoryLocation()));
        Element commentNode = privateInfoNode.addElement("privatecomment");
        if (privateInfo.getComment() != null && !privateInfo.getComment().isEmpty()) {
            commentNode.addText(privateInfo.getComment());
        }
        return privateInfoNode;
    }
}
