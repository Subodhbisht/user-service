package sb.bisht.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sb.bisht.userservice.entity.User;
import sb.bisht.userservice.exception.UserNotFoundException;
import sb.bisht.userservice.model.UserSearchCriteria;
import sb.bisht.userservice.service.UserService;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/saveUser")
    public ResponseEntity<Mono<User>> saveUser(@RequestBody User user) {
        Mono<User> persistedUser = userService.save(user);
        return new ResponseEntity(persistedUser, HttpStatus.OK);
    }

    @PutMapping("/updateUser")
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        userService.update(user);
        return new ResponseEntity<String>("User with id =" + user.getUserId() + " is updated.", HttpStatus.OK);
    }

    @PostMapping("/getUsers")
    public Flux<User> getUsers(@RequestBody UserSearchCriteria criteria) {
        return userService.findUsers(criteria);
    }

    @GetMapping("/user/{userName}")
    public ResponseEntity<Mono<?>> getUser(@PathVariable String userName) {
        try {
            Mono<User> user = userService.getUser(userName);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}