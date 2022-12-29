package be.edu.bggclient.internal.xml;

import java.io.IOException;
import java.io.StringWriter;

import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public final class XmlPrinter {
    private XmlPrinter() {
        throw new UnsupportedOperationException("This static class cannot be instantiated.");
    }

    public static String prettyPrint(Node xmlNode) {
        try (StringWriter stringWriter = new StringWriter()) {
            new XMLWriter(stringWriter, OutputFormat.createPrettyPrint()).write(xmlNode);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
