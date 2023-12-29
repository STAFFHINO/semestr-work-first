package ru.util.service.impl;

import ru.entity.Product;
import ru.entity.User;
import ru.util.repository.ProductRepository;
import ru.util.repository.impl.ProductRepositoryImpl;
import ru.util.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository = new ProductRepositoryImpl();

    public List<Product> getProducts() {
        return repository.getProductsFromDB();
    }

    public List<Product> getProductsByPrice() {
        return repository.getFilteredByPriceProductsFromDB();
    }

    public List<Product> getProductsByPriceDown() {
        return repository.getFilteredByPriceDownProductsFromDB();
    }

    public List<Product> getProductsByDate() {
        return repository.getFilteredByDateProductsFromDB();
    }

    public void putProduct(String name, String desc, float price, String city, String category, long id_of_author) {
        repository.addProductToDB(name, desc, price, city, category, id_of_author);
    }

    public List<Product> getProductsOfUser(long id) {
        return repository.getProductsOfUserFromDB(id);
    }

    public void addProductToBasket(User user, String productName) {
        repository.addToBasket(user, productName);
    }

    public List<Product> getBasketOfUser(long id) {
        return repository.getProductsBasketFromDB(id);
    }

    public List<Product> getProductsWithCategory(String category) {
        return repository.getProductsWithCategoryFromDB(category);
    }

    public void uploadImage(String product, String image) {
        repository.uploadImageToDB(product, image);
    }

    public String loadImage(String product) {
        return repository.loadImageFromDB(product);
    }

    public void editNameOfProduct(long id, String newName) {
        repository.editNameOfProductToDB(id, newName);
    }

    public void editDescriptionOfProduct(long id, String newDescription) {
        repository.editDescriptionOfProductToDB(id, newDescription);
    }

    public void editCityOfProduct(long id, String newCity) {
        repository.editCityOfProductToDB(id, newCity);
    }

    public void editPriceOfProduct(long id, String newPrice) {
        repository.editPriceOfProductToDB(id, newPrice);
    }

    public void deleteProduct(long id) {
        repository.deleteProductFromDB(id);
    }

    public void deleteBasket(long idOfUser) {
        repository.deleteBasketFromDB(idOfUser);
    }
}
