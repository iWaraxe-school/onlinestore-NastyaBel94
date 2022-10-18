package by.issoft.store.XMLReader;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class HandleNonCommand implements HandleCommand{


    public HandleNonCommand() {

    }

    @Override
    public void handle(String command) throws ParserConfigurationException, IOException, SAXException {
        System.out.println("The command isn't recognized");
    }
}
