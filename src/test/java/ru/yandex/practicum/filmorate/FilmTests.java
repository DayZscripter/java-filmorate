package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.controller.FilmController;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.implement.FilmServiceImplements;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class FilmTests extends FilmorateApplicationTests {

    private static FilmController filmController;
    private static Film film1;
    private static Film film2;
    private static Film film3;
    private static Film film4;

    @BeforeAll
    public static void init() {
        filmController = new FilmController(new FilmServiceImplements());
    }

    @BeforeEach
    void BeforeEach() {
        film1 = Film.builder()
                .name("Хищник 2")
                .description("фантастический боевик")
                .duration(123)
                .releaseDate(LocalDate.of(1990, 8, 10))
                .build();
        film2 = Film.builder()
                .name("Кровавое месиво")
                .description("ужасы")
                .duration(120)
                .releaseDate(LocalDate.of(1989, 1, 8))
                .build();
        film3 = Film.builder()
                .name("Хищник")
                .description("фантастический боевик")
                .duration(107)
                .releaseDate(LocalDate.of(1987, 6, 13))
                .build();
        film4 = Film.builder()
                .name("Блейд")
                .description("фантастический боевик")
                .duration(134)
                .releaseDate(LocalDate.of(1999, 7, 3))
                .build();
    }


    @Test
    void shouldCreateFilmTest() {
        assertEquals(film3, filmController.addFilm(film3));
        assertNotNull(filmController.getFilms());
        assertTrue(filmController.getFilms().contains(film3));
    }

    @Test
    void shouldUpdateFilmTest() {
        Film expected1 = film3;
        Film actual1 = filmController.addFilm(expected1);
        actual1.setId(film3.getId());
        actual1.setReleaseDate(LocalDate.of(2001, 1, 1));
        actual1.setName("Блейд");
        Film update = filmController.updateFilm(actual1);
        assertNotNull(update, "ОШИБКА ТЕСТА: фильм не обновлен");
        assertEquals(actual1, update, "ОШИБКА ТЕСТА: фильмы не равны");
    }

    @Test
    void shouldThrowReleaseDateTest() {
        film3.setReleaseDate(LocalDate.of(1893, 5, 5));
        Exception exception = assertThrows(ValidationException.class, () -> filmController.addFilm(film3));
        assertEquals("Дата релиза не может быть раньше 1895-12-28", exception.getMessage());
    }

    @Test
    void shouldThrowDurationNegativeTest() {
        film3.setDuration(-13);
        assertThrows(ValidationException.class, () -> filmController.addFilm(film3));
    }

    @Test
    void shouldThrowNameIsEmptyTest() {
        film3.setName("");
        Exception exception = assertThrows(ValidationException.class, () -> filmController.addFilm(film3));
        String expectedMessage = "Название не может быть пустым";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldThrowDescriptionMoreThan200Test() {
        film3.setDescription("Американский вертолет был сбит партизанами в Южной Америке. " +
                "Оставшийся в живых экипаж находится в плену. " +
                "ЦРУ США бросает свои лучшие силы для освобождения американских граждан. " +
                "ЦРУ США бросает свои лучшие силы для освобождения американских граждан.");
        Exception exception = assertThrows(ValidationException.class, () -> filmController.addFilm(film3));
        assertEquals("Максимальная длина описания — 200 символов", exception.getMessage());
    }

}