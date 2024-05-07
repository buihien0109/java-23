package com.example.movieapp;

import com.example.movieapp.entity.Movie;
import com.example.movieapp.model.enums.MovieType;
import com.example.movieapp.repository.MovieRepository;
import com.github.javafaker.Faker;
import com.github.slugify.Slugify;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@SpringBootTest
class MovieAppApplicationTests {
    @Autowired
    private MovieRepository movieRepository;

    @Test
    void save_movies() {
        Random random = new Random();
        Faker faker = new Faker();
        Slugify slugify = Slugify.builder().build();

        for (int i = 0; i < 100; i++) {
            String name = faker.book().title();
            Movie movie = Movie.builder()
                    .name(name)
                    .slug(slugify.slugify(name))
                    .description(faker.lorem().paragraph())
                    .poster("https://placehold.co/600x400?text=" + String.valueOf(name.charAt(0)).toUpperCase())
                    .releaseYear(faker.number().numberBetween(2020, 2024))
                    .rating(faker.number().randomDouble(1, 1, 10))
                    .type(MovieType.values()[random.nextInt(MovieType.values().length)])
                    .status(faker.bool().bool())
                    .trailer("https://www.youtube.com/embed/inIVdZSFfc0?si=A-V6ER7dPCMf8r12")
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();

            movieRepository.save(movie);
        }
    }


    @Test
    void test_movie_query() {
        // select * from movies
        List<Movie> movies = movieRepository.findAll();
        System.out.println("Sum movies: " + movies.size());

        // select * from movies where id in (1,2,3);
        List<Movie> moviesById = movieRepository.findAllById(List.of(1, 2, 3));
        System.out.println("Sum movies by list ID: " + moviesById.size());

        // select * from movies where id = 1
        Movie movie = movieRepository.findById(1).orElse(null);
        System.out.println("Movie: " + movie);

        // Update
        movie.setName("Lật mặt 7");
        movieRepository.save(movie);

        // Delete
        // delete from movies where id = 1
        movieRepository.deleteById(1);
        movieRepository.delete(movie);

        // delete from movies
        movieRepository.deleteAll();

        // delete from movies where id in (1, 2, 3)
        movieRepository.deleteAllById(List.of(1, 2, 3));

        // Sort
        List<Movie> moviesSort = movieRepository
                .findByType(MovieType.PHIM_BO, Sort.by("name", "rating").descending());
    }

    @Test
    void test_pagination() {
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("id").descending());
        Page<Movie> page = movieRepository.findByStatus(true, pageRequest);

        System.out.println("Total pages: " + page.getTotalPages());
        System.out.println("Total elements: " + page.getTotalElements());
        page.getContent().forEach(m -> System.out.println(m.getId()));
    }
}
