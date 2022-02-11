package by.gordievich.task.service;

import by.gordievich.task.entity.Position;
import by.gordievich.task.entity.Product;
import by.gordievich.task.shop.DiscountCard;
import by.gordievich.task.shop.Store;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class ReceiptService implements CustomerService {
    private List<Position> positions = new ArrayList<>();
    private final Store store = Store.getInstance();
    private StringBuilder stringBuilder = new StringBuilder();
    private double fullPriceSum = 0.0;
    private DiscountCard discountCard;

    @Override
    public String generateReceipt(String[] args){
        buildReceiptTitle(store.getCashiers());
        addPositionsToList(args);
        calculateEachPosition();
        calculateOverallTotal();
        return stringBuilder.toString();
    }

    @Override
    public void buildReceiptTitle(List<String> cashiers){
        String randomCashier = cashiers.get(new Random().nextInt(cashiers.size()));
        String currentDate = LocalDate.now().toString();
        String currentTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        stringBuilder.append(StringFormatter.TITLE.formatted(randomCashier, currentDate, currentTime));
    }

    @Override
    public void addPositionsToList(String[] argsLine) {
        Product product;
        int id;
        int requiredNumber;

        for (String pair : argsLine) {
            if(pair.contains("card")) {
                discountCard = DiscountCard.getDiscountByCard(pair);
                System.out.println(discountCard.name());
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

    @Override
    public void calculateEachPosition() {
        for (Position position:
             positions) {
            Product product = position.getProduct();
            double fullPrice = product.getPrice() * position.getRequiredNumber();
            fullPriceSum += fullPrice;
            if(product.isDiscount()){
                if(position.getRequiredNumber() >= 5){
                    position.setDiscount(10);
                    position.setTotal(fullPrice * (1 - position.getDiscount() / 100.));
                    stringBuilder.append(StringFormatter.DISCOUNT_FIELD.formatted(product.getId(), product.getDescription(), product.getPrice(),
                            position.getRequiredNumber(), fullPrice, position.getTotal() - fullPrice, position.getTotal()));
                } else{
                    if(discountCard == DiscountCard.CARD_NOT_DEFINED) {
                        position.setTotal(fullPrice);
                        stringBuilder.append(StringFormatter.NORMAL_FIELD.formatted(product.getId(), product.getDescription(), product.getPrice(),
                                position.getRequiredNumber(), position.getTotal()));
                    } else{
                        position.setDiscount(discountCard.getDiscount());
                        position.setTotal(fullPrice * (1 - discountCard.getDiscount() / 100.));
                        stringBuilder.append(StringFormatter.DISCOUNT_FIELD.formatted(product.getId(), product.getDescription(), product.getPrice(),
                                position.getRequiredNumber(), fullPrice, position.getTotal() - fullPrice, position.getTotal()));
                    }

                }
            } else {
                if(discountCard == DiscountCard.CARD_NOT_DEFINED) {
                    position.setTotal(fullPrice);
                    stringBuilder.append(StringFormatter.NORMAL_FIELD.formatted(product.getId(), product.getDescription(), product.getPrice(),
                            position.getRequiredNumber(), position.getTotal()));
                } else{
                    position.setDiscount(discountCard.getDiscount());
                    position.setTotal(fullPrice * (1 - discountCard.getDiscount() / 100.));
                    stringBuilder.append(StringFormatter.DISCOUNT_FIELD.formatted(product.getId(), product.getDescription(), product.getPrice(),
                            position.getRequiredNumber(), fullPrice, position.getTotal() - fullPrice, position.getTotal()));
                }
            }
        }
    }

    @Override
    public void calculateOverallTotal(){
        double overallTotal = positions.stream()
                .map(x -> x.getTotal())
                .reduce(Double::sum).get();
       stringBuilder.append(StringFormatter.COMPLETION.formatted(fullPriceSum, overallTotal - fullPriceSum, overallTotal));
    }

}
