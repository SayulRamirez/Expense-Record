package edu.project.exceptions;

public class CategoryNotFound extends RuntimeException {

    public CategoryNotFound(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
