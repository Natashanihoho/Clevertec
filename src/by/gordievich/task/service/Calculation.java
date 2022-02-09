package by.gordievich.task.service;

import by.gordievich.task.entity.Basket;
import by.gordievich.task.entity.Position;
import by.gordievich.task.entity.Product;
import by.gordievich.task.entity.Receipt;


public class Calculation {

    private Basket basket;
    private Receipt receipt = new Receipt();
    
    public Calculation(Basket basket) {
        this.basket = basket;
    }
    
    public void fillReceipt() {
        double value;
        for (Position position:
             basket.getProductsInBasket()) {
             value = calculatePositionValue(position);
             position.setValue(value);
             receipt.addPosition(position);
        }
    }

    public String getReceipt() {
        return receipt.getStringBuilder().toString();
    }

    public double calculatePositionValue(Position position) {
        Product product = position.getProduct();
        return product.getPrice() * position.getNumber();
    }
}
