package ru.yandex.practicum.filmorate.service;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;

public interface FilmService {

    Film addFilm(Film film); //добавление фильма

    Film updateFilm(Film film); //обновление фильма

    List<Film> getAllFilms(); //получение всех фильмов

    List<Film> getTopFilms(int count); //получаем топ 10 фильмов

    void addLike(int userId, int filmId); // добавление лайка

    void deleteLike(int userId, int filmId); //убрать лайк

    Film findFilmById(int id); //поиск фильма по айди
}
