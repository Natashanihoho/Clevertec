package by.gordievich.task.entity;

public class Product {
    private final int id;
    private final String description;
    private double price;
    private int availableNumber;
    private boolean isDiscount;


    public Product(int id, String description, double price, int availableNumber, boolean isDiscount) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.availableNumber = availableNumber;
        this.isDiscount = isDiscount;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public boolean isDiscount() {
        return isDiscount;
    }

    public int getAvailableNumber() {
        return availableNumber;
    }

    public void setAvailableNumber(int availableNumber) {
        this.availableNumber = availableNumber;
    }

}
