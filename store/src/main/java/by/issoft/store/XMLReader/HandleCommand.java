package by.issoft.store.XMLReader;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URISyntaxException;

public interface HandleCommand {
    void handle(String command) throws ParserConfigurationException, IOException, SAXException, URISyntaxException, InterruptedException;
}
