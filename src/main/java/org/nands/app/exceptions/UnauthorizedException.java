package org.nands.app.exceptions;

public class UnauthorizedException
        extends AppException {

    public UnauthorizedException(
            String message) {

        super(message, 401);
    }
}