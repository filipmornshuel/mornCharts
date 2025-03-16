package ch.morn.historian.charts.mornCharts.controller;

import ch.morn.historian.charts.mornCharts.exception.UserNotFoundException;
import ch.morn.historian.charts.mornCharts.model.User;
import ch.morn.historian.charts.mornCharts.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/users")
    List<User> getAllUser() {
        return repository.findAll();
    }

    @PostMapping("/users")
    User saveNewUser(@RequestBody User newUser) {
        return repository.save(newUser);
    }

    @GetMapping("/users/{id}")
    User getOneUser(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/users/{id}")
    User replaceUser(@RequestBody User newUser, @PathVariable Long id) {
        return repository.findById(id)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    user.setEmail(newUser.getEmail());
                    user.setPasswordHash(newUser.getPasswordHash());
                    user.setRole(newUser.getRole());
                    user.setCreatedAt(newUser.getCreatedAt());
                    return repository.save(newUser);
                })
                .orElseGet(() -> {
                    return repository.save(newUser);
                });
    }

    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
