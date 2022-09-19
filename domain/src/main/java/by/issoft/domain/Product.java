package by.issoft.domain;

public class Product {
    private String name;
    private Integer rate;
    private Integer price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getRate() {
        return rate;
    }

    public Integer getPrice() {
        return price;
    }

}