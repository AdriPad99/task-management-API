package com.example.task_managment_api.User;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public String addUser(Users user) {
        if (user.getRole().equals("ADMIN") || user.getRole().equals("USER")) {
            repo.save(user);
            return "User added successfully";
        } else {
            return "Incorrect role for given user.";
        }
    }

    public Users getUserById(Integer id) {
        return repo.findById(id)
        .orElseThrow(() -> new IllegalArgumentException(
            "There is no User with the provided ID."));
    }

    public String updateUser(Integer id, Users user) {

        Users userInfo = getUserById(id);

        if (userInfo.getId() > 0) {
            userInfo.setUsername(user.getUsername());
            userInfo.setEmail(user.getEmail());
            userInfo.setRole(user.getRole());
            repo.save(userInfo);
            return "user " + userInfo.getUsername() + " updated successfully!";
        } else {
            return "user by id of " + id + " does not exist within the db.";
        }
    }

    public String deleteUserWithId(Integer id) {
        Users user = getUserById(id);

        if (user.getId() > 0) {
            repo.deleteById(id);
            return "User " + id + " deleted successfully!";
        } else {
            return "User by id of " + id + " not found.";
        }

    }

    public List<Users> getAllAvailableUsers() {
        return repo.findAll();
    }

}
