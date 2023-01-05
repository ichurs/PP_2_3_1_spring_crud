package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;


@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String users(Model model) {
        model.addAttribute("users", userService.listUsers());
        return "users";
    }

    @GetMapping("/users")
    public String userTable(Model model) {
        model.addAttribute("users", userService.listUsers());
        return "users";
    }

    @GetMapping("/users/{id}")
    public String getContactById(Model model, @PathVariable("id") int userId) {
        model.addAttribute("user", userService.getUserById(userId));
        return "user";
    }

    @GetMapping("/newUser")
    public String addNewUser(@ModelAttribute("user") User user) {
        return "newUser";
    }

    @PostMapping("/newUser")
    public String saveNewUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/users";
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.removeUser(id);
        return "redirect:/users";
    }

    @GetMapping("/users/{id}/edit")
    public String updateUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }

    @PatchMapping("/users/{id}/edit")
    public String update(@ModelAttribute("user") User user,
                         @PathVariable("id") int id) {
        userService.updateUser(id, user);
        return "redirect:/users";
    }


}
