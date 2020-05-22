package com.example.greenery.exceptions;

public class ItemNotFoundException extends RuntimeException {

    public ItemNotFoundException(Integer id) {
        super("Could not find item with id:  " + id);
    }
}
