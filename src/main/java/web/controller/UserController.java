package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @RequestMapping(method = RequestMethod.GET)
//    public ModelAndView allUsers() {
//        List<User> users = userService.listUsers();
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("users");
//        modelAndView.addObject("usersList", users);
//        return modelAndView;
//    }

    @GetMapping("/")
    public String users(Model model) {
        model.addAttribute("users", userService.listUsers());
        return "users";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView editPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        return modelAndView;
    }
}
