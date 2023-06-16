package ru.yandex.practicum.filmorate.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
public class Film {

    private int id;
    @NotBlank()
    @Size(max = 200)
    private String name;
    @Positive
    private int duration;
    @NotNull
    private String description;
    private LocalDate releaseDate;
    private final Set<Integer> likes = new HashSet<>();

    public void addLike(User user) {
        likes.add(user.getId());
    }

    public void deleteLike(User user) {
        likes.remove(user.getId());
    }

}