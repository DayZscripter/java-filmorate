package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.controller.UserController;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.implement.UserServiceImplements;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class UserTests extends FilmorateApplicationTests {

    private static UserController userController;
    private static User user1;
    //private static User user2;
    //private static User user3;

    @BeforeAll
    public static void init() {
        userController = new UserController(new UserServiceImplements());
    }

    @BeforeEach
    void beforeEach() {
        user1 = User.builder()
                .email("LTC@yandex.ru")
                .login("Nagibator")
                .name("Kolyan")
                .birthday(LocalDate.of(1991, 12, 2))
                .build();
//        user2 = User.builder()
//                .email("Pavel_Skate@gmail.com")
//                .login("KonchiCabana")
//                .name("Pavel")
//                .birthday(LocalDate.of(1991, 8, 27))
//                .build();
//        user3 = User.builder()
//                .email("Tita@yandex.ru")
//                .login("NikitaTanks")
//                .name("Nikita")
//                .birthday(LocalDate.of(1990, 7, 3))
//                .build();
    }

    @Test
    void shouldThrowBirthdayInFutureTest() {
        user1.setBirthday(LocalDate.of(2024, 7, 27));
        Exception exception = assertThrows(ValidationException.class, () -> userController.addUser(user1));
        String expectedMessage = "дата рождения не может быть указана в будущем времени";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldCreateUserTest() {
        assertEquals(user1, userController.addUser(user1));
        assertNotNull(userController.getUsers());
        assertTrue(userController.getUsers().contains(user1));
    }

    @Test
    void shouldUpdateUserTest() {
        User actual1 = userController.addUser(user1);
        actual1.setId(user1.getId());
        actual1.setEmail("LTC@yandex.ru");
        actual1.setName("Kolyan");
        User update = userController.updateUser(actual1);
        assertNotNull(update, "ошибка теста: юзер не обновлен");
        assertEquals(user1, update, "ошибка теста: юзеры не равны!");
    }

    @Test
    void shouldThrowWrongIdTest() {
        User actual1 = userController.addUser(user1);
        actual1.setId(0);
        Exception exception = assertThrows(ValidationException.class, () -> userController.updateUser(user1));
        assertEquals("Пользователя с таким айди не существует.", exception.getMessage());
    }

    @Test
    void shouldThrowEmailIsEmptyTest() {
        user1.setEmail("");
        Exception exception = assertThrows(ValidationException.class, () -> userController.addUser(user1));
        String expectedMessage = "электронная почта не может быть пустой.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldThrowEmailWithNoSimbolsTest() {
        user1.setEmail("LTCyandex.ru");
        Exception exception = assertThrows(ValidationException.class, () -> userController.addUser(user1));
        String expectedMessage = "электронная почта должна содержать символ @";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldThrowLoginEmptyTest() {
        user1.setLogin("");
        Exception exception = assertThrows(ValidationException.class, () -> userController.addUser(user1));
        String expectedMessage = "логин не может быть пустым.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldThrowLoginWithSpaceTest() {
        user1.setLogin("nuff nika");
        Exception exception = assertThrows(ValidationException.class, () -> userController.addUser(user1));
        String expectedMessage = "логин не может содержать пробелы";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }


}
