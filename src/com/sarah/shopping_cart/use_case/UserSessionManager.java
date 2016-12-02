package com.sarah.shopping_cart.use_case;

import com.sarah.shopping_cart.models.User;
import com.sarah.shopping_cart.repository.UserRepository;

import java.util.Objects;

public class UserSessionManager {

    private final UserRepository userRepository;
    private String loggedInUserEmail;

    public UserSessionManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean signup(String email, String password) {
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            return false;
        }

        if (userRepository.userWithEmailExists(email)) {
            return false;
        }
        userRepository.addUser(User.builder()
                .setEmail(email)
                .setPassword(password)
                .build());
        return true;
    }

    public boolean login(String email, String password) {
        if (loggedInUserEmail != null) {
            return true;
        }

        User user = userRepository.getUserByEmail(email);
        if (user == null) {
            return false;
        }

        boolean authenticated = Objects.equals(password, user.getPassword());
        if (authenticated) {
            loggedInUserEmail = user.getEmail();
        }
        return authenticated;
    }

    public void logout() {
        loggedInUserEmail = null;
    }

    public boolean isLoggedIn() {
        return loggedInUserEmail != null;
    }

}
