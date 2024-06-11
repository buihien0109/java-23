package com.example.movieapp.model.request;

import com.example.movieapp.model.enums.MovieType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateMovieRequest {
    String name;
    String description;
    Integer releaseYear;
    MovieType type;
    Boolean status;
    String trailer;
    Integer countryId;
    List<Integer> genreIds;
    List<Integer> actorIds;
    List<Integer> directorIds;
}
