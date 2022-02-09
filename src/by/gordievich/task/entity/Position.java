package by.gordievich.task.entity;

public class Position {
    private Product product;
    private int number;
    private double value;

    public Position(Product product, int number) {
        this.product = product;
        this.number = number;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Product getProduct() {
        return product;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return String.format("%-4d %-13s %-7.2f %-8d %-6.2f", product.getId(), product.getDescription(), product.getPrice(), number, value);
       // return product.getName() + " " + number + " " + product.getPrice() + " ---> " + value;
    }
}
