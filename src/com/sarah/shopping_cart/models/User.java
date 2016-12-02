package com.sarah.shopping_cart.models;

public class User {

    private final String email;
    private final String password;

    private User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User: email=" + email;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String email;
        private String password;

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public User build() {
            return new User(email, password);
        }
    }
}
