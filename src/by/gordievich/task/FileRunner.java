package by.gordievich.task;

import by.gordievich.task.exceptions.NotEnoughProductsException;
import by.gordievich.task.exceptions.UnknownIdException;
import by.gordievich.task.service.Interpreter;
import by.gordievich.task.service.InterpreterImpl;
import by.gordievich.task.service.ReceiptService;
import by.gordievich.task.service.StoreFactory;
import by.gordievich.task.shop.Store;

import java.io.*;

public class FileRunner {

    private static final String stockProductsFile = "stockProducts.txt";
    private static final String receiptFile = "receipt.txt";
    private static final String orderFile = "args.txt";

    private static String[] fileArgs;

    public static void main(String[] args) throws IOException, UnknownIdException, NotEnoughProductsException {
        Store store = StoreFactory.fileStore(stockProductsFile);

        try(BufferedReader reader = new BufferedReader(new FileReader(orderFile))) {
            String argsLine = reader.readLine();
            fileArgs = argsLine.split(" ");
        }
        Interpreter interpreter = new InterpreterImpl();
        String receipt = interpreter.interpret(fileArgs);

        ReceiptService receiptService = rec -> {
            try(FileWriter fileWriter = new FileWriter(receiptFile)) {
                fileWriter.write(rec);
            }
        };
        receiptService.writeReceipt(receipt);

    }
}
