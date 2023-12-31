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

    @PostMapping
    public Film addFilm(@Valid @RequestBody Film film) {
        log.info("Добавлен фильм: {}", film);
        return filmService.createFilm(film);
    }

    @GetMapping
    public List<Film> getFilms() {
        return filmService.getAllFilms();
    }

    //лайк фильму
    @PutMapping("/{id}/like/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void setLike(@PathVariable int id, @PathVariable int userId) {
        filmService.addLike(id, userId);
    }

    @DeleteMapping("/{id}/like/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteLike(@PathVariable int id, @PathVariable int userId) {
        filmService.deleteLike(id, userId);
    }

    //возвращает лист топ10 фильмов по колличеству лайков
    @GetMapping("/popular")
    @ResponseStatus(HttpStatus.OK)
    public List<Film> getTopOfFilms(@RequestParam(defaultValue = "10") int count) {
        return filmService.getTopFilms(count);
    }

    @DeleteMapping("/{id}")
    public boolean deleteFilmById(@PathVariable int id) {
        return filmService.deleteFilmById(id);
    }

    @PutMapping
    public Film updateFilm(@Valid @RequestBody Film film) {
        log.info("Обновление информации по фильму: {}", film);
        return filmService.updateFilm(film);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Film findFilmById(@PathVariable int id) {
        return filmService.findFilmById(id);
    }

}
