package com.example.demothymeleaf.controller;

import com.example.demothymeleaf.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WebController {
    private List<User> users = new ArrayList<>(
            List.of(
                    new User(1, "Nguyễn Văn A", 20, true),
                    new User(2, "Trần Thị B", 22, false),
                    new User(3, "Lê Văn C", 25, true),
                    new User(4, "Phạm Thị D", 30, false),
                    new User(5, "Nguyễn Đăng E", 35, true),
                    new User(6, "Trần Thị F", 40, false),
                    new User(7, "Lê Văn G", 45, true)
            )
    );

    @GetMapping("/")
    public String getHome(Model model) {
        model.addAttribute("user", users.get(0));
        model.addAttribute("users", users);
        return "index";
    }

    @GetMapping("/blog")
    public String getBlog() {
        return "admin/blog";
    }
}
