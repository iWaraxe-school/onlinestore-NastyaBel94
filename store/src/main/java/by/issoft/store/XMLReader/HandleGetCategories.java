package by.issoft.store.XMLReader;

import by.issoft.domain.Categories;
import by.issoft.store.MyClient;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class HandleGetCategories implements HandleCommand {
    HandleCommand secondCommand;
    MyClient client;

    public HandleGetCategories(MyClient client, HandleCommand secondCommand) {
        this.client= client;
        this.secondCommand = secondCommand;

    }

    @Override
    public void handle(String command) throws ParserConfigurationException, IOException, SAXException, URISyntaxException, InterruptedException {
        if (command.equalsIgnoreCase("getcategories")) {

            System.out.println("List of Categories:");
            List<Categories> categoriesList = client.getCategories();
            for (Categories categories : categoriesList) {
                System.out.println(categories.getName());
            }

        } else if (secondCommand != null) {
            secondCommand.handle(command);
        }


    }
}

