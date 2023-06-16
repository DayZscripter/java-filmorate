package ru.yandex.practicum.filmorate.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@Valid @RequestBody User user) {
        log.debug("Получен POST-запрос на добавление пользователя: {}", user);
        return userService.addUser(user);
    }

    @PutMapping("/{id}/friends/{friendId}")
    @ResponseStatus(HttpStatus.OK)
    public void addFriends(@PathVariable int id, @PathVariable int friendId) { // добавляет в друзья
        log.debug("Получен PUT-запрос на добавление в друзья пользователя с id: {}", friendId);
        userService.addFriend(id, friendId);
    }

    @DeleteMapping("/{id}/friends/{friendId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFriend(@PathVariable int id, @PathVariable int friendId) { //удаляет из друзей
        log.debug("Получен DELETE-запрос на удаление из друзей пользователя с id: {}", friendId);
        userService.deleteFriendById(id, friendId);
    }

    @GetMapping("/{id}/friends/common/{otherId}")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getListOfCommonFriends(@PathVariable int id, @PathVariable int otherId) {
        log.debug("Получен GET-запрос на получение списка общих друзей пользователя с id: {} - и пользователя с id: {}",
                id, otherId);
        return userService.getCommonFriends(id, otherId);
    }

    @GetMapping("/{id}/friends")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getFriendList(@PathVariable int id) {
        log.debug("Получен GET-запрос на получение списка друзей пользователя с id: {}", id);
        return userService.getListOfFriends(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public User updateUser(@Valid @RequestBody User user) {
        log.debug("Получен PUT-запрос на обновление пользователяс: {}", user);
        return userService.updateUser(user);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        log.debug("Получен GET-запрос на получение пользователя с id: {}", id);
        return userService.getUserById(id);
    }
}