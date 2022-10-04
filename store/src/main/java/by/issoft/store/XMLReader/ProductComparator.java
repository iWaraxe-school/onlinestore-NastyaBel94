package by.issoft.store.XMLReader;

import by.issoft.domain.Product;

import java.util.function.Function;

public class ProductComparator<T> implements java.util.Comparator<Product> {
    private final Function<Product, Comparable<T>> productIntegerFunction;
    private SortingType type;

    public ProductComparator(SortingType type, Function<Product, Comparable<T>> productIntegerFunction) {
        this.type = type;
        this.productIntegerFunction = productIntegerFunction;
    }

    //Применение Тернарной операции
    public int compare(Product o1, Product o2) {
        return productIntegerFunction.apply(o1).compareTo((T) productIntegerFunction.apply(o2)) * (type == SortingType.ASCENDING ? 1 : -1);
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }

}
