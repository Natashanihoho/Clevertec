package by.gordievich.task;

import by.gordievich.task.service.Interpreter;
import by.gordievich.task.service.InterpreterImpl;
import by.gordievich.task.service.ReceiptService;

import java.io.FileWriter;
import java.io.IOException;

public class FileRunner {
    public static void main(String[] args) throws IOException {
        Interpreter interpreter = new InterpreterImpl();
        String receipt = interpreter.interpret(args);


        ReceiptService receiptService = new ReceiptService() {
            @Override
            public void writeReceipt(String receipt) throws IOException {
                try(FileWriter fileWriter = new FileWriter("receipt.jpeg")) {
                    fileWriter.write(receipt);
                }
            }
        };

        receiptService.writeReceipt(receipt);
    }
}
