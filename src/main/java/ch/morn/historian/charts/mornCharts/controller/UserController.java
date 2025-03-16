package ch.morn.historian.charts.mornCharts.controller;

import ch.morn.historian.charts.mornCharts.controller.modelAssembler.UserModelAssembler;
import ch.morn.historian.charts.mornCharts.exception.UserNotFoundException;
import ch.morn.historian.charts.mornCharts.model.User;
import ch.morn.historian.charts.mornCharts.repository.UserRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
public class UserController {
    private final UserRepository repository;
    private final UserModelAssembler modelAssembler;

    public UserController(UserRepository repository, UserModelAssembler modelAssembler) {
        this.repository = repository;
        this.modelAssembler = modelAssembler;
    }

    @GetMapping("/users")
    public CollectionModel<EntityModel<User>> getAllUser() {
        List<EntityModel<User>> users = repository.findAll().stream()
                .map(modelAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(users, linkTo(methodOn(UserController.class).getAllUser()).withSelfRel());
    }

    @PostMapping("/users")
    public User saveNewUser(@RequestBody User newUser) {
        return repository.save(newUser);
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> getOneUser(@PathVariable Long id) {
        User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        return modelAssembler.toModel(user);

    }

    @PutMapping("/users/{id}")
    public User replaceUser(@RequestBody User newUser, @PathVariable Long id) {
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
    public void deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
