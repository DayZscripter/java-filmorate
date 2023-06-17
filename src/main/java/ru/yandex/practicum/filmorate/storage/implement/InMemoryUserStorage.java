package ru.yandex.practicum.filmorate.storage.implement;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.ObjectNotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.util.*;

@Component
@Slf4j
public class InMemoryUserStorage implements UserStorage {

    private final Map<Integer, User> users = new HashMap<>();
    private static int id;


    @Override
    public User save(User user) {
        return users.put(user.getId(), user);
    }

    @Override
    public User addUser(User user) {
        user.setId(generateUserId());
        return users.put(user.getId(), user);
    }

    @Override
    public User findUserById(int id) {
        if (!users.containsKey(id)) {
            throw new ObjectNotFoundException(String.format("не найден пользователь с id %d", id));
        }
        return users.get(id);
    }

    @Override
    public List<User> getUserList() {
        return new ArrayList<>(users.values());
    }

    @Override
    public Set<Integer> getAllId() {
        return users.keySet();
    }

    private int generateUserId() {
        return ++id;
    }

    @Override
    public void deleteAllUsers() {
        users.clear();
    }
}