package by.issoft.domain;

import java.util.ArrayList;
import java.util.List;

public abstract class Category {
    public String name;
    private List <Product> productList;


    public Category (String name){
        this.name=name;
        this.productList = new ArrayList<>();
    }
}
