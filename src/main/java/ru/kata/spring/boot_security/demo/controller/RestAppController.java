package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RestAppController {
    private UserService uService;

    private UserRepository userRepository;

    @Autowired
    public RestAppController(UserService userService, UserRepository userRepository) {
        this.uService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(uService.getAllUsers());
    }

    @GetMapping(value = "/getUser/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") long id) {
        return ResponseEntity.ok(uService.getUser(id));
    }

    @PostMapping(value = "/saveUser")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        uService.saveUser(user);
        return ResponseEntity.ok(user);
    }

    @PostMapping(value = "/updateUser/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        uService.saveUser(user);
        return ResponseEntity.ok(user);
    }

    @PostMapping(value = "/deleteUser/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable(name = "id") int id) {
        uService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/getAuthUser")
    public ResponseEntity<User> getAuth(Principal principal) {
        User user = (User) uService.loadUserByUsername(principal.getName());
        return ResponseEntity.ok(user);
    }
}
