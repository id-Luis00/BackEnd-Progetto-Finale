package ediLuis.BE_W6_D1.exceptions;


import ediLuis.progetto_finale.exceptions.BadRequestException;
import ediLuis.progetto_finale.exceptions.NotFoundException;
import ediLuis.progetto_finale.exceptions.UnauthorizedException;
import ediLuis.progetto_finale.payloads.exceptionsPayload.ErrorPayload;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorPayload handleBadRequest(BadRequestException ex){
        return new ErrorPayload(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorPayload handleNotFound(NotFoundException ex) {
        return new ErrorPayload(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorPayload handleGenericExceptions(Exception exception){
        exception.printStackTrace();
        return new ErrorPayload(exception.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorPayload handleUnauthorized(RuntimeException exception){
        return new ErrorPayload(exception.getMessage(), LocalDateTime.now());
    }


}
