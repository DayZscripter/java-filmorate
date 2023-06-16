package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.controller.FilmController;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.implement.FilmServiceImplements;
import ru.yandex.practicum.filmorate.storage.FilmStorage;
import ru.yandex.practicum.filmorate.storage.UserStorage;
import ru.yandex.practicum.filmorate.storage.implement.InMemoryFilmStorage;
import ru.yandex.practicum.filmorate.storage.implement.InMemoryUserStorage;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class FilmTests extends FilmorateApplicationTests {

    private static FilmController filmController;
    private static Film testFilm1;

    @BeforeAll
    public static void init() {
        FilmStorage testFilmStorage = new InMemoryFilmStorage();
        UserStorage testUserStorage = new InMemoryUserStorage();
        filmController = new FilmController(new FilmServiceImplements(testFilmStorage, testUserStorage));
    }

    @BeforeEach
    void beforeEach() {

        testFilm1 = Film.builder()
                .name("Хищник")
                .description("фантастический боевик")
                .duration(107)
                .releaseDate(LocalDate.of(1987, 6, 13))
                .build();
    }

    @Test
    void shouldCreateFilmTest() {
        assertEquals(testFilm1, filmController.addFilm(testFilm1));
        assertNotNull(filmController.getFilms());
        assertTrue(filmController.getFilms().contains(testFilm1));
    }

    @Test
    void shouldUpdateFilmTest() {
        Film expected1 = testFilm1;
        Film actual1 = filmController.addFilm(expected1);
        actual1.setId(testFilm1.getId());
        actual1.setReleaseDate(LocalDate.of(2001, 1, 1));
        actual1.setName("Блейд");
        Film update = filmController.updateFilm(actual1);
        assertNotNull(update, "ОШИБКА ТЕСТА: фильм не обновлен");
        assertEquals(actual1, update, "ОШИБКА ТЕСТА: фильмы не равны");
    }

    @Test
    void shouldThrowReleaseDateTest() {
        testFilm1.setReleaseDate(LocalDate.of(1893, 5, 5));
        Exception exception = assertThrows(ValidationException.class, () -> filmController.addFilm(testFilm1));
        assertEquals("Дата релиза не может быть раньше 1895-12-28", exception.getMessage());
    }

    @Test
    void shouldThrowDurationNegativeTest() {
        testFilm1.setDuration(-13);
        assertThrows(ValidationException.class, () -> filmController.addFilm(testFilm1));
    }

    @Test
    void shouldThrowNameIsEmptyTest() {
        testFilm1.setName("");
        Exception exception = assertThrows(ValidationException.class, () -> filmController.addFilm(testFilm1));
        String expectedMessage = "Название не может быть пустым";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldThrowDescriptionMoreThan200Test() {
        testFilm1.setDescription("Американский вертолет был сбит партизанами в Южной Америке. " +
                "Оставшийся в живых экипаж находится в плену. " +
                "ЦРУ США бросает свои лучшие силы для освобождения американских граждан. " +
                "\n" +
                "\n" +
                "— Мне страшно, Пончо.\n" +
                "\n" +
                "— Ерунда! Нет человека, чтоб ты страшился!\n" +
                "\n" +
                "— Что-то там ждёт нас, и это не человек. Мы все умрём." +
                "\n" +
                "\n");
        Exception exception = assertThrows(ValidationException.class, () -> filmController.addFilm(testFilm1));
        assertEquals("Максимальная длина описания 200 символов", exception.getMessage());
    }

}