package be.edu.bggclient.internal.xml;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import javax.xml.namespace.QName;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public abstract class XmlNode {
    private final XPath xpath = XPathFactory.newInstance().newXPath();
    private final Node node;

    public static Stream<Node> nodes(Node root, String expression) {
        XmlNode rootNode = new XmlNode(root) {
        };
        return rootNode.nodes(expression).map(XmlNode::detach);
    }

    private static Node detach(Node node) {
        //https://stackoverflow.com/questions/3782618/xpath-evaluate-performance-slows-down-absurdly-over-multiple-calls
        if (node.getParentNode() != null) {
            node.getParentNode().removeChild(node);
        }
        return node;
    }

    protected XmlNode(Node node) {
        this.node = Objects.requireNonNull(node);
    }

    protected String stringValueAttribute(String attribute) {
        return this.string(attribute + "/@value");
    }

    protected String string(String expression) {
        return (String) this.data(expression, XPathConstants.STRING);
    }

    protected LocalDateTime dateTime(String expression) {
        return LocalDateTime.parse(this.string(expression), DateTimeFormatter.RFC_1123_DATE_TIME);
    }

    protected Number numericValueAttribute(String attribute) {
        return this.number(attribute + "/@value");
    }

    protected Number number(String expression) {
        return (Number) this.data(expression, XPathConstants.NUMBER);
    }

    protected boolean toBoolean(String expression) {
        return number(expression).intValue() != 0;
    }

    protected Stream<Node> nodes(String expression) {
        NodeList nodeList = (NodeList) this.data(expression, XPathConstants.NODESET);
        return IntStream.range(0, nodeList.getLength()).mapToObj(nodeList::item);
    }

    private Object data(String expression, QName returnType) {
        try {
            return xpath.evaluate(expression, node, returnType);
        } catch (XPathExpressionException e) {
            throw new IllegalStateException("Unable to evaluate xpath expression: " + expression, e);
        }
    }
}
