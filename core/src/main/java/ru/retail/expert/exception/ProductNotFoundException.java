package ru.retail.expert.exception;

public class ProductNotFoundException extends ParentException {

    public ProductNotFoundException(String message) {
        super(message);
    }
}
