package ru.util.service;

import ru.entity.User;

public interface UserService {
    void setSeller(User user);
    void addUser(String login, String password, String nickname);
    User getUser(String login, String password);
    User getUserById(long id);
}
