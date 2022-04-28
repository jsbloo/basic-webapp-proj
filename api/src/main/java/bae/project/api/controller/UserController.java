package bae.project.api.controller;

import bae.project.api.domain.Factoid;
import bae.project.api.domain.User;
import bae.project.api.service.UserService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("user")
public class UserController {

    UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<User> create(@RequestBody User user){
        return new ResponseEntity<>(service.create(user), HttpStatus.CREATED);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<User> getById(@PathVariable long id){
        return new ResponseEntity<>(service.getById(id),HttpStatus.OK);
    }

    @GetMapping(value="/{username}")
    public User findByUsername(@PathVariable String username) {
        System.out.println("Username :" + username);
        return service.findByUsername(username);
    }
}
