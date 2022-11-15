package by.issoft.store.XMLReader;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URISyntaxException;

public class HandleTop implements HandleCommand {
    HandleCommand secondCommand;
    SortHelper sortHelper;

    public HandleTop(HandleCommand secondCommand, SortHelper sortHelper) {
        this.secondCommand = secondCommand;
        this.sortHelper =sortHelper;
    }

    @Override
    public void handle(String command) throws ParserConfigurationException, IOException, SAXException, URISyntaxException, InterruptedException {
        if (command.equals("top")) {
            System.out.println("Print top 5 most expensive products sorted by price desc");
            sortHelper.getTop5();
        }else if (secondCommand != null) {
            secondCommand.handle(command);
        }
    }
}
