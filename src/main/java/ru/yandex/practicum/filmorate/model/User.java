package ru.yandex.practicum.filmorate.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
<<<<<<< HEAD
=======

>>>>>>> main
    int id;
    @NotBlank
    @Email
    String email;
    @NotNull
<<<<<<< HEAD
    @Pattern(regexp = "^[^ ]+$", message = "Логин содержит пробелы")
=======
    @Pattern(regexp = "\\S+", message = "Логин содержит пробелы")
>>>>>>> main
    String login;
    String name;
    @Past
    LocalDate birthday;
<<<<<<< HEAD
=======
    private final Set<Integer> friends = new HashSet<>(); //хешсет для списка друзей,(set <Integer  вместо >set <Long>)

    public Set<Integer> getFriends() {
        return friends;
    }

    public void addFriend(int friendId) {
        friends.add(friendId);
    }

    public boolean deleteFriend(int friendId) {
        return friends.remove(friendId);
    }
>>>>>>> main
}
