package by.issoft.consoleApp;

import by.issoft.domain.Product;
import by.issoft.store.RandomStorePopulator;
import by.issoft.store.Store;
import by.issoft.store.XMLReader.ProductComparator;
import by.issoft.store.XMLReader.SortingType;

import java.io.Console;
import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class StoreApp {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Store onlineStore = new Store();
        RandomStorePopulator randomStorePopulator = new RandomStorePopulator();
        randomStorePopulator.fillStoreRandomly(onlineStore);

        ProductComparator<String> sortNameAsc = new ProductComparator<String>(SortingType.ASCENDING, Product::getName);
        ProductComparator<Integer> sortPriceAsc = new ProductComparator<Integer>(SortingType.ASCENDING, Product::getPrice);
        ProductComparator<Integer> sortRateDesc = new ProductComparator<Integer>(SortingType.DESCENDING, Product::getRate);
        ProductComparator<Integer> sortByPriceAndTop5 = new ProductComparator<Integer>(SortingType.DESCENDING, Product::getPrice);

        for (int i = 0; i < onlineStore.categoryList.size(); i++) {
            onlineStore.categoryList.get(i).sortProducts(sortNameAsc);
        }
        for (int i = 0; i < onlineStore.categoryList.size(); i++) {
            onlineStore.categoryList.get(i).sortProducts(sortPriceAsc);
        }
        for (int i = 0; i < onlineStore.categoryList.size(); i++) {
            onlineStore.categoryList.get(i).sortProducts(sortRateDesc);
        }

        //final Map<String, Integer> collect=
        onlineStore.getProductStream()
                .sorted(sortByPriceAndTop5)
                .limit(5)
                .forEach(p -> System.out.println(p.getName() + ":" + p.getPrice()));
        //.collect(Collectors.toMap(product-> product.getName(),product -> product.getPrice()));

        onlineStore.printAllCategoriesAndProducts();

        /*Console console = System.console();
        while (true) {
            System.out.println();
            System.out.print("Enter your command:");
            String command = console.readLine().toLowerCase();
            switch (command) {
                case "quit":
                    return;
            }
        }*/
    }

}