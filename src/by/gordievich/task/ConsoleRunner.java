package by.gordievich.task;

import by.gordievich.task.exceptions.UnknownIdException;
import by.gordievich.task.service.Interpreter;
import by.gordievich.task.service.InterpreterImpl;
import by.gordievich.task.service.ReceiptService;

import java.io.IOException;


public class ConsoleRunner {

    public static void main(String[] args) throws IOException, UnknownIdException {
        Interpreter interpreter = new InterpreterImpl();
        String receipt = interpreter.interpret(args);

        ReceiptService receiptService = new ReceiptService() {
            @Override
            public void writeReceipt(String receipt) {
                System.out.println(receipt);
            }
        };

        receiptService.writeReceipt(receipt);
    }
}
