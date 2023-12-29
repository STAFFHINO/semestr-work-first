package ru.util.repository.impl;

import ru.entity.User;
import ru.util.database.PostgresConnectionProvider;
import ru.util.repository.UserRepository;
import ru.util.service.Encryptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepositoryImpl implements UserRepository {

    private final String SALT = "Ykn7n7km7tKmvt77";
    private final Connection connection = PostgresConnectionProvider.getConnection();
    private String query;
    private PreparedStatement statement;

    public void setSellerToDB(User user) {
        try {
            query = "UPDATE public.\"user\" SET role = 'seller' WHERE login = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, user.getLogin());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addUserToDB(String login, String password, String nickname) {
        try {
            String pass = Encryptor.hashPassword(password, SALT);
            query = "INSERT INTO public.user (login, password,role,nickname) VALUES (?,?,?,?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, login);
            statement.setString(2, pass);
            statement.setString(3, "authorised");
            statement.setString(4, nickname);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User getUserFromDB(String login, String password) {
        try {
            String pass = Encryptor.hashPassword(password, SALT);
            query = "SELECT * FROM public.\"user\" WHERE login=? AND password=?";
            statement = connection.prepareStatement(query);
            statement.setString(1, login);
            statement.setString(2, pass);
            ResultSet resultSet = statement.executeQuery();
            User user = new User();
            while (resultSet.next()) {
                user.setId(resultSet.getLong("id"));
                user.setNickname(resultSet.getString("nickname"));
                user.setLogin(resultSet.getString("login"));
                user.setRole(resultSet.getString("role"));
            }
            if (user.getLogin() == null) {
                return null;
            } else {
                return user;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User getUserByIdFromDB(long id) {
        try {
            query = "SELECT * FROM public.\"user\" WHERE id=?";
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            User user = new User();
            while (resultSet.next()) {
                user.setId(resultSet.getLong("id"));
                user.setNickname(resultSet.getString("nickname"));
                user.setLogin(resultSet.getString("login"));
                user.setRole(resultSet.getString("role"));
            }
            if (user.getLogin() == null) {
                return null;
            } else {
                return user;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
