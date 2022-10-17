package by.issoft.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public abstract class Category implements Categories{
    private String name;
    private List<Product> productList;

    public Category(String name) {
        this.name = name;
        this.productList = new ArrayList<>();
    }

    public void addProduct(Product product) {
        this.productList.add(product);
    }

    public String getName() {
        return name;
    }

    public void print() {
        System.out.println("category name: " + getName());
        System.out.println();
        for (Product product : productList) {
            System.out.println("Name: " + product.getName());
            System.out.println("Price: " + product.getPrice());
            System.out.println("Rate: " + product.getRate());
            System.out.println();

        }
        System.out.println("---");
    }

    public List<Product> getProductList() {
        return productList;
    }

}