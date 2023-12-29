package ru.util.repository.impl;

import ru.entity.Product;
import ru.entity.User;
import ru.util.database.PostgresConnectionProvider;
import ru.util.repository.ProductRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {
    private final List<Product> products = new ArrayList<>();
    private final Connection connection = PostgresConnectionProvider.getConnection();
    private PreparedStatement statement;
    private String query;
    private ResultSet resultSet;

    @Override
    public List<Product> getProductsFromDB() {
        try {
            query = ("SELECT * FROM product");
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            products.clear();
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getLong("id"));
                product.setName(resultSet.getString("name"));
                product.setDescription(resultSet.getString("description"));
                product.setPrice(resultSet.getString("price"));
                product.setIdOfSeller(resultSet.getLong("id_of_author"));
                product.setCategory(resultSet.getString("category"));
                product.setDateOfAdd(resultSet.getDate("date_of_add"));
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return products;
    }

    public List<Product> getFilteredByPriceProductsFromDB() {
        try {
            query = ("SELECT * FROM product ORDER BY price");
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            products.clear();
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getLong("id"));
                product.setName(resultSet.getString("name"));
                product.setDescription(resultSet.getString("description"));
                product.setPrice(resultSet.getString("price"));
                product.setIdOfSeller(resultSet.getLong("id_of_author"));
                product.setCategory(resultSet.getString("category"));
                product.setDateOfAdd(resultSet.getDate("date_of_add"));
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return products;
    }

    public List<Product> getFilteredByPriceDownProductsFromDB() {
        try {
            query = ("SELECT * FROM product ORDER BY price DESC");
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            products.clear();
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getLong("id"));
                product.setName(resultSet.getString("name"));
                product.setDescription(resultSet.getString("description"));
                product.setPrice(resultSet.getString("price"));
                product.setIdOfSeller(resultSet.getLong("id_of_author"));
                product.setCategory(resultSet.getString("category"));
                product.setDateOfAdd(resultSet.getDate("date_of_add"));
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return products;
    }

    public List<Product> getFilteredByDateProductsFromDB() {
        try {
            query = ("SELECT * FROM product ORDER BY date_of_add");
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            products.clear();
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getLong("id"));
                product.setName(resultSet.getString("name"));
                product.setDescription(resultSet.getString("description"));
                product.setPrice(resultSet.getString("price"));
                product.setIdOfSeller(resultSet.getLong("id_of_author"));
                product.setCategory(resultSet.getString("category"));
                product.setDateOfAdd(resultSet.getDate("date_of_add"));
                products.add(product);
            }

        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return products;
    }

    public void addProductToDB(String name, String desc, float price, String city, String category, long id_of_author) {
        try {
            query = "INSERT INTO product (name, description, price, city, category, date_of_add, id_of_author) VALUES (?,?,?,?,?,?,?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, desc);
            statement.setFloat(3, price);
            statement.setString(4, city);
            statement.setString(5, category);
            statement.setDate(6, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
            statement.setLong(7, id_of_author);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Product> getProductsOfUserFromDB(long idOfUser) {
        try {
            query = ("SELECT * FROM product WHERE id_of_author=?");
            statement = connection.prepareStatement(query);
            statement.setLong(1, idOfUser);
            resultSet = statement.executeQuery();
            products.clear();
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getLong("id"));
                product.setName(resultSet.getString("name"));
                product.setDescription(resultSet.getString("description"));
                product.setPrice(resultSet.getString("price"));
                product.setIdOfSeller(resultSet.getLong("id_of_author"));
                product.setCategory(resultSet.getString("category"));
                product.setDateOfAdd(resultSet.getDate("date_of_add"));
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    public void addToBasket(User user, String productName) {
        try {
            query = "(SELECT * FROM product WHERE name=?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, productName);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String query_one = "INSERT INTO user_basket (id_of_user, id_of_product) VALUES (?,?)";
                statement = connection.prepareStatement(query_one);
                statement.setLong(1, user.getId());
                statement.setLong(2, resultSet.getLong("id"));
                statement.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Product> getProductsBasketFromDB(long idOfUser) {
        try {
            query = ("SELECT * FROM product p WHERE EXISTS (SELECT 1 from user_basket b where b.id_of_user = ? AND p.id = b.id_of_product)");
            statement = connection.prepareStatement(query);
            statement.setLong(1, idOfUser);
            resultSet = statement.executeQuery();
            products.clear();
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getLong("id"));
                product.setName(resultSet.getString("name"));
                product.setDescription(resultSet.getString("description"));
                product.setPrice(resultSet.getString("price"));
                product.setIdOfSeller(resultSet.getLong("id_of_author"));
                product.setCategory(resultSet.getString("category"));
                product.setDateOfAdd(resultSet.getDate("date_of_add"));
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    public List<Product> getProductsWithCategoryFromDB(String category) {
        try {
            query = ("SELECT * FROM product WHERE category=?");
            statement = connection.prepareStatement(query);
            statement.setString(1, category);
            resultSet = statement.executeQuery();
            products.clear();
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getLong("id"));
                product.setName(resultSet.getString("name"));
                product.setDescription(resultSet.getString("description"));
                product.setPrice(resultSet.getString("price"));
                product.setIdOfSeller(resultSet.getLong("id_of_author"));
                product.setDateOfAdd(resultSet.getDate("date_of_add"));
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    public void uploadImageToDB(String product, String image) {
        try {
            query = ("INSERT INTO product_image (product, image) VALUES (?,?)");
            statement = connection.prepareStatement(query);
            statement.setString(1, product);
            statement.setString(2, image);
            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String loadImageFromDB(String product) {
        String p = null;
        try {
            query = ("SELECT image FROM product_image WHERE product=?");
            statement = connection.prepareStatement(query);
            statement.setString(1, product);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                p = resultSet.getString("image");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return p;
    }

    public void editNameOfProductToDB(long id, String newName) {
        try {
            String q = "UPDATE product_image SET product=? WHERE product=(SELECT name FROM product WHERE id=?)";
            PreparedStatement s = connection.prepareStatement(q);
            s.setString(1, newName);
            s.setLong(2, id);
            s.execute();
            query = ("UPDATE product SET name=? WHERE id=?");
            statement = connection.prepareStatement(query);
            statement.setString(1, newName);
            statement.setLong(2, id);
            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void editDescriptionOfProductToDB(long id, String newDescription) {
        try {
            query = ("UPDATE product SET description=? WHERE id=?");
            statement = connection.prepareStatement(query);
            statement.setString(1, newDescription);
            statement.setLong(2, id);
            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void editCityOfProductToDB(long id, String newCity) {
        try {
            query = ("UPDATE product SET city=? WHERE id=?");
            statement = connection.prepareStatement(query);
            statement.setString(1, newCity);
            statement.setLong(2, id);
            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void editPriceOfProductToDB(long id, String newPrice) {
        long price = Long.parseLong(newPrice);
        try {
            query = ("UPDATE product SET price=? WHERE id=?");
            statement = connection.prepareStatement(query);
            statement.setLong(1, price);
            statement.setLong(2, id);
            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteProductFromDB(long id) {
        try {
            query = ("DELETE FROM product WHERE id=?");
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteBasketFromDB(long id) {
        try {
            query = ("DELETE FROM user_basket WHERE id_of_user=?");
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
