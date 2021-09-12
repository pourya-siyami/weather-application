package com.example.weatherapplication.user;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    String secret = "aa3yle89ephyh28t2g5g8st7r3z67txt16bllz1zoakppje3zyka8gffglxgsc9gf702zpnkb8ajsqoj";

    @GetMapping("/users")
    public String showUsersList(Model model) {
        List<User> users = userService.getAlUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("users/{id}")
    public String showUserPage(@PathVariable long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "userpage";
    }

    @GetMapping("newuser")
    public String showNewUserForm(Model model) {
        User u = new User();
        model.addAttribute("user", u);
        return "newuser";
    }

    @PostMapping("newuser")
    public String addNewUser(@ModelAttribute(value = "user") User user) {
        userService.saveUser(user);
        return "login";
    }

    @GetMapping("login")
    public String login(Model model) {
        User u = new User();
        model.addAttribute("user", u);
        return "login";
    }

    @PostMapping("login")
    public String login(@ModelAttribute(value = "user") User user) {
        User u = userService.getUsernameAndPassword
                (user.getUsername(), user.getPassword());
        if (u != null) {
            String payload = String.format("{\"message\" : %d }", u.getId());
            String token = Jwts.builder()
                    .setPayload(payload)
                    .signWith(SignatureAlgorithm.HS256, secret)
                    .compact();
            u.setToken(token);
            userService.saveUser(u);
            return "weather";
        } else {
            return "not found";
        }
    }
}
