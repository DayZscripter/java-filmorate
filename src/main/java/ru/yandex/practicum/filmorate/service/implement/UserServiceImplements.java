package ru.yandex.practicum.filmorate.service.implement;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service    //класс бизнесс-логики добавления,обновления,получения пользователей! +валидация
public class UserServiceImplements implements UserService {

    private static int id;
    private final Map<Integer, User> users = new HashMap<>();

    private int generateUserId() {
        return ++id;
    }

    @Override
    public User addUser(User user) {
        validateUser(user);
        user.setId(generateUserId());
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public User updateUser(User user) {
        if (users.containsKey(user.getId())) {
            validateUser(user);
            users.put(user.getId(), user);
            return user;
        } else {
            //log.error("ERROR: ID введен неверно - такого пользователя не существует!");
            throw new ValidationException("Такого пользователя нет.");
        }
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    private void validateUser(User user) {
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            //log.error("ERROR: Поле Email не может быть пустым!");
            throw new ValidationException("электронная почта не может быть пустой.");
        }
        if (!user.getEmail().contains("@")) {
            //log.error("ERROR: Поле Email должно содержать символ @");
            throw new ValidationException("электронная почта должна содержать символ @");
        }
        if (user.getLogin() == null || user.getLogin().isEmpty()) {
            //log.error("ERROR: Поле Login не может быть пустым!");
            throw new ValidationException("логин не может быть пустым.");
        }
        if (user.getLogin().contains(" ")) {
            //log.error("ERROR: Поле Login не может содержать пробелы!");
            throw new ValidationException("логин не может содержать пробелы.");
        }
        if (user.getName() == null || user.getName().isEmpty() || user.getName().isBlank()) {
            user.setName(user.getLogin());
        }
        if (user.getBirthday().isAfter(LocalDate.now())) {
            //log.error("ERROR: Поле Birthday не может быть в будущем!");
            throw new ValidationException("дата рождения не может быть в будущем.");
        }
    }
}
