package by.gordievich.task.entity;


public class DiscountForFivePosition extends Position {

    private final int DISCOUNT = 10;

    public DiscountForFivePosition(Product product, int requiredNumber) {
        super(product, requiredNumber);
    }

    public double getDiscountValue() {
        double coefficient = DISCOUNT / 100.;
        return (-1) * total * coefficient / (1 - coefficient);
    }

    public double getTotalBeforeDiscount() {
        double coefficient = DISCOUNT / 100.;
        return total / (1 - coefficient);
    }

    @Override
    public void calculateTotal(int number) {
        total = price * number * (1 - DISCOUNT / 100.);
    }

    @Override
    public String toString() {
        return String.format("%-4d %-13s %-7.2f %-8d %-6.2f\n%40.2f\n%41.2f",
                id, description, price, requiredNumber, getTotalBeforeDiscount(), getDiscountValue() , total);
    }
}
