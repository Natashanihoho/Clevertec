package by.gordievich.task.entity;

public class Position {
    private Product product;
    protected int id;
    protected String description;
    protected double price;
    protected int requiredNumber;
    protected double total;

    public Position(Product product, int requiredNumber) {
        this.id = product.getId();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.requiredNumber = requiredNumber;
    }

    public void calculateTotal(int number){
        total = price * number;
    }

    public int getRequiredNumber() {
        return requiredNumber;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return String.format("%-4d %-13s %-7.2f %-8d %-6.2f", id, description, price, requiredNumber, total);
    }
}



