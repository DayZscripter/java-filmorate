package ru.yandex.practicum.filmorate.service.implement;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.ObjectNotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.FilmService;
import ru.yandex.practicum.filmorate.storage.FilmStorage;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static ru.yandex.practicum.filmorate.service.implement.ValidatationService.validateFilm;

@Slf4j
@Service
public class FilmServiceImplements implements FilmService { //класс бизнес-логики хранения/получения/удаления фильмов
    private final FilmStorage filmStorage;
    private final UserStorage userStorage;

    @Autowired
    public FilmServiceImplements(FilmStorage filmStorage, UserStorage userStorage) {
        this.filmStorage = filmStorage;
        this.userStorage = userStorage;
    }

    @Override
    public Film addFilm(Film film) {
        validateFilm(film);
        filmStorage.addFilm(film);
        return film;
    }

    @Override
    public Film updateFilm(Film film) {
        if (filmStorage.getAllId().contains(film.getId())) {
            validateFilm(film);
            filmStorage.save(film);
        } else {
            log.error("id введен неверно или такого фильма не существует!");
            throw new ObjectNotFoundException("Такого фильма не существует!");
        }
        return film;

    }

    @Override
    public Film findFilmById(int id) {
        return filmStorage.findFilmById(id);
    }

    @Override
    public List<Film> getAllFilms() {
        return new ArrayList<>(filmStorage.getFilmList());
    }

    @Override
    public void addLike(int userId, int filmId) {
        User user = userStorage.findUserById(userId);
        Film film = filmStorage.findFilmById(filmId);
        film.addLike(user);
    }

    @Override
    public void deleteLike(int userId, int filmId) {
        User user = userStorage.findUserById(userId);
        Film film = filmStorage.findFilmById(filmId);
        film.deleteLike(user);
    }

    @Override
    public List<Film> getTopFilms(int count) {
        return filmStorage.getFilmList().stream().sorted(Comparator.comparingInt(film -> film.getLikes().size()))
                .limit(count)
                .collect(Collectors.toList());

    }
}