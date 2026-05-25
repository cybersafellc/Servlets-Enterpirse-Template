package org.nands.app.exceptions;

public class DatabaseException
        extends AppException {

    public DatabaseException(
            Throwable cause) {

        super(
                "Internal database error",
                500
        );

        initCause(cause);
    }
}