package by.issoft.store.XMLReader;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URISyntaxException;

public class HandleQuit implements HandleCommand{
    HandleCommand secondCommand;

    public HandleQuit(HandleCommand secondCommand) {
        this.secondCommand = secondCommand;
    }


    @Override
    public void handle(String command) throws ParserConfigurationException, IOException, SAXException, URISyntaxException, InterruptedException {
        if (command.equals("quit")) {
            System.out.println("System exit");
            System.exit(0);
        }else if(secondCommand != null) {
            secondCommand.handle(command);
        }
    }
}
