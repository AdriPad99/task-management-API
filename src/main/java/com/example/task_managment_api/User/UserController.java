package com.example.task_managment_api.User;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    // gets all the users
    @GetMapping
    public List<Users> getAllUsers() {
        return service.getAllAvailableUsers();
    }

    // gets user by their id
    @GetMapping("{id}")
    public Users getUserById(@PathVariable Integer id) {
        return service.getUserById(id);
    }

    // adds user to db
    @PostMapping
    public String addUserAccount(@RequestBody Users user) {
        return service.addUser(user);
    }
    
    // updates user by given id in db
    @PutMapping("{id}")
    public String updateUserAccount(@PathVariable Integer id, @RequestBody Users user) {        
        return service.updateUser(id, user);
    }

    // delete user from db
    @DeleteMapping("{id}")
    public String deleteUserAccount(@PathVariable Integer id) {
        return service.deleteUserWithId(id);
    }
    
}
