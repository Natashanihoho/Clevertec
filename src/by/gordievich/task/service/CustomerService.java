package by.gordievich.task.service;

import by.gordievich.task.entity.Position;

import java.util.List;

public interface CustomerService {

    void addPositionsToList(String[] args);
    void buildReceiptTitle(List<String> cashiers);
    void calculateEachPosition();
    void calculateOverallTotal();
    String generateReceipt(String[] args);
}
