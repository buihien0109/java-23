package com.example.movieapp.controller;

import com.example.movieapp.model.enums.MovieType;
import com.example.movieapp.repository.ActorRepository;
import com.example.movieapp.repository.CountryRepository;
import com.example.movieapp.repository.DirectorRepository;
import com.example.movieapp.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/movies")
@RequiredArgsConstructor
public class MovieController {
    private final CountryRepository countryRepository;
    private final DirectorRepository directorRepository;
    private final ActorRepository actorRepository;
    private final GenreRepository genreRepository;

    // Tạo movie (template)
    @GetMapping("/create")
    public String getCreatePage(Model model) {
        // Trả về ds quốc gia, đạo diễn, diễn viên, thể loại, loại phim
        // TODO: Refactor theo controller - service - repo
        model.addAttribute("countries", countryRepository.findAll());
        model.addAttribute("directors", directorRepository.findAll());
        model.addAttribute("actors", actorRepository.findAll());
        model.addAttribute("genres", genreRepository.findAll());
        model.addAttribute("movieTypes", MovieType.values());
        return "admin/movie/create";
    }
}
