package edu.project.exceptions;

public class AmountInvalid extends RuntimeException {

    public AmountInvalid(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
