package com.example.greenery.exceptions;

public class TypeNotFoundException extends RuntimeException {
    public TypeNotFoundException(String type) {
        super("Could not find items with type:  " + type);
    }
}
