package org.nands.app.exceptions;

public class ValidationException
        extends AppException {

    public ValidationException(
            String message) {

        super(message, 400);
    }
}