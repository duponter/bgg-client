package be.edu.bggclient.internal.geeklist;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.HashMap;
import java.util.Map;

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

import static java.time.temporal.ChronoField.DAY_OF_MONTH;
import static java.time.temporal.ChronoField.DAY_OF_WEEK;
import static java.time.temporal.ChronoField.HOUR_OF_DAY;
import static java.time.temporal.ChronoField.MINUTE_OF_HOUR;
import static java.time.temporal.ChronoField.MONTH_OF_YEAR;
import static java.time.temporal.ChronoField.SECOND_OF_MINUTE;
import static java.time.temporal.ChronoField.YEAR;
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
        }
    }

    private Element toXml(Geeklist geeklist) {
        Element geeklistNode = DocumentHelper.createElement("geeklist");
        geeklistNode.addAttribute("id", geeklist.getId());
        geeklistNode.addElement("postdate").addText(geeklist.getPostDate().atOffset(ZoneOffset.UTC).format(RFC_1123_DATE_TIME_B));
        geeklistNode.addElement("editdate").addText(geeklist.getEditDate().atOffset(ZoneOffset.UTC).format(RFC_1123_DATE_TIME_B));
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
        itemNode.addAttribute("postdate", item.getPostDate().atOffset(ZoneOffset.UTC).format(RFC_1123_DATE_TIME_B));
        itemNode.addAttribute("editdate", item.getEditDate().atOffset(ZoneOffset.UTC).format(RFC_1123_DATE_TIME_B));
        itemNode.addAttribute("thumbs", Integer.toString(item.getThumbs()));
        itemNode.addAttribute("imageid", item.getImageId());
        itemNode.addElement("body").addText(item.getComments());
        return itemNode;
    }

    private static final DateTimeFormatter RFC_1123_DATE_TIME_B;

    static {
        // manually code maps to ensure correct data always used
        // (locale data can be changed by application code)
        Map<Long, String> dow = new HashMap<>();
        dow.put(1L, "Mon");
        dow.put(2L, "Tue");
        dow.put(3L, "Wed");
        dow.put(4L, "Thu");
        dow.put(5L, "Fri");
        dow.put(6L, "Sat");
        dow.put(7L, "Sun");
        Map<Long, String> moy = new HashMap<>();
        moy.put(1L, "Jan");
        moy.put(2L, "Feb");
        moy.put(3L, "Mar");
        moy.put(4L, "Apr");
        moy.put(5L, "May");
        moy.put(6L, "Jun");
        moy.put(7L, "Jul");
        moy.put(8L, "Aug");
        moy.put(9L, "Sep");
        moy.put(10L, "Oct");
        moy.put(11L, "Nov");
        moy.put(12L, "Dec");
        RFC_1123_DATE_TIME_B = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .parseLenient()
                .optionalStart()
                .appendText(DAY_OF_WEEK, dow)
                .appendLiteral(", ")
                .optionalEnd()
                .appendValue(DAY_OF_MONTH, 2)
                .appendLiteral(' ')
                .appendText(MONTH_OF_YEAR, moy)
                .appendLiteral(' ')
                .appendValue(YEAR, 4)  // 2 digit year not handled
                .appendLiteral(' ')
                .appendValue(HOUR_OF_DAY, 2)
                .appendLiteral(':')
                .appendValue(MINUTE_OF_HOUR, 2)
                .optionalStart()
                .appendLiteral(':')
                .appendValue(SECOND_OF_MINUTE, 2)
                .optionalEnd()
                .appendLiteral(' ')
                .appendOffset("+HHMM", "+0000")  // should handle UT/Z/EST/EDT/CST/CDT/MST/MDT/PST/MDT
                .toFormatter();
    }


    @Test
    void generatesJsonFromXml() throws IOException {
        try (InputStream xml = GeeklistXmlNodeTest.class.getResourceAsStream("geeklist.xml")) {
            assertThat(xml).isNotNull();
            // TODO_EDU support multiple geeklists?
            Node xmlNode = XmlNode.nodes(new XmlInput().read(xml), "//geeklist").findFirst().orElseThrow();
            Geeklist geeklist = new GeeklistXmlNode(xmlNode).build();

            XslStylesheet xslStylesheet = new XslStylesheet("/be/edu/bggclient/internal/geeklist/geeklist-xml-cleanup/xsl");
            String cleaned = xslStylesheet.apply(xmlNode);

            StringWriter stringWriter = new StringWriter();
            XMLWriter writer = new XMLWriter(stringWriter, OutputFormat.createPrettyPrint());
            writer.write(toXml(geeklist));

            assertThat(stringWriter.toString()).isEqualToIgnoringWhitespace(cleaned);
        }
    }
}
