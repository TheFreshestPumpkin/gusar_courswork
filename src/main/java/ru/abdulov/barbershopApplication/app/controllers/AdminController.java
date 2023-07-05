package ru.abdulov.barbershopApplication.app.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.abdulov.barbershopApplication.app.entitys.User;
import ru.abdulov.barbershopApplication.app.entitys.enums.Role;
import ru.abdulov.barbershopApplication.app.services.BarberService;
import ru.abdulov.barbershopApplication.app.services.UserService;

import java.util.Map;


/** Данный класс предназначен для доступа к функционалу админа: удаление пользователей и
 * изменение ролей*/
@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {
    private final UserService userService;
    private final BarberService barberService;

    /** Данный метод передаёт в модель список пользователей */
    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("users", userService.userList());
        return "admin";
    }

    /** Данный метод позволяет удалить пользователя из бд */
    @GetMapping("/admin/user/delete/{id}")
    public String userDelete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
    /** Данный метод передаёт в модель информация объект пользователя и список возмозжных ролей */
    @GetMapping("/admin/user/edit/{user}")
    public String userEdit(@PathVariable("user") User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "user-edit";
    }
    /** Данный метод позволяет поменять роль пользователя на полученные значения */
    @PostMapping("/admin/user/edit")
    public String userEdit(@RequestParam("userId") User user, @RequestParam Map<String, String> form) {
        userService.changeUserRoles(user, form);
        return "redirect:/admin";
    }
}