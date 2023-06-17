package ru.yandex.practicum.filmorate.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    int id;
    @NotBlank
    @Email
    String email;
    @NotNull
    @Pattern(regexp = "\\S+", message = "Логин содержит пробелы")
    String login;
    String name;
    @Past
    LocalDate birthday;
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
}
