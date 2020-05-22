package com.example.greenery.exceptions;

public class LoginNotFoundException extends RuntimeException {
    public LoginNotFoundException(String login) {
        super("Could not find item with login:  " + login);
    }
}
