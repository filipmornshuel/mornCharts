package ch.morn.historian.charts.mornCharts.controller;

import ch.morn.historian.charts.mornCharts.exception.UserNotFoundException;
import ch.morn.historian.charts.mornCharts.model.User;
import ch.morn.historian.charts.mornCharts.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    // GET /users - Liste aller Benutzer
    @GetMapping
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    // GET /users/{id} - Einzelnen Benutzer abrufen
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return ResponseEntity.ok(user);
    }

    // POST /users - Neuen Benutzer speichern
    @PostMapping
    public ResponseEntity<User> saveNewUser(@RequestBody User newUser) {
        User savedUser = repository.save(newUser);
        return ResponseEntity.ok(savedUser);
    }

    // PUT /users/{id} - Benutzer ersetzen oder neu anlegen
    @PutMapping("/{id}")
    public ResponseEntity<User> replaceUser(@RequestBody User newUser, @PathVariable Long id) {
        User updatedUser = repository.findById(id)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    user.setEmail(newUser.getEmail());
                    user.setPasswordHash(newUser.getPasswordHash());
                    user.setRole(newUser.getRole());
                    user.setCreatedAt(newUser.getCreatedAt());
                    return repository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return repository.save(newUser);
                });

        return ResponseEntity.ok(updatedUser);
    }

    // DELETE /users/{id} - Benutzer löschen
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
