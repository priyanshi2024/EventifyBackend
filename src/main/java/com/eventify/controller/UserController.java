package com.eventify.controller;

import com.eventify.entity.User;
import com.eventify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @PostMapping("/save")
    public String addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PutMapping("/edit")
    public String editUser(@RequestBody User user, @RequestParam Long userId) {
        return userService.editUser(user, userId);
    }

    @DeleteMapping("/delete")
    public String deleteUser(@RequestParam Long userId) {
        return userService.deleteUser(userId);
    }
}
