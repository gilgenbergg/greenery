package com.example.greenery.exceptions;

public class StatusNotFoundException extends RuntimeException {
    public StatusNotFoundException(String status) {
        super("Could not find item with status:  " + status);
    }
}
