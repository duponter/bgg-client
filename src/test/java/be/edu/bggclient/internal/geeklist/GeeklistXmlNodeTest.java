package be.edu.bggclient.internal.geeklist;

import java.io.IOException;
import java.io.InputStream;

import be.edu.bggclient.geeklist.Geeklist;
import be.edu.bggclient.geeklist.GeeklistItem;
import be.edu.bggclient.internal.xml.XmlFormatter;
import be.edu.bggclient.internal.xml.XmlInput;
import be.edu.bggclient.internal.xml.XmlNode;
import be.edu.bggclient.internal.xml.XmlPrinter;
import be.edu.bggclient.internal.xml.XslStylesheet;
import org.approvaltests.JsonApprovals;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Node;

import static org.assertj.core.api.Assertions.assertThat;

class GeeklistXmlNodeTest {
    @Test
    void mapsXmlToPojo() {
        JsonApprovals.verifyAsJson(new GeeklistXmlNode(readGeeklistXml()).build());
    }

    @Test
    void pojoToXmlMatchesCleanedOriginalXml() {
        Node xmlNode = readGeeklistXml();

        String prettyPrint = XmlPrinter.prettyPrint(toXmlNode(new GeeklistXmlNode(xmlNode).build()));
        String cleanedXml = new XslStylesheet(GeeklistXmlNodeTest.class, "geeklist-xml-cleanup.xsl").apply(xmlNode);
        assertThat(prettyPrint).isEqualToIgnoringWhitespace(cleanedXml);
    }

    private Node readGeeklistXml() {
        try (InputStream xml = GeeklistXmlNodeTest.class.getResourceAsStream("geeklist.xml")) {
            assertThat(xml).isNotNull();
            return XmlNode.nodes(new XmlInput().read(xml), "//geeklist").findFirst().orElseThrow();
        } catch (IOException ioe) {
            throw new IllegalStateException(ioe);
        }
    }

    private Element toXmlNode(Geeklist geeklist) {
        Element geeklistNode = DocumentHelper.createElement("geeklist");
        geeklistNode.addAttribute("id", XmlFormatter.format(geeklist.getId()));
        geeklistNode.addElement("postdate").addText(XmlFormatter.format(geeklist.getPostDate()));
        geeklistNode.addElement("editdate").addText(XmlFormatter.format(geeklist.getEditDate()));
        geeklistNode.addElement("thumbs").addText(XmlFormatter.format(geeklist.getThumbs()));
        geeklistNode.addElement("numitems").addText(XmlFormatter.format(geeklist.getItemCount()));
        geeklistNode.addElement("username").addText(XmlFormatter.format(geeklist.getUsername()));
        geeklistNode.addElement("title").addText(XmlFormatter.format(geeklist.getTitle()));
        geeklistNode.addElement("description").addText(XmlFormatter.format(geeklist.getDescription()));
        geeklist.getItems().forEach(geeklistItem -> geeklistNode.add(toXmlNode(geeklistItem)));
        return geeklistNode;
    }

    private Element toXmlNode(GeeklistItem item) {
        Element itemNode = DocumentHelper.createElement("item");
        itemNode.addAttribute("id", XmlFormatter.format(item.getId()));
        itemNode.addAttribute("objecttype", XmlFormatter.format(item.getObjectType()));
        itemNode.addAttribute("subtype", XmlFormatter.format(item.getSubType()));
        itemNode.addAttribute("objectid", XmlFormatter.format(item.getObjectId()));
        itemNode.addAttribute("objectname", XmlFormatter.format(item.getObjectName()));
        itemNode.addAttribute("username", XmlFormatter.format(item.getUsername()));
        itemNode.addAttribute("postdate", XmlFormatter.format(item.getPostDate()));
        itemNode.addAttribute("editdate", XmlFormatter.format(item.getEditDate()));
        itemNode.addAttribute("thumbs", XmlFormatter.format(item.getThumbs()));
        itemNode.addAttribute("imageid", XmlFormatter.format(item.getImageId()));
        itemNode.addElement("body").addText(item.getComments());
        return itemNode;
    }
}
