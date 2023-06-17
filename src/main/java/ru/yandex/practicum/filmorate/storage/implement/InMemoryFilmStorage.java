package ru.yandex.practicum.filmorate.storage.implement;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.ObjectNotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.FilmStorage;

import java.util.*;

@Component
@Slf4j
public class InMemoryFilmStorage implements FilmStorage {
    private final Map<Integer, Film> films = new HashMap<>();
    private static int id;

    @Override
    public Film save(Film film) {
        return films.put(film.getId(), film);
    }

    @Override
    public Film addFilm(Film film) {
        film.setId(generateFilmId());
        return films.put(film.getId(), film);
    }

    @Override
    public Film findFilmById(int id) {
        if (!films.containsKey(id)) {
            throw new ObjectNotFoundException(String.format("не найден фильм с id %d", id));
        }
        return films.get(id);
    }

    @Override
    public void deleteAllFilms() {
        films.clear();
    }

    @Override
    public List<Film> getFilmList() {
        log.debug("найдены все фильмы");
        return new ArrayList<>(films.values());
    }

    public int generateFilmId() {
        return ++id;
    }

    public Set<Integer> getAllId() {
        return films.keySet();
    }
}
