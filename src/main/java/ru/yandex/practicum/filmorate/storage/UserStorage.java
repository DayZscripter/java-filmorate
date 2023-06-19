package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.User;

import java.util.List;

public interface UserStorage {
    User findUserById(int id);

    List<User> getUserList();

    void deleteAllUsers();

    User addUser(User user);

    //правки замечаний с 10го тз

    User updateUser(User user);

    boolean deleteById(int id);

}
