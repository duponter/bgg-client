package be.edu.bggclient.internal.xml;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import javax.xml.XMLConstants;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Node;

public class XslStylesheet {
    private static final String XML_DECLARATION = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
    private static final String XSL_DIRECTIVE = "<?xml-stylesheet version=\"2.0\" type=\"text/xsl\" href=\"%s\"?>";

    private final String location;

    public XslStylesheet(String location) {
        this.location = location;
    }

    public String includeInXml(String xml) {
        return String.join(
                System.lineSeparator(),
                XML_DECLARATION,
                String.format(XSL_DIRECTIVE, location),
                xml.replace(XML_DECLARATION, "")
        );
    }

    public String read() {
        String filename = this.asFilename();
        try {
            return Files.readString(Path.of(Objects.requireNonNull(XslStylesheet.class.getResource(filename)).toURI()));
        } catch (IOException | URISyntaxException e) {
            throw new IllegalStateException("Unable to read Xsl Stylesheet " + filename, e);
        }
    }

    public String apply(Node node) {
        TransformerFactory factory = TransformerFactory.newInstance();
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "");

        try {
            Transformer transformer = factory.newTransformer(new StreamSource(XslStylesheet.class.getResourceAsStream(this.asFilename())));
            StringWriter stringWriter = new StringWriter();
            transformer.transform(new DOMSource(node), new StreamResult(stringWriter));
            return stringWriter.toString();
        } catch (TransformerException e) {
            e.printStackTrace();
            throw new IllegalStateException(e);
        }

    }

    private String asFilename() {
        int pos = this.location.lastIndexOf("/");
        return (pos == -1 ? this.location : this.location.substring(0, pos)) + ".xsl";
    }

}
