package com.example.movieapp.controller;

import com.example.movieapp.entity.Movie;
import com.example.movieapp.model.enums.MovieType;
import com.example.movieapp.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class WebController {
    private final MovieService movieService;

    @GetMapping("/")
    public String getHomePage(Model model) {
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
    public String getPhimLePage(Model model) {
        List<Movie> movies = movieService.getMoviesByType(MovieType.PHIM_LE, true, Sort.by("createdAt").descending());
        model.addAttribute("movies", movies);
        return "web/phim-le";
    }

    @GetMapping("/phim-chieu-rap")
    public String getPhimChieuRapPage(Model model) {
        List<Movie> movies = movieService.getMoviesByType(MovieType.PHIM_CHIEU_RAP, true, Sort.by("createdAt").descending());
        model.addAttribute("movies", movies);
        return "web/phim-chieu-rap";
    }
}
