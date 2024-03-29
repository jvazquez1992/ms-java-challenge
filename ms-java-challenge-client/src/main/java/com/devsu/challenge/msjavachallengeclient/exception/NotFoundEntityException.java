package com.devsu.challenge.msjavachallengeclient.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundEntityException extends RuntimeException {
    public NotFoundEntityException(String message){super(message);}
}
