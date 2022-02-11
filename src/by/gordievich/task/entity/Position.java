package by.gordievich.task.entity;

public class Position {
    private Product product;
    private int requiredNumber;
    private double total;
    private int discount;

    public Position(Product product, int requiredNumber) {
        this.product = product;
        this.requiredNumber = requiredNumber;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getRequiredNumber() {
        return requiredNumber;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Product getProduct() {
        return product;
    }

}



