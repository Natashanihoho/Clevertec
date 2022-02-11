package by.gordievich.task;

import by.gordievich.task.service.ReceiptService;
import by.gordievich.task.shop.Store;


public class CheckRunner {

    public static void main(String[] args) {

        ReceiptService receiptService = new ReceiptService();

        System.out.println(receiptService.generateReceipt(args));
    }

}
