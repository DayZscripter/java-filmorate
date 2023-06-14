package ru.yandex.practicum.filmorate.service.implement;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service    //класс бизнесс-логики добавления,обновления,получения фильмов! +валидация
@Slf4j
public class FilmServiceImplements implements FilmService {

    private final Map<Integer, Film> films = new HashMap<>();
    private static int id;
    private static final LocalDate RELEASE_DATE = LocalDate.of(1895, 12, 28);

    private void validateFilm(Film film) {

        if (film.getName() == null || film.getName().isEmpty()) {
            log.error("поле Name не может быть пустым");
            throw new ValidationException("Название не может быть пустым");
        }
        if (film.getDescription().length() > 200) {
            log.error("поле Description должно содержать до 200 символов");
            throw new ValidationException("Максимальная длина описания — 200 символов");
        }
        if (film.getReleaseDate().isBefore(RELEASE_DATE)) {
            log.error("в поле Release нельзя вносить дату ранее 28.12.1895 года");
            throw new ValidationException("Дата релиза не может быть раньше " + RELEASE_DATE);
        }
        if (film.getDuration() < 0) {
            log.error("в поле Duration время продолжительности фильма должно быть положительным");
            throw new ValidationException("Продолжительность фильма должна быть положительной.");
        }
    }

    private int generateFilmId() {
        return ++id;
    }

    @Override
    public Film addFilm(Film film) {
        validateFilm(film);
        film.setId(generateFilmId());
        films.put(film.getId(), film);
        return film;
    }

    @Override
    public Film updateFilm(Film film) {
        if (films.containsKey(film.getId())) {
            validateFilm(film);
            films.put(film.getId(), film);
        } else {
            log.error("ID введен неверно! Такого фильма нет в базе данных");
            throw new ValidationException("Такого фильма нет :(");
        }
        return film;
    }

    @Override
    public List<Film> getAlFilms() {
        return new ArrayList<>(films.values());
    }
}
