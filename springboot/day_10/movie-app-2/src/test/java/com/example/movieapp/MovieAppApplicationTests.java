package com.example.movieapp;

import com.example.movieapp.entity.*;
import com.example.movieapp.model.enums.MovieType;
import com.example.movieapp.model.enums.UserRole;
import com.example.movieapp.repository.*;
import com.github.javafaker.Faker;
import com.github.slugify.Slugify;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootTest
class MovieAppApplicationTests {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private DirectorRepository directorRepository;

    @Autowired
    private EpisodeRepository episodeRepository;

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    void save_genres() {
        Faker faker = new Faker();
        Slugify slugify = Slugify.builder().build();

        for (int i = 0; i < 20; i++) {
            String name = faker.funnyName().name();
            Genre genre = Genre.builder()
                    .name(name)
                    .slug(slugify.slugify(name))
                    .build();
            genreRepository.save(genre);
        }
    }

    @Test
    void save_actors() {
        Faker faker = new Faker();

        for (int i = 0; i < 100; i++) {
            String name = faker.name().fullName();
            Actor actor = Actor.builder()
                    .name(name)
                    .avatar("https://placehold.co/600x400?text=" + String.valueOf(name.charAt(0)).toUpperCase())
                    .bio(faker.lorem().paragraph())
                    .build();
            actorRepository.save(actor);
        }
    }

    @Test
    void save_directors() {
        Faker faker = new Faker();

        for (int i = 0; i < 30; i++) {
            String name = faker.name().fullName();
            Director director = Director.builder()
                    .name(name)
                    .avatar("https://placehold.co/600x400?text=" + String.valueOf(name.charAt(0)).toUpperCase())
                    .bio(faker.lorem().paragraph())
                    .build();
            directorRepository.save(director);
        }
    }

    @Test
    void save_countries() {
        Faker faker = new Faker();
        Slugify slugify = Slugify.builder().build();

        for (int i = 0; i < 20; i++) {
            String name = faker.country().name();
            Country country = Country.builder()
                    .name(name)
                    .slug(slugify.slugify(name))
                    .build();
            countryRepository.save(country);
        }
    }

    @Test
    void save_users() {
        Faker faker = new Faker();

        for (int i = 0; i < 50; i++) {
            String name = faker.name().fullName();
            User user = User.builder()
                    .name(name)
                    .email(faker.internet().emailAddress())
                    .avatar("https://placehold.co/600x400?text=" + String.valueOf(name.charAt(0)).toUpperCase())
                    .password("123")
                    .role(i == 0 || i == 1 ? UserRole.ADMIN : UserRole.USER)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
            userRepository.save(user);
        }
    }

    @Test
    void save_blogs() {
        Faker faker = new Faker();
        Slugify slugify = Slugify.builder().build();
        Random random = new Random();

        List<User> users = userRepository.findByRole(UserRole.ADMIN);

        for (int i = 0; i < 30; i++) {
            String title = faker.book().title();
            Blog blog = Blog.builder()
                    .title(title)
                    .slug(slugify.slugify(title))
                    .description(faker.lorem().paragraph())
                    .content(faker.lorem().paragraph(100))
                    .thumbnail("https://placehold.co/600x400?text=" + String.valueOf(title.charAt(0)).toUpperCase())
                    .status(faker.bool().bool())
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .user(users.get(random.nextInt(users.size())))
                    .build();
            blogRepository.save(blog);
        }
    }

    @Test
    void save_comments() {
        Faker faker = new Faker();
        Random random = new Random();

        List<User> users = userRepository.findByRole(UserRole.USER);
        List<Blog> blogs = blogRepository.findAll();

        for (Blog blog : blogs) {
            // random 5 -> 10 comments
            for (int i = 0; i < random.nextInt(6) + 5; i++) {
                Comment comment = Comment.builder()
                        .content(faker.lorem().paragraph())
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .user(users.get(random.nextInt(users.size())))
                        .blog(blog)
                        .build();
                commentRepository.save(comment);
            }
        }
    }

