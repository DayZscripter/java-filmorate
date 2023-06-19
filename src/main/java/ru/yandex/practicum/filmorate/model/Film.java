package ru.yandex.practicum.filmorate.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Film {
    int id;
    @NotBlank()
    @Size(max = 200)
    String name;
    @NotNull
    String description;
    LocalDate releaseDate;
    @Positive
    int duration;
    @NotNull
    RatingMpa mpa;
    List<Genre> genres;
    Set<Integer> likes;
}
