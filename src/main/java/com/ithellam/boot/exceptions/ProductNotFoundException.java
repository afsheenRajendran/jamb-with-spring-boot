package com.ithellam.boot.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException() {
        super();
    }

    public ProductNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
