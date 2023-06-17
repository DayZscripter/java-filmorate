package ru.yandex.practicum.filmorate.service;

import ru.yandex.practicum.filmorate.model.User;

import java.util.List;

public interface UserService {
    User addUser(User user); //создаем пользователя

    User updateUser(User user); //запрос на обновление пользователя

    List<User> getAllUsers(); //получение листа всех пользователей

    List<User> getCommonFriends(int userId, int friendId); //список общих друзей пользователя

    List<User> getListOfFriends(int id); //лист друзей пользователя

    Integer addFriend(int userId, int friendId); //добавление в друзья

    void deleteFriendById(int userId, int friendId); //удаление из друзей по айди

    User getUserById(int id); //получить пользователя с айди
}
