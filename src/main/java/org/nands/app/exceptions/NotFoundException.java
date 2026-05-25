package org.nands.app.exceptions;

public class NotFoundException
        extends AppException {

    public NotFoundException(
            String message) {

        super(message, 404);
    }
}