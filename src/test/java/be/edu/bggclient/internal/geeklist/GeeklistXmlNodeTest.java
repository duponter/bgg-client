package be.edu.bggclient.internal.geeklist;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import be.edu.bggclient.geeklist.Geeklist;
import be.edu.bggclient.geeklist.GeeklistItem;
import be.edu.bggclient.internal.xml.XmlInput;
import be.edu.bggclient.internal.xml.XmlNode;
import be.edu.bggclient.internal.xml.XslStylesheet;
import org.approvaltests.JsonApprovals;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Node;

import static org.assertj.core.api.Assertions.assertThat;

class GeeklistXmlNodeTest {
    @Test
    void mapsXmlToPojo() {
        JsonApprovals.verifyAsJson(new GeeklistXmlNode(readGeeklistXml()).build());
    }

    private static final DateTimeFormatter BGG_DATE_TIME = DateTimeFormatter.ofPattern("E, dd MMM yyyy HH:mm:ss +0000", Locale.US);

    private Element toXml(Geeklist geeklist) {
        Element geeklistNode = DocumentHelper.createElement("geeklist");
        geeklistNode.addAttribute("id", geeklist.getId());
        geeklistNode.addElement("postdate").addText(geeklist.getPostDate().format(BGG_DATE_TIME));
        geeklistNode.addElement("editdate").addText(geeklist.getEditDate().format(BGG_DATE_TIME));
        geeklistNode.addElement("thumbs").addText(Integer.toString(geeklist.getThumbs()));
        geeklistNode.addElement("numitems").addText(Integer.toString(geeklist.getItemCount()));
        geeklistNode.addElement("username").addText(geeklist.getUsername());
        geeklistNode.addElement("title").addText(geeklist.getTitle());
        geeklistNode.addElement("description").addText(geeklist.getDescription());
        geeklist.getItems().forEach(geeklistItem -> geeklistNode.add(toXml(geeklistItem)));
        return geeklistNode;
    }

    private Element toXml(GeeklistItem item) {
        Element itemNode = DocumentHelper.createElement("item");
        itemNode.addAttribute("id", item.getId());
        itemNode.addAttribute("objecttype", item.getObjectType());
        itemNode.addAttribute("subtype", item.getSubType());
        itemNode.addAttribute("objectid", item.getObjectId());
        itemNode.addAttribute("objectname", item.getObjectName());
        itemNode.addAttribute("username", item.getUsername());
        itemNode.addAttribute("postdate", item.getPostDate().format(BGG_DATE_TIME));
        itemNode.addAttribute("editdate", item.getEditDate().format(BGG_DATE_TIME));
        itemNode.addAttribute("thumbs", Integer.toString(item.getThumbs()));
        itemNode.addAttribute("imageid", item.getImageId());
        itemNode.addElement("body").addText(item.getComments());
        return itemNode;
    }

    private String prettyPrint(Node xmlNode) {
        try (StringWriter stringWriter = new StringWriter()) {
            new XMLWriter(stringWriter, OutputFormat.createPrettyPrint()).write(toXml(new GeeklistXmlNode(xmlNode).build()));
            return stringWriter.toString();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Test
    void pojoToXmlMatchesCleanedOriginalXml() {
        Node xmlNode = readGeeklistXml();
        String cleanedXml = new XslStylesheet("/be/edu/bggclient/internal/geeklist/geeklist-xml-cleanup.xsl").apply(xmlNode);

        assertThat(prettyPrint(xmlNode)).isEqualToIgnoringWhitespace(cleanedXml);
    }

    private Node readGeeklistXml() {
        try (InputStream xml = GeeklistXmlNodeTest.class.getResourceAsStream("geeklist.xml")) {
            assertThat(xml).isNotNull();
            // TODO_EDU support multiple geeklists?
            return XmlNode.nodes(new XmlInput().read(xml), "//geeklist").findFirst().orElseThrow();
        } catch (IOException ioe) {
            throw new IllegalStateException(ioe);
        }
    }
}
