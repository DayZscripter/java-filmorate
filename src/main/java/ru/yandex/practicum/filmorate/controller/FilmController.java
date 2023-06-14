package ru.yandex.practicum.filmorate.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;

import java.util.List;

@RestController
@RequestMapping("/films")
@AllArgsConstructor
public class FilmController {    //класс обслуживающий фильмы
    private final FilmService filmService;

    @PostMapping
    public Film addFilm(@RequestBody Film film) {
        return filmService.addFilm(film);
    }
    @PutMapping
    public Film updateFilm (@RequestBody Film film) {
        return filmService.updateFilm(film);
    }
    @GetMapping
    public List<Film> getFilms() {
        return filmService.getAlFilms();
    }
}
