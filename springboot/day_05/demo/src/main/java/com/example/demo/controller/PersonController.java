package com.example.demo.controller;

import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping("/people")
    public String getPeople(Model model,
                            @RequestParam(required = false, defaultValue = "1") int page,
                            @RequestParam(required = false, defaultValue = "10") int pageSize) {
        model.addAttribute("pageData", personService.getAllPeople(page, pageSize));
        return "people";
    }
}
