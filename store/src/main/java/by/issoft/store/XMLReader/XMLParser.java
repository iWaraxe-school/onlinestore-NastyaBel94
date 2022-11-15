package by.issoft.store.XMLReader;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;


public class XMLParser {

  /*  public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {

        xmlReader();

    }*/

    public static Map<String, String> xmlReader() throws ParserConfigurationException, SAXException, IOException {
        Map<String, String> sorts = new LinkedHashMap();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.parse(new File("C:\\Users\\Nastya\\IdeaProjects\\onlinestore-NastyaBel94\\store\\src\\main\\resources\\config.xml"));

        Node sortNode = document.getElementsByTagName("sort").item(0);

        NodeList elements = sortNode.getChildNodes();

        for (int i = 0; i < elements.getLength(); i++) {
            Node nNode = elements.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                // Получение всех атрибутов элемента
                String name = elements.item(i).getNodeName();
                String value = elements.item(i).getTextContent();
                sorts.put(name, value);
            }
        }
        sorts.forEach((name, value) -> {
            System.out.println("name:" + name);
            System.out.println("value:" + value);
        });
        return sorts;
    }
}
