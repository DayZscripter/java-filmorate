package ru.yandex.practicum.filmorate.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/films")
@Slf4j
@AllArgsConstructor
public class FilmController {
    private final FilmService filmService;

    @PutMapping("/{id}/like/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void leaveLike(@PathVariable int id, @PathVariable int userId) {    // ставим like фильму
        log.debug("Получен PUT-запрос на добавление лайка у фильма с id: {}", id);
        filmService.addLike(id, userId);
    }

    @GetMapping("/popular")
    @ResponseStatus(HttpStatus.OK)
    public List<Film> getTopFilms(@RequestParam(defaultValue = "10") int count) { //return список топ10 фильмов по like
        log.debug("Получен GET-запрос на получение топ-10 фильмов");
        return filmService.getTopFilms(count);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Film findFilmById(@PathVariable int id) {    //
        log.debug("Получен GET-запрос на получение фильма с id: {}.", id);
        return filmService.findFilmById(id);
    }

    @DeleteMapping("/{id}/like/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void removeLike(@PathVariable int id, @PathVariable int userId) {    //удаляем лике
        log.debug("Получен DELETE-запрос на удаление лайка у фильма с id: {}", id);
        filmService.deleteLike(id, userId);
    }

    @PostMapping
    public Film addFilm(@Valid @RequestBody Film film) {
        log.debug("Получен POST-запрос на создание фильма: {}", film);
        return filmService.addFilm(film);
    }

    @PutMapping
    public Film updateFilm(@Valid @RequestBody Film film) {
        log.debug("Получен PUT-запрос на обновление фильма: {}", film);
        return filmService.updateFilm(film);
    }

    @GetMapping
    public List<Film> getFilms() {
        log.info("Получен GET-запрос на получение всех фильмов");
        return filmService.getAllFilms();
    }

}