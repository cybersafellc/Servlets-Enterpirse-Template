package org.nands.app.exceptions;

public class BadRequestException extends AppException{
    public BadRequestException(String message){
        super(message, 400);
    }
}
