package by.gordievich.task;

import by.gordievich.task.exceptions.UnknownIdException;
import by.gordievich.task.service.Interpreter;
import by.gordievich.task.service.InterpreterImpl;
import by.gordievich.task.service.ReceiptService;
import by.gordievich.task.service.StoreFactory;
import by.gordievich.task.shop.Store;

import java.io.IOException;


public class ConsoleRunner {

    public static void main(String[] args) throws UnknownIdException {
        Store store = StoreFactory.consoleStore();
        Interpreter interpreter = new InterpreterImpl();
        String receipt = interpreter.interpret(args);


        ReceiptService receiptService = receipt1 -> System.out.println(receipt1);

        receiptService.writeReceipt(receipt);
    }
}
