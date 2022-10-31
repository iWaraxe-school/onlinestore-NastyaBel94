package by.issoft.store;

import by.issoft.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;


public class Store {
    //Реализация паттерна Singleton
    private static Store instance;
    Timer timer = new Timer();

    public List<Categories> categoryList = new ArrayList<>();

    public void addCategory(Categories categories) {
        categoryList.add(categories);
    }

    public void printAllCategoriesAndProducts() {
        for (Categories categories : categoryList) {
            categories.print();
        }
    }

    public List<Categories> getCategories() {
        return categoryList;
    }

    public List<Product> getAllProductsList() {
        List<Product> allProducts = new ArrayList<>();
        for (Categories categories : categoryList) {
            allProducts.addAll(categories.getProductList());
        }
        return allProducts;
    }


    public static Store getInstance() {
        Store result = instance;
        if (result != null) {
            return result;
        }
        synchronized (Store.class) {
            if (instance == null) {
                instance = new Store();
            }
            return instance;
        }
    }

    CopyOnWriteArrayList<Product> purchasedProducts = new CopyOnWriteArrayList<>();

    public void addToCart(int index) {
        final List<Product> allProductsList = getAllProductsList();
        if (index >= 0 && index < allProductsList.size()) {
            purchasedProducts.add(allProductsList.get(index));
        } else {
            System.out.println("Product doesn't exist");
        }

    }
    public void clear() {
    purchasedProducts.clear();
    }



}

