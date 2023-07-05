package ru.abdulov.barbershopApplication.app.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.abdulov.barbershopApplication.app.entitys.User;
import ru.abdulov.barbershopApplication.app.services.UserService;

import java.security.Principal;

/** Данный класс предназначен для доступа к функционалу пользователя: вход, регистрация, страница пользователя*/
@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String createUser(User user, Model model) {
        if(!userService.createUser(user)){
            model.addAttribute("errorMessage","Данный email уже используется");
            return "registration";
        }
        userService.createUser(user);
        return "redirect:/login";
    }

    @GetMapping("/user/{user}")
    public String userInfo(@PathVariable("user") User user,Principal principal, Model model){
        model.addAttribute("user1", userService.getUserByPrincipal(principal));
        model.addAttribute("user",user);
        model.addAttribute("appointments",user.getAppointments());
        return "user-info";
    }
}
