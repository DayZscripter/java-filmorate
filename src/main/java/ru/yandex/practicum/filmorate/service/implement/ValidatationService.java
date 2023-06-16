package ru.yandex.practicum.filmorate.service.implement;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;

@Slf4j
public final class ValidatationService {

    private static final LocalDate date = LocalDate.of(1895, 12, 28);

    private ValidatationService() {

    }


    public static void validateUser(User user) {
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new ValidationException("поле электронная почта не может быть пустой.");
        }
        if (!user.getEmail().contains("@")) {
            throw new ValidationException("электронная почта должна содержать символ @");
        }
        if (user.getLogin() == null || user.getLogin().isEmpty()) {
            throw new ValidationException("логин не может быть пустым.");
        }
        if (user.getLogin().contains(" ")) {
            log.error("пробелы в логине!");
            throw new ValidationException("логин не может содержать пробелы");
        }
        if (user.getBirthday().isAfter(LocalDate.now())) {
            throw new ValidationException("дата рождения не может быть указана в будущем времени");
        }
        if (user.getName() == null || user.getName().isEmpty() || user.getName().isBlank()) {
            user.setName(user.getLogin());
        }
    }


    public static void validateFilm(Film film) {

        if (film.getName() == null || film.getName().isEmpty()) {
            throw new ValidationException("Название не может быть пустым");
        }
        if (film.getDescription().length() > 200) {
            throw new ValidationException("Максимальная длина описания 200 символов");
        }
        if (film.getReleaseDate().isBefore(date)) {
            throw new ValidationException("Дата релиза не может быть раньше " + date);
        }
        if (film.getDuration() < 0) {
            throw new ValidationException("Продолжительность фильма должна быть положительной!");
        }

    }


}