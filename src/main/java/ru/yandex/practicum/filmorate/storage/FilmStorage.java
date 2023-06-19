package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;

public interface FilmStorage {
    List<Film> getFilmList();

    Film addFilm(Film film);

    Film findFilmById(int id);

    void deleteAllFilms();

    Film updateFilm(Film film);

    //исправить замечание с 10го тз
    boolean deleteById(int id);

}
