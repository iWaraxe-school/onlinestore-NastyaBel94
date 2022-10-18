package by.issoft.domain;

import java.util.List;

public interface Categories {
    void print();
    List<Product> getProductList();
    String getName();
    void addProduct(Product product);
}
