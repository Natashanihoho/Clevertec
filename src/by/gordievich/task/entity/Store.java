package by.gordievich.task.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Store {
    private static Store instance;
    private final List<Product> stockProducts = new ArrayList<>(Arrays.asList(
            new Product(1, "Lollipop", 0.87, 20, false),
            new Product(2, "Popcorn", 2.10, 20, true),
            new Product(3, "Ice cream", 2.50, 20, false),
            new Product(4, "Yogurt", 1.50, 20, true),
            new Product(5, "Biscuit", 1.48, 20, true),
            new Product(6, "Cake", 18.65, 20, false),
            new Product(7, "Chocolate", 3.10, 20, false),
            new Product(8, "Croissant", 1.20, 20, false),
            new Product(9, "Jam", 3.41, 20, false),
            new Product(10, "Marshmallow", 1.48, 20, false),
            new Product(11, "Waffle", 2.63, 20, true),
            new Product(12, "Brownie", 1.79, 20, false),
            new Product(13, "Bun", 0.62, 20, true),
            new Product(14, "Eclair", 1.90, 20, false),
            new Product(15, "Gum", 0.50, 20, false),
            new Product(16, "Macaroon", 5.52, 20, false),
            new Product(17, "Marmalade", 2.70, 20, false),
            new Product(18, "Lemonade", 1.62, 20, true),
            new Product(19, "Juice", 3.00, 20, false),
            new Product(20, "Coke", 2.50, 20, false)));

    private Store() {}

    public static Store getInstance() {
        if(instance == null) {
            instance = new Store();
        }
        return instance;
    }

    public Product getProduct(int id, int number) {

        Product product =  stockProducts.stream()
                                            .filter(x -> x.getId() == id)
                                            .findAny()
                                            .orElseGet(null);
        if(product == null) return null;
        if(product.getAvailableNumber() < number) return null;
        product.setAvailableNumber(product.getAvailableNumber() - number);
        return product;
    }
}
