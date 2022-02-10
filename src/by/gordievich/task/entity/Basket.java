package by.gordievich.task.entity;


import java.util.ArrayList;
import java.util.List;

public class Basket {
    private List<Position> basketPositions = new ArrayList<>();
    Store store = Store.getInstance();

    public void putPositionsInBasket(int id, int number) {
        Product product = store.getProduct(id, number);
        if(product != null) {
            if(product.isDiscount() && number >= 5) {
                basketPositions.add(new DiscountForFivePosition(product, number));
            } else {
                basketPositions.add(new Position(product, number));
            }
        } else {
            System.out.println("Id = " + id + " doesn't exist!");
        }
    }

    public List<Position> getPositionsFromBasket() {
        return basketPositions;
    }
}