    @Test
    void save_movies() {
        Random random = new Random();
        Faker faker = new Faker();
        Slugify slugify = Slugify.builder().build();

        List<Country> countries = countryRepository.findAll();
        List<Genre> genres = genreRepository.findAll();
        List<Director> directors = directorRepository.findAll();
        List<Actor> actors = actorRepository.findAll();

        for (int i = 0; i < 100; i++) {
            // Random 1 country
            Country rdCountry = countries.get(random.nextInt(countries.size()));

            // Random 1 -> 3 genres
            List<Genre> rdGenres = new ArrayList<>();
            for (int j = 0; j < random.nextInt(3) + 1; j++) {
                Genre rdGenre = genres.get(random.nextInt(genres.size()));
                if (!rdGenres.contains(rdGenre)) {
                    rdGenres.add(rdGenre);
                }
            }

            // Random 5 -> 7 actors
            List<Actor> rdActors = new ArrayList<>();
            for (int j = 0; j < random.nextInt(3) + 5; j++) {
                Actor rdActor = actors.get(random.nextInt(actors.size()));
                if (!rdActors.contains(rdActor)) {
                    rdActors.add(rdActor);
                }
            }

            // Random 1 -> 3 directors
            List<Director> rdDirectors = new ArrayList<>();
            for (int j = 0; j < random.nextInt(3) + 1; j++) {
                Director rdDirector = directors.get(random.nextInt(directors.size()));
                if (!rdDirectors.contains(rdDirector)) {
                    rdDirectors.add(rdDirector);
                }
            }

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
                    .country(rdCountry)
                    .genres(rdGenres)
                    .actors(rdActors)
                    .directors(rdDirectors)
                    .build();

            movieRepository.save(movie);
        }
    }

    @Test
    void save_reviews() {
        Faker faker = new Faker();
        Random random = new Random();

        List<User> users = userRepository.findByRole(UserRole.USER);
        List<Movie> movies = movieRepository.findAll();

        for (Movie movie : movies) {
            // random 5 -> 10 reviews
            for (int i = 0; i < random.nextInt(6) + 5; i++) {
                Review review = Review.builder()
                        .content(faker.lorem().paragraph())
                        .rating(random.nextInt(10) + 1)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .user(users.get(random.nextInt(users.size())))
                        .movie(movie)
                        .build();
                reviewRepository.save(review);
            }
        }
    }

    @Test
    void save_episodes() {
        List<Movie> movies = movieRepository.findAll();
        for (Movie movie : movies) {
            if(movie.getType() == MovieType.PHIM_BO) {
                // Random 5 -> 10 episodes
                for (int i = 0; i < new Random().nextInt(6) + 5; i++) {
                    Episode episode = Episode.builder()
                            .name("Tập " + (i + 1))
                            .duration(45)
                            .videoUrl("blob:https://teacher.techmaster.vn/ded8c44b-aa96-4c22-8611-17ae8c9e7555")
                            .displayOrder(i + 1)
                            .createdAt(LocalDateTime.now())
                            .updatedAt(LocalDateTime.now())
                            .movie(movie)
                            .build();
                    episodeRepository.save(episode);
                }
            } else {
                Episode episode = Episode.builder()
                        .name("Tập full")
                        .duration(120)
                        .videoUrl("blob:https://teacher.techmaster.vn/ded8c44b-aa96-4c22-8611-17ae8c9e7555")
                        .displayOrder(1)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .movie(movie)
                        .build();
                episodeRepository.save(episode);
            }
        }
    }

    @Test
    void save_favorites() {
        List<User> users = userRepository.findByRole(UserRole.USER);
        List<Movie> movies = movieRepository.findByStatus(true);

        for (User user : users) {
            // 1 -> 3 favorite movies. Each favorite has a unique movie
            List<Movie> favoriteMovies = new ArrayList<>();
            for (int i = 0; i < new Random().nextInt(3) + 1; i++) {
                Movie movie = movies.get(new Random().nextInt(movies.size()));
                if (!favoriteMovies.contains(movie)) {
                    favoriteMovies.add(movie);
                }
            }

            for (Movie movie : favoriteMovies) {
                Favorite favorite = Favorite.builder()
                        .user(user)
                        .movie(movie)
                        .createdAt(LocalDateTime.now())
                        .build();
                favoriteRepository.save(favorite);
            }
        }
    }

    @Test
    void save_histories() {
        List<User> users = userRepository.findByRole(UserRole.USER);
        List<Movie> movies = movieRepository.findByStatus(true);

        for (User user : users) {
            // 1 -> 3 histories. Each history has a unique movie
            List<Movie> historyMovies = new ArrayList<>();
            for (int i = 0; i < new Random().nextInt(3) + 1; i++) {
                Movie movie = movies.get(new Random().nextInt(movies.size()));
                if (!historyMovies.contains(movie)) {
                    historyMovies.add(movie);
                }
            }

            for (Movie movie : historyMovies) {
                if(movie.getType() == MovieType.PHIM_BO) {

                } else {

                }
            }
        }
    }

    @Test
    void update_password_user() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            user.setPassword(passwordEncoder.encode("123"));
            userRepository.save(user);
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
