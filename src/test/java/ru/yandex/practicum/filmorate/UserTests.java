package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.controller.UserController;
import ru.yandex.practicum.filmorate.exception.ObjectNotFoundException;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.implement.UserServiceImplements;
import ru.yandex.practicum.filmorate.storage.UserStorage;
import ru.yandex.practicum.filmorate.storage.implement.InMemoryUserStorage;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class UserTests extends FilmorateApplicationTests {

    private static UserController userController;
    private static User testUser1;

    @BeforeAll
    public static void init() {
        UserStorage userStorage = new InMemoryUserStorage();
        userController = new UserController(new UserServiceImplements(userStorage));
    }

    @BeforeEach
    void beforeEach() {
        testUser1 = User.builder()
                .email("LTC@yandex.ru")
                .login("Nagibator")
                .name("Kolyan")
                .birthday(LocalDate.of(1991, 12, 2))
                .build();
    }

    @Test
    void shouldThrowBirthdayInFutureTest() {
        testUser1.setBirthday(LocalDate.of(2024, 7, 27));
        Exception exception = assertThrows(ValidationException.class, () -> userController.addUser(testUser1));
        String expectedMessage = "дата рождения не может быть указана в будущем времени";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldCreateUserTest() {
        assertEquals(testUser1, userController.addUser(testUser1));
        assertNotNull(userController.getUsers());
        assertTrue(userController.getUsers().contains(testUser1));
    }

    @Test
    void shouldUpdateUserTest() {
        User actual1 = userController.addUser(testUser1);
        actual1.setId(testUser1.getId());
        actual1.setEmail("LTC@yandex.ru");
        actual1.setName("Kolyan");
        User update = userController.updateUser(actual1);
        assertNotNull(update, "ошибка теста: юзер не обновлен");
        assertEquals(testUser1, update, "ошибка теста: юзеры не равны!");
    }

    @Test
    void shouldThrowWrongIdTest() {
        User actual1 = userController.addUser(testUser1);
        actual1.setId(0);
        Exception exception = assertThrows(ObjectNotFoundException.class, () -> userController.updateUser(testUser1));
        assertEquals("пользователя с таким id не существует.", exception.getMessage());
    }

    @Test
    void shouldThrowEmailIsEmptyTest() {
        testUser1.setEmail("");
        Exception exception = assertThrows(ValidationException.class, () -> userController.addUser(testUser1));
        String expectedMessage = "поле электронная почта не может быть пустой.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldThrowEmailWithNoSimbolsTest() {
        testUser1.setEmail("LTCyandex.ru");
        Exception exception = assertThrows(ValidationException.class, () -> userController.addUser(testUser1));
        String expectedMessage = "электронная почта должна содержать символ @";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldThrowLoginEmptyTest() {
        testUser1.setLogin("");
        Exception exception = assertThrows(ValidationException.class, () -> userController.addUser(testUser1));
        String expectedMessage = "логин не может быть пустым.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldThrowLoginWithSpaceTest() {
        testUser1.setLogin("minecraft sila");
        Exception exception = assertThrows(ValidationException.class, () -> userController.addUser(testUser1));
        String expectedMessage = "логин не может содержать пробелы";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }


}
