package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.FilmGenre;

import java.util.List;

public interface FilmGenreStorage {
    void deleteByFilmId(int filmId);

    List<FilmGenre> getLikesFilmId(int filmId);

    List<FilmGenre> findAllFilmGenre();

    FilmGenre add(FilmGenre filmGenre);
}
