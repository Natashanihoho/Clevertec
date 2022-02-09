package by.gordievich.task.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Store {
    private final List<Product> productsInStock = new ArrayList<>(Arrays.asList(
            new Product(1, "Lollipop", 0.87),
            new Product(2, "Popcorn", 2.10),
            new Product(3, "Ice cream", 2.50),
            new Product(4, "Yogurt", 1.50),
            new Product(5, "Biscuit", 1.48),
            new Product(6, "Cake", 18.65),
            new Product(7, "Chocolate", 3.10),
            new Product(8, "Croissant", 1.20),
            new Product(9, "Jam", 3.41),
            new Product(10, "Marshmallow", 1.48),
            new Product(11, "Waffle", 2.63),
            new Product(12, "Brownie", 1.79),
            new Product(13, "Bun", 0.62),
            new Product(14, "Eclair", 1.90),
            new Product(15, "Gum", 0.50),
            new Product(16, "Macaroon", 5.52),
            new Product(17, "Marmalade", 2.70),
            new Product(18, "Lemonade", 1.62),
            new Product(19, "Juice", 3.00),
            new Product(20, "Coke", 2.50)));
}
