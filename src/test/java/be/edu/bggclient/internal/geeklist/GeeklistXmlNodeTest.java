package be.edu.bggclient.internal.geeklist;

import java.io.IOException;
import java.io.InputStream;

import be.edu.bggclient.geeklist.Geeklist;
import be.edu.bggclient.internal.xml.XmlInput;
import be.edu.bggclient.internal.xml.XmlNode;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GeeklistXmlNodeTest {
    @Test
    void mapsXmlToGeeklistPojo() throws IOException {
        try (InputStream xml = Geeklist.class.getResourceAsStream("geeklist.xml")) {
            assertThat(xml).isNotNull();
            XmlNode.nodes(new XmlInput().read(xml), "//geeklist")
                    .map(GeeklistXmlNode::new)
                    .map(GeeklistXmlNode::build)
                    .findFirst()
                    .ifPresent(System.out::println);
        }
    }
}
