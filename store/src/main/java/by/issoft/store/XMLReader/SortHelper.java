package by.issoft.store.XMLReader;


import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.Store;

import java.util.*;

public class SortHelper {
    Store onlineStore;

    public SortHelper(Store onlineStore) {
        this.onlineStore = onlineStore;
    }

    public List<Product> sortProductList(Map<String, String> sorts) {
        List<Product> allProductList = onlineStore.getAllProductsList();
        List<String> allKeys = new ArrayList<>();
        List<String> allValues = new ArrayList<>();
        for (Map.Entry<String, String> entry : sorts.entrySet()) {
            allKeys.add(entry.getKey());
            allValues.add(entry.getValue());
        }
        for (int i = sorts.size() - 1; i >= 0; i--) {
            switch (allKeys.get(i)) {
                case "name":
                    if (allValues.get(i).equals("ASC")) {
                        allProductList.sort(Comparator.comparing(Product::getName));
                    } else {
                        allProductList.sort(Comparator.comparing(Product::getName).reversed());
                    }
                    break;
                case "prise":
                    if (allValues.get(i).equals("ASC")) {
                        allProductList.sort(Comparator.comparing(Product::getPrice));
                    } else {
                        allProductList.sort(Comparator.comparing(Product::getPrice).reversed());
                    }
                    break;
                case "rate":
                    if (allValues.get(i).equals("ASC")) {
                        allProductList.sort(Comparator.comparing(Product::getRate));
                    } else {
                        allProductList.sort(Comparator.comparing(Product::getRate).reversed());
                    }
                    break;
            }
        }
        return allProductList;

    }

    public void printAllProducts(List<Product> products) {
        for (Product entry : products) {
            System.out.println(entry.getName());
            System.out.println(entry.getPrice());
            System.out.println(entry.getRate());
        }
    }

    public void getTop5() {
        onlineStore.getCategories().stream()
                .map(Category::getProductList)
                .flatMap(Collection::stream)
                .sorted(Comparator.comparing(Product::getPrice).reversed())
                .limit(5)
                .forEach(System.out::println);
    }

}
