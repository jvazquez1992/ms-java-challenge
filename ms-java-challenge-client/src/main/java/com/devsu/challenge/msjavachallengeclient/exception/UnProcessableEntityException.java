package com.devsu.challenge.msjavachallengeclient.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class UnProcessableEntityException extends RuntimeException {
    public UnProcessableEntityException(String message) {
        super(message);
    }
}

