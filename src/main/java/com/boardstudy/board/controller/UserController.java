package com.boardstudy.board.controller;

import com.boardstudy.board.Service.UserService;
import com.boardstudy.board.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        User registeredUser = userService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully with ID: " + registeredUser.getId());
    }

    @GetMapping("/find")
    public ResponseEntity<User> findUserByUsername(@RequestParam String username) {
        return userService.findUserByUsername(username)
                .map(user -> ResponseEntity.ok(user))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }
}
