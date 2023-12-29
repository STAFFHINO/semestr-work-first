package ru.util.service;

import ru.entity.Product;
import ru.entity.User;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();
    List<Product> getProductsByPrice();
    List<Product> getProductsByPriceDown();
    List<Product> getProductsByDate();
    void putProduct(String name, String desc, float price, String city, String category, long id_of_author);
    List<Product> getProductsOfUser(long id);
    void addProductToBasket(User user, String productName);
    List<Product> getBasketOfUser(long id);
    List<Product> getProductsWithCategory(String category);
    void uploadImage(String product, String image);
    String loadImage(String product);
    void editNameOfProduct(long id, String newName);
    void editDescriptionOfProduct(long id, String newDescription);
    void editCityOfProduct(long id, String newCity);
    void editPriceOfProduct(long id, String newPrice);
    void deleteProduct(long id);
    void deleteBasket(long idOfUser);
}
