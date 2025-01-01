package com.vehicle.controller;

import com.vehicle.model.User;
import com.vehicle.service.UserService;
import com.vehicle.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes("userId")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new UserDto());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserDto userDto) {
        userService.registerUser(userDto);
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String loginUser (@RequestParam String username, @RequestParam String password, Model model) {
        User user = userService.loginUser (username, password);
        if (user != null) {
            model.addAttribute("userId", user.getId()); // Simpan userId di session
            return "redirect:/dashboard";
        }
        return "redirect:/login?error";
    }

    @PostMapping("/logout")
    public String logoutUser (SessionStatus sessionStatus) {
        sessionStatus.setComplete(); // Menghapus session
        return "redirect:/login";
    }
}