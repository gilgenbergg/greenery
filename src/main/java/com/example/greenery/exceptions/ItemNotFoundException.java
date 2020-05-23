package com.example.greenery.exceptions;

import java.util.function.Supplier;

public class ItemNotFoundException extends RuntimeException {

    public ItemNotFoundException(Integer id) {
        super("Could not find item with id:  " + id);
    }
}
