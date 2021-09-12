package com.example.weatherapplication.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> getAlUsers() {
        return (List<User>) userRepository.findAll();
    }

    public String getUsernameById(long id) {
        Optional<User> userId = userRepository.findById(id);
        if (userId.isPresent()) {
            return userId.get().getUsername();
        } else {
            return "not found";
        }
    }

    public User getUserById(long id) {
        Optional<User> userId = userRepository.findById(id);
        if (userId.isPresent()) {
            return userId.get();
        } else {
            return new User();
        }
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User getUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }
}
