package be.edu.bggclient.internal.xml;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public final class XmlInput {
    private static final DocumentBuilderFactory FACTORY = DocumentBuilderFactory.newInstance();

    static {
        FACTORY.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        FACTORY.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
    }

    private XmlInput() {
        throw new UnsupportedOperationException("This static class cannot be instantiated.");
    }

    public static DocumentBuilder newDocumentBuilder() {
        try {
            return FACTORY.newDocumentBuilder();
        } catch (ParserConfigurationException pce) {
            throw new IllegalStateException("Unable to create DocumentBuilder from factory", pce);
        }
    }
}
