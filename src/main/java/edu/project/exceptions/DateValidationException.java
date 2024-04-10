package edu.project.exceptions;

public class DateValidationException extends RuntimeException {

    public DateValidationException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
