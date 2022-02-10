package by.gordievich.task.entity;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Receipt {

    private final List<String> cashiers = new ArrayList<>(Arrays.asList("Liam Neeson", "Meryl Streep", "Kate Winslet", "Will Smith"));
    private StringBuilder stringBuilder = new StringBuilder();

    public Receipt() {
        initialization();
    }

    private void initialization() {
        stringBuilder.append(String.format("               CASH RECEIPT              \n"));
        stringBuilder.append(String.format("          SUPERMARKET \"LOLLIPOP\"         \n"));
        stringBuilder.append(String.format("  17100 Collins Ave Sunny Isles Beach, \n               FL 33160\n"));
        stringBuilder.append(String.format("           Tel: 37529-234-6375\n\n"));
        stringBuilder.append(String.format("Cashier: %-16s%s%s\n", cashiers.get(new Random().nextInt(cashiers.size())), "DATE: ", LocalDate.now().toString()));
        stringBuilder.append(String.format("%31s%s\n",  "TIME: ", LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))));
        stringBuilder.append(String.format("-----------------------------------------\n"));
        stringBuilder.append(String.format("ID | DESCRIPTION | PRICE | NUMBER | TOTAL\n"));
        stringBuilder.append(String.format("-----------------------------------------\n"));
    }

    public void addPosition(Position position) {
        stringBuilder.append(position.toString());
        stringBuilder.append("\n");
    }

    public void sumUp(double firstOverallTotal, double overallDiscount, double overallTotal) {
        stringBuilder.append(String.format("-----------------------------------------\n"));
        stringBuilder.append(String.format("-----------------------------------------\n"));
        stringBuilder.append(String.format("Total%36.2f\n", firstOverallTotal));
        stringBuilder.append(String.format("Discount%33.2f\n", overallDiscount));
        stringBuilder.append(String.format("                                    -----\n"));
        stringBuilder.append(String.format("TO PAY%35.2f\n", overallTotal));
        stringBuilder.append(String.format("           ---THANK YOU!---                "));
    }

    public StringBuilder getStringBuilder() {
        return stringBuilder;
    }
}
