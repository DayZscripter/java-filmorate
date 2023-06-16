package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.User;

import java.util.List;
import java.util.Set;

public interface UserStorage {
    User addUser(User user);

    List<User> getUserList();

    Set<Integer> getAllId();

    void deleteAllUsers();

    User save(User user);

    User findUserById(int id);
}
