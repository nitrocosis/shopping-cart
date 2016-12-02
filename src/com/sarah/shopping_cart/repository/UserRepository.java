package com.sarah.shopping_cart.repository;

import com.sarah.shopping_cart.models.User;

import java.util.LinkedHashMap;
import java.util.Map;

public class UserRepository {

    private final Map<String, User> users = new LinkedHashMap<>();

    public void addUser(User user) {
        users.put(user.getEmail(), user);
    }

    public void removeUserByEmail(String email) {
        users.remove(email);
    }

    public User getUserByEmail(String email) {
        return users.get(email);
    }

    public boolean userWithEmailExists(String email) {
        return users.containsKey(email);
    }
}
