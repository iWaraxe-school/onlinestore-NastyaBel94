package by.issoft.store.XMLReader;

import by.issoft.domain.Product;
import by.issoft.store.MyClient;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class HandleGetProducts implements HandleCommand {
    HandleCommand secondCommand;
    MyClient client;


    public HandleGetProducts(MyClient client, HandleCommand secondCommand) {
        this.client= client;
        this.secondCommand = secondCommand;
    }

    @Override
    public void handle(String command) throws ParserConfigurationException, IOException, SAXException, URISyntaxException, InterruptedException {
        if (command.equalsIgnoreCase("getproducts")) {

            System.out.println("List of Products:");
            List<Product> productList = client.getProducts();
            for (Product products : productList) {
                System.out.println(products.getName());
                System.out.println(products.getRate());
                System.out.println(products.getPrice());
            }
        } else if (secondCommand != null) {
            secondCommand.handle(command);
        }
    }
}
