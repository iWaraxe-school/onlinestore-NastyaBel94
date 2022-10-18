package by.issoft.store;

import by.issoft.domain.*;

import java.util.ArrayList;
import java.util.List;


public class Store {

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
//Реализация паттерна Singleton
    private static Store instance;

    private Store() {}

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
}

