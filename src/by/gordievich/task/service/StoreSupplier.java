package by.gordievich.task.service;

public interface StoreSupplier<T> {
    void loadProducts(T obj);
}
