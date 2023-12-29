package ru.util.repository;

import ru.entity.Product;
import ru.entity.User;

import java.util.List;

public interface ProductRepository {
    List<Product> getProductsFromDB();
    List<Product> getFilteredByPriceProductsFromDB();
    List<Product> getFilteredByPriceDownProductsFromDB();
    List<Product> getFilteredByDateProductsFromDB();
    void addProductToDB(String name, String desc, float price, String city, String category, long id_of_author);
    List<Product> getProductsOfUserFromDB(long idOfUser);
    void addToBasket(User user, String productName);
    List<Product> getProductsBasketFromDB(long idOfUser);
    List<Product> getProductsWithCategoryFromDB(String category);
    void uploadImageToDB(String product, String image);
    String loadImageFromDB(String product);
    void editNameOfProductToDB(long id, String newName);
    void editDescriptionOfProductToDB(long id, String newDescription);
    void editCityOfProductToDB(long id, String newCity);
    void editPriceOfProductToDB(long id, String newPrice);
    void deleteProductFromDB(long id);
    void deleteBasketFromDB(long id);
}
