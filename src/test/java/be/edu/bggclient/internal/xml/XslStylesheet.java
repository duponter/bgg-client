package be.edu.bggclient.internal.xml;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
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

    private final Class<?> baseClass;
    private final String location;

    public XslStylesheet(Class<?> baseClass, String location) {
        this.baseClass = baseClass;
        this.location = location;
    }

    public String includeInXml(String xml) {
        return String.join(
                System.lineSeparator(),
                XML_DECLARATION,
                String.format(XSL_DIRECTIVE, location),
                xml.replace(XML_DECLARATION, "").trim()
        );
    }

    public String read() {
        try (InputStream inputStream = this.baseClass.getResourceAsStream(this.location)) {
            return new String(Objects.requireNonNull(inputStream).readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException ioe) {
            throw new IllegalStateException(String.format("Unable to read Xsl Stylesheet %s from base %s", this.location, this.baseClass), ioe);
        }
    }

    public String apply(Node node) {
        // TODO_EDU Introduce XmlUnit here: https://github.com/xmlunit/user-guide/wiki/XSLT-Support
        // https://mvnrepository.com/artifact/org.xmlunit/xmlunit-core
        TransformerFactory factory = TransformerFactory.newInstance();
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "");

        StringWriter stringWriter = new StringWriter();
        try (InputStream inputStream = this.baseClass.getResourceAsStream(this.location)) {
            Transformer transformer = factory.newTransformer(new StreamSource(inputStream));
            transformer.transform(new DOMSource(node), new StreamResult(stringWriter));
        } catch (IOException ioe) {
            throw new IllegalStateException(String.format("Unable to read Xsl Stylesheet %s from base %s", this.location, this.baseClass), ioe);
        } catch (TransformerException te) {
            throw new IllegalStateException("Unable to transform node " + node, te);
        }
        return stringWriter.toString();
    }
}
