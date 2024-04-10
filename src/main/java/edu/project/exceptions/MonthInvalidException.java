package edu.project.exceptions;

public class MonthInvalidException extends RuntimeException {

    public MonthInvalidException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
