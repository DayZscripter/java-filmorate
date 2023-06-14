package ru.yandex.practicum.filmorate.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class Film {

    private int id;                 //id фильма
    private String name;
    private String description;
    private LocalDate releaseDate;  //дата релиза фильма
    private int duration;           //продолжительность фильма
}
