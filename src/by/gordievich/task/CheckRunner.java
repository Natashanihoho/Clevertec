package by.gordievich.task;

import by.gordievich.task.entity.Basket;
import by.gordievich.task.service.Handling;


public class CheckRunner {

    public static void main(String[] args) {

        Basket basket = new Basket();

        for (int i = 0; i < args.length; i++) {
            String[] separArgs = args[i].split("-");
            int id = Integer.parseInt(separArgs[0]);
            int number = Integer.parseInt(separArgs[1]);
            basket.putPositionsInBasket(id, number);
        }

        Handling handling = new Handling(basket);
        System.out.println(handling.getReceipt());

    }

}
