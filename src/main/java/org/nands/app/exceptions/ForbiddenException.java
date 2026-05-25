package org.nands.app.exceptions;

public class ForbiddenException extends AppException{
    public ForbiddenException(String message){
        super(message, 403);
    }
}
