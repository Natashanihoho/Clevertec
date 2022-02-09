package by.gordievich.task.entity;

public class Product {
    private int id;
    private String description;
    private double price;
    private boolean isDiscount;

    public Product(int id, String description, double price, boolean isDiscount) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.isDiscount = isDiscount;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public boolean isDiscount() {
        return isDiscount;
    }

    @Override
    public String toString() {
        return "ID = " + id + ", name = " + description + ", price = " + price;
    }
}
