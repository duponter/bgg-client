package be.edu.bggclient.internal.geeklist;

import java.io.IOException;
import java.io.InputStream;

import be.edu.bggclient.geeklist.Geeklist;
import be.edu.bggclient.internal.xml.XmlInput;
import be.edu.bggclient.internal.xml.XmlNode;
import be.edu.bggclient.internal.xml.XslStylesheet;
import org.approvaltests.JsonApprovals;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GeeklistXmlNodeTest {
    @Test
    void mapsXmlToGeeklistPojo() throws IOException {
        try (InputStream xml = GeeklistXmlNodeTest.class.getResourceAsStream("geeklist.xml")) {
            assertThat(xml).isNotNull();
            Geeklist geeklist = XmlNode.nodes(new XmlInput().read(xml), "//geeklist")
                    .map(GeeklistXmlNode::new)
                    .map(GeeklistXmlNode::build)
                    .findFirst()
                    .orElseThrow();
            JsonApprovals.verifyAsJson(geeklist);
        }
    }

    @Test
    void generatesJsonFromXml() throws IOException {
        try (InputStream xml = GeeklistXmlNodeTest.class.getResourceAsStream("geeklist.xml")) {
            XslStylesheet xslStylesheet = new XslStylesheet("/be/edu/bggclient/internal/geeklist/geeklist-to-json/xsl");
            System.out.println(xslStylesheet.apply(new XmlInput().read(xml)));
        }
    }
}
