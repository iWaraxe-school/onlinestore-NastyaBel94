package by.issoft.consoleApp;

import by.issoft.domain.Product;
import by.issoft.store.RandomStorePopulator;
import by.issoft.store.Store;
import by.issoft.store.XMLReader.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.*;


public class StoreApp {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException, ParserConfigurationException, SAXException {
        //Store onlineStore = new Store();
        RandomStorePopulator randomStorePopulator = new RandomStorePopulator();
        randomStorePopulator.fillStoreRandomly();
        SortHelper sortHelper = new SortHelper();

        Store.getInstance().printAllCategoriesAndProducts();

        HandleNonCommand handleNonCommand = new HandleNonCommand();
        HandleSort handleSort = new HandleSort(handleNonCommand, sortHelper);
        HandleTop handleTop = new HandleTop(handleSort, sortHelper);
        HandleCreateOrder handleCreateOrder = new HandleCreateOrder(handleTop);
        HandleQuit handleQuit = new HandleQuit(handleCreateOrder);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean value = true;
        while (value) {
            System.out.println("Enter command sort/top/quit/addToCart:");
            String command = reader.readLine();
            System.out.println("Your command is:" + command);

            handleQuit.handle(command);

            /*switch (command) {
                case "sort":
                    List<Product> i = sortHelper.sortProductList(XMLParser.xmlReader());
                    sortHelper.printAllProducts(i);
                    break;
                case "top":
                    System.out.println("Print top 5 most expensive products sorted by price desc");
                    sortHelper.getTop5();
                    break;
                case "quit":
                    value = false;
                    break;
                default:
                    System.out.println("The command isn't recognized");
            }*/

        }

    }

}
