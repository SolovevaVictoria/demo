package ru.geekbrains.demo.api;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.demo.model.Reader;
import ru.geekbrains.demo.model.User;
import ru.geekbrains.demo.repository.UserRepository;
import ru.geekbrains.demo.security.CustumPasswordEncoder;

import java.util.List;
//import ru.geekbrains.demo.repository.RoleRepository;

//import ru.geekbrains.demo.service.RoleService;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustumPasswordEncoder custumPasswordEncoder;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User newUser = new User();
        newUser.setUserName(user.getUserName());
        newUser.setPassword(custumPasswordEncoder.encode(user.getPassword()));
        return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(newUser));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userRepository.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userRepository.getById(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        try {
            User user = userRepository.getById(id);
            user.setUserName(userDetails.getUserName());
            user.setPassword(userDetails.getPassword());
            User updatedUser = userRepository.save(user);
            return ResponseEntity.ok(updatedUser);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }


    }
}