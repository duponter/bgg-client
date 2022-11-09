package be.edu.bggclient.internal.geeklist;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

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

import static org.assertj.core.api.Assertions.assertThat;

class GeeklistXmlNodeTest {
    @Test
    void mapsXmlToGeeklistPojo() throws IOException {
        try (InputStream xml = GeeklistXmlNodeTest.class.getResourceAsStream("geeklist.xml")) {
            assertThat(xml).isNotNull();
            // TODO_EDU support multiple geeklists?
            Geeklist geeklist = XmlNode.nodes(new XmlInput().read(xml), "//geeklist")
                    .map(GeeklistXmlNode::new)
                    .map(GeeklistXmlNode::build)
                    .findFirst()
                    .orElseThrow();
            JsonApprovals.verifyAsJson(geeklist);

            OutputFormat format = OutputFormat.createPrettyPrint();
            XMLWriter writer = new XMLWriter(System.out, format);
            writer.write(toXml(geeklist));
        }
    }

    private Element toXml(Geeklist geeklist) {
        Element geeklistNode = DocumentHelper.createElement("geeklist");
        geeklistNode.addAttribute("id", geeklist.getId());
        geeklistNode.addElement("postdate").addText(geeklist.getPostDate().atOffset(ZoneOffset.UTC).format(DateTimeFormatter.RFC_1123_DATE_TIME));
        geeklistNode.addElement("editdate").addText(geeklist.getEditDate().atOffset(ZoneOffset.UTC).format(DateTimeFormatter.RFC_1123_DATE_TIME));
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
        itemNode.addAttribute("postdate", item.getPostDate().atOffset(ZoneOffset.UTC).format(DateTimeFormatter.RFC_1123_DATE_TIME));
        itemNode.addAttribute("editdate", item.getEditDate().atOffset(ZoneOffset.UTC).format(DateTimeFormatter.RFC_1123_DATE_TIME));
        itemNode.addAttribute("thumbs", Integer.toString(item.getThumbs()));
        itemNode.addAttribute("imageid", item.getImageId());
        itemNode.addElement("body").addText(item.getComments());
        return itemNode;
    }

    @Test
    void generatesJsonFromXml() throws IOException {
        try (InputStream xml = GeeklistXmlNodeTest.class.getResourceAsStream("geeklist.xml");
             InputStream expected = GeeklistXmlNodeTest.class.getResourceAsStream("GeeklistXmlNodeTest.mapsXmlToGeeklistPojo.approved.json")) {
            XslStylesheet xslStylesheet = new XslStylesheet("/be/edu/bggclient/internal/geeklist/geeklist-to-json/xsl");
            String toJson = xslStylesheet.apply(XmlNode.nodes(new XmlInput().read(xml), "//geeklist").findFirst().orElseThrow());
            System.out.println(toJson);
            assertThat(expected).asString(Charset.defaultCharset()).isEqualToIgnoringWhitespace(toJson);
        }
    }
}
