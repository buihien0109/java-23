package com.example.movieapp.controller;

import com.example.movieapp.entity.Episode;
import com.example.movieapp.entity.Movie;
import com.example.movieapp.entity.Review;
import com.example.movieapp.model.enums.MovieType;
import com.example.movieapp.service.EpisodeService;
import com.example.movieapp.service.MovieService;
import com.example.movieapp.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class WebController {
    private final MovieService movieService;
    private final ReviewService reviewService;
    private final EpisodeService episodeService;

    @GetMapping("/")
    public String getHomePage(Model model) {
        List<Movie> listPhimHot = movieService.getHotMovies(true, 1, 8).getContent();
        List<Movie> listPhimBo = movieService.getMoviesByType(MovieType.PHIM_BO, true, 1, 6).getContent();
        List<Movie> listPhimLe = movieService.getMoviesByType(MovieType.PHIM_LE, true, 1, 6).getContent();
        List<Movie> listPhimChieuRap = movieService.getMoviesByType(MovieType.PHIM_CHIEU_RAP, true, 1, 6).getContent();
        model.addAttribute("listPhimBo", listPhimBo);
        model.addAttribute("listPhimLe", listPhimLe);
        model.addAttribute("listPhimChieuRap", listPhimChieuRap);
        model.addAttribute("listPhimHot", listPhimHot);
        return "web/index";
    }

    @GetMapping("/phim-bo")
    public String getPhimBoPage(Model model,
                                @RequestParam(required = false, defaultValue = "1") int page,
                                @RequestParam(required = false, defaultValue = "12") int pageSize) {
        Page<Movie> pageData = movieService.getMoviesByType(MovieType.PHIM_BO, true, page, pageSize);
        model.addAttribute("pageData", pageData);
        model.addAttribute("currentPage", page);
        return "web/phim-bo";
    }

    @GetMapping("/phim-le")
    public String getPhimLePage(Model model,
                                @RequestParam(required = false, defaultValue = "1") int page,
                                @RequestParam(required = false, defaultValue = "12") int pageSize) {
        Page<Movie> pageData = movieService.getMoviesByType(MovieType.PHIM_LE, true, page, pageSize);
        model.addAttribute("pageData", pageData);
        model.addAttribute("currentPage", page);
        return "web/phim-le";
    }

    @GetMapping("/phim-chieu-rap")
    public String getPhimChieuRapPage(Model model,
                                      @RequestParam(required = false, defaultValue = "1") int page,
                                      @RequestParam(required = false, defaultValue = "12") int pageSize) {
        Page<Movie> pageData = movieService.getMoviesByType(MovieType.PHIM_CHIEU_RAP, true, page, pageSize);
        model.addAttribute("pageData", pageData);
        model.addAttribute("currentPage", page);
        return "web/phim-chieu-rap";
    }

    @GetMapping("/phim/{id}/{slug}")
    public String getPhimDetailPage(Model model, @PathVariable Integer id, @PathVariable String slug) {
        Movie movie = movieService.getMovie(id, slug, true);
        List<Movie> relatedMovies = movieService.getRelatedMovies(id, movie.getType(), true, 6);
        List<Review> reviews = reviewService.getReviewsByMovie(id);
        List<Episode> episodes = episodeService.getEpisodeListOfMovie(id, true);

        model.addAttribute("movie", movie);
        model.addAttribute("relatedMovies", relatedMovies);
        model.addAttribute("reviews", reviews);
        model.addAttribute("episodes", episodes);
        return "web/chi-tiet-phim";
    }

    @GetMapping("/dang-nhap")
    public String getLoginPage() {
        return "web/login";
    }
}
