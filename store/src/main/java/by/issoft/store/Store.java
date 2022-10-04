package by.issoft.store;

import by.issoft.domain.Category;
import by.issoft.domain.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

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

    //Объединение всех продуктов и категорий
    public Stream<Product> getProductStream() {
        return categoryList.stream().flatMap(c -> c.getProductStream());
    }
}