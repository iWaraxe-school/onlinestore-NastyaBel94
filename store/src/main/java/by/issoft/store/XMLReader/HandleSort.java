package by.issoft.store.XMLReader;

import by.issoft.domain.Product;
import by.issoft.store.MyClient;
import by.issoft.domain.Categories;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class HandleSort implements HandleCommand {
    HandleCommand secondCommand;
    SortHelper sortHelper;

    public HandleSort(HandleCommand secondCommand, SortHelper sortHelper) {
        this.secondCommand = secondCommand;
        this.sortHelper = sortHelper;
    }

    @Override
    public void handle(String command) throws ParserConfigurationException, IOException, SAXException, URISyntaxException, InterruptedException {

        if (command.equals("sort")) {
            List<Product> i = sortHelper.sortProductList(XMLParser.xmlReader());
            sortHelper.printAllProducts(i);

        } else if (secondCommand != null) {
            secondCommand.handle(command);
        }
    }
}
