package by.issoft.store.XMLReader;

import by.issoft.store.CreateOrder;
import by.issoft.store.Store;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HandleCreateOrder implements HandleCommand {
    HandleCommand secondCommand;

    public HandleCreateOrder(HandleCommand secondCommand) {
        this.secondCommand = secondCommand;
    }



    @Override
    public void handle(String command) throws ParserConfigurationException, IOException, SAXException {
        if (command.equals("createOrder")) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter an index of product to order:");
            String indexString = reader.readLine();
            int index = Integer.parseInt(indexString);
            new CreateOrder(index).start();;

        } else if (secondCommand != null) {
            secondCommand.handle(command);
        }

    }
}
