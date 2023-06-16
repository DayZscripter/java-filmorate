package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;
import java.util.Set;

public interface FilmStorage { //хранилище данных о фильмах
    Film addFilm(Film film);

    List<Film> getFilmList();

    Set<Integer> getAllId();

    void deleteAllFilms();

    Film save(Film film);

    Film findFilmById(int id);
}
