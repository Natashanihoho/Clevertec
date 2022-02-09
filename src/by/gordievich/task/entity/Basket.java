package by.gordievich.task.entity;

import java.util.ArrayList;
import java.util.List;

public class Basket {
    private List<Position> productsInBasket = new ArrayList<>();
    Store store = Store.getInstance();

    public void putProductsInBasket(int id, int number) {
        Product product = store.getProduct(id);
        Position position = new Position(product, number);
        if(product != null) {
            productsInBasket.add(position);
            //System.out.println("ADDED: " + product.getName() + " - " + number);
        } else {
            System.out.println("Id = " + id + " doesn't exist!");
        }
        //throw new IdDoesNotExistException("Id = " + id + " doesn't exist!");
    }

    public List<Position> getProductsInBasket() {
        return productsInBasket;
    }
}
