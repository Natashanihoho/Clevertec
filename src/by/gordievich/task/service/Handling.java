package by.gordievich.task.service;

import by.gordievich.task.entity.*;

public class Handling {

    private Basket basket;
    private Receipt receipt = new Receipt();
    
    public Handling(Basket basket) {
        this.basket = basket;
    }
    
    public void calculateReceipt() {
        for (Position position :
             basket.getPositionsFromBasket()) {
             position.calculateTotal(position.getRequiredNumber());
             receipt.addPosition(position);
        }
        double overallTotal = calculateOverallTotal();
        double overallDiscount = calculateOverallDiscount();
        double firstOverallTotal = calculateFirstOverallTotal(overallTotal, overallDiscount);
        receipt.sumUp(firstOverallTotal, overallDiscount, overallTotal);
    }

    private double calculateOverallTotal() {
        double overallTotal = 0.0;
        for (Position position:
                basket.getPositionsFromBasket()) {
            overallTotal += position.getTotal();
        }
        return overallTotal;
    }

    private double calculateOverallDiscount() {
        double overallDiscount = 0.0;
        for (Position position:
                basket.getPositionsFromBasket()) {
            if(position.getClass() == DiscountForFivePosition.class) {
                DiscountForFivePosition discountForFivePosition = (DiscountForFivePosition) position;
                overallDiscount += discountForFivePosition.getDiscountValue();
            }
        }
        return overallDiscount;
    }

    private double calculateFirstOverallTotal(double overallTotal, double overallDiscount) {
        return overallTotal - overallDiscount;
    }

    public String getReceipt() {
        calculateReceipt();
        return receipt.getStringBuilder().toString();
    }

}
