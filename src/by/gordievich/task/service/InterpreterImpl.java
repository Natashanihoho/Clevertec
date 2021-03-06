package by.gordievich.task.service;

import by.gordievich.task.entity.Position;
import by.gordievich.task.entity.Product;
import by.gordievich.task.exceptions.NotEnoughProductsException;
import by.gordievich.task.exceptions.UnknownIdException;
import by.gordievich.task.shop.DiscountCard;
import by.gordievich.task.shop.Store;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InterpreterImpl implements Interpreter {

    private final Store store = Store.getInstance();
    private final int discountPercentForFive = 10;

    private List<Position> positions = new ArrayList<>();
    private StringBuilder stringBuilder = new StringBuilder();
    private DiscountCard discountCard = DiscountCard.CARD_NOT_DEFINED;

    @Override
    public String interpret(String[] args) throws UnknownIdException, NotEnoughProductsException {
        buildReceiptTitle(store.getCashiers());
        addPositionsToList(args);
        calculateEachPosition();
        calculateOverallTotal();
        return stringBuilder.toString();
    }

    private void buildReceiptTitle(List<String> cashiers) {
        String randomCashier = cashiers.get(new Random().nextInt(cashiers.size()));
        String currentDate = LocalDate.now().toString();
        String currentTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        stringBuilder.append(StringFormatter.TITLE.formatted(randomCashier, currentDate, currentTime));
    }

    private void addPositionsToList(String[] argsLine) throws UnknownIdException, NotEnoughProductsException {
        Product product = null;
        int id;
        int requiredNumber;

        for (String pair : argsLine) {
            if (pair.contains("card")) {
                discountCard = DiscountCard.getDiscountByCard(pair);
                break;
            }
            String[] arg = pair.split("-");
            id = Integer.parseInt(arg[0]);
            requiredNumber = Integer.parseInt(arg[1]);
            product = store.getProduct(id, requiredNumber);

            if (product != null) {
                positions.add(new Position(product, requiredNumber));
            }
        }
    }

    private void calculateEachPosition() {
        for (Position position : positions) {
            Product product = position.getProduct();

            double fullPrice = product.getPrice() * position.getRequiredNumber();
            double discountValue= checkAndGetDiscountValue(position);

            if(discountValue != 0) {
                position.setTotal(fullPrice - discountValue);
                stringBuilder.append(StringFormatter.DISCOUNT_FIELD.formatted(
                        product.getId(),
                        product.getDescription(),
                        product.getPrice(),
                        position.getRequiredNumber(),
                        fullPrice,
                        0 - discountValue));
            } else {
                position.setTotal(fullPrice);
                stringBuilder.append(StringFormatter.NORMAL_FIELD.formatted(
                        product.getId(),
                        product.getDescription(),
                        product.getPrice(),
                        position.getRequiredNumber(),
                        position.getTotal()));
            }
        }
    }

    private double checkAndGetDiscountValue(Position position) {
        Product product = position.getProduct();
        double fullPrice = product.getPrice() * position.getRequiredNumber();
        double discountValue = 0;
        if (product.isSpecialOffer() && position.getRequiredNumber() >= 5) {
            position.setDiscount(fullPrice * (double) discountPercentForFive / 100);
            return position.getDiscount();
        } else if(discountCard != DiscountCard.CARD_NOT_DEFINED) {
            position.setDiscount(fullPrice * (double) discountCard.getDiscount() / 100);
            return position.getDiscount();
        }
        return discountValue;
    }

    private void calculateOverallTotal() {
        double overallTotal = positions.stream()
                .map(x -> x.getTotal())
                .reduce(Double::sum).get();
        double overallDiscount = positions.stream()
                .map(x -> x.getDiscount())
                .reduce(Double::sum).get();
        stringBuilder.append(StringFormatter.COMPLETION.formatted(0 - overallDiscount, overallTotal));
    }

}
