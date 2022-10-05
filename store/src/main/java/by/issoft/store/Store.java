package by.issoft.store;

import by.issoft.domain.Category;
import by.issoft.domain.Product;

import java.util.ArrayList;
import java.util.List;


public class Store {
    public List<Category> categoryList = new ArrayList<>();

    public void addCategory(Category category) {
        categoryList.add(category);
    }

    public void printAllCategoriesAndProducts() {
        for (Category category : categoryList) {
            category.print();
        }
    }

    public List<Category> getCategories() {
        return categoryList;
    }

    public List<Product> getAllProductsList() {
        List<Product> allProducts = new ArrayList<>();
        for (Category category : categoryList) {
            allProducts.addAll(category.getProductList());
        }
        return allProducts;
    }

}