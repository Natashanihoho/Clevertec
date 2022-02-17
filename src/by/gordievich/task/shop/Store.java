package by.gordievich.task.shop;

import by.gordievich.task.entity.Product;
import by.gordievich.task.exceptions.UnknownIdException;

import java.util.List;

public class Store {

    private static Store instance;
    private static List<Product> stockProducts;
    private final List<String> cashiers;

    private Store() {
        cashiers = List.of("Liam Neeson", "Meryl Streep", "Kate Winslet", "Will Smith");
    }

    public void loadProducts(List<Product> stockProducts) {
        this.stockProducts = stockProducts;
    }

    public List<String> getCashiers() {
        return cashiers;
    }

    public static Store getInstance() {
        if (instance == null) {
            instance = new Store();
        }
        return instance;
    }

    public Product getProduct(int id, int requiredNumber) throws UnknownIdException {
        Product product = stockProducts.stream()
                .filter(x -> x.getId() == id)
                .findFirst()
                .orElseGet(null);
        if (product == null) {
                throw new UnknownIdException("Product isn't found");
        }
        if (product.getAvailableNumber() < requiredNumber) return null;
        product.setAvailableNumber(product.getAvailableNumber() - requiredNumber);
        return product;
    }
}
