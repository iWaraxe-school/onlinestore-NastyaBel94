package by.issoft.store.XMLReader;

import by.issoft.store.MyClient;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

public class HandleAddToCart implements HandleCommand {
    HandleCommand secondCommand;
    MyClient client;

    public HandleAddToCart(MyClient client, HandleCommand secondCommand) {
        this.client = client;
        this.secondCommand = secondCommand;
    }

    @Override
    public void handle(String command) throws ParserConfigurationException, IOException, SAXException, URISyntaxException, InterruptedException {

        if (command.equalsIgnoreCase("addtocart")) {

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter an index of product to order:");
            String indexString = reader.readLine();
            int productid = Integer.parseInt(indexString);
            client.addToCart(productid);


        } else if (secondCommand != null) {
            secondCommand.handle(command);
        }
    }
}
