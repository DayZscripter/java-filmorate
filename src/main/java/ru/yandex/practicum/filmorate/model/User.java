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
    int id;
    @NotBlank
    @Email
    String email;
    @NotNull
    @Pattern(regexp = "^[^ ]+$", message = "Логин содержит пробелы")
    String login;
    String name;
    @Past
    LocalDate birthday;
}
