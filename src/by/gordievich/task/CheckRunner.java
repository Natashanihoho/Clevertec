package by.gordievich.task;

import by.gordievich.task.entity.Basket;
import by.gordievich.task.service.Calculation;


public class CheckRunner {

    public static void main(String[] args) {

        Basket basket = new Basket();

        for (int i = 0; i < args.length; i++) {
            String[] separArgs = args[i].split("-");
            int id = Integer.parseInt(separArgs[0]);
            int number = Integer.parseInt(separArgs[1]);
            basket.putProductsInBasket(id, number);
        }

        Calculation calculation = new Calculation(basket);
        calculation.fillReceipt();
        System.out.println(calculation.getReceipt());

    }

}
