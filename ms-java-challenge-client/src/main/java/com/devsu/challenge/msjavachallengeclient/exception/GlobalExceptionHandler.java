package com.devsu.challenge.msjavachallengeclient.exception;

import com.devsu.challenge.msjavachallengeclient.domain.enums.ClientStatusEnum;
import com.devsu.challenge.msjavachallengeclient.domain.enums.GenderEnum;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Arrays;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {NotFoundEntityException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorDetails notFoundEntityException(NotFoundEntityException ex, WebRequest request) {
        return new ErrorDetails(ex.getMessage(), request.getDescription(false));
    }

    @ExceptionHandler(value = {AlreadyExistsEntityException.class})
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ErrorDetails alreadyExistsEntityException(AlreadyExistsEntityException ex,  WebRequest request){
        return new ErrorDetails(ex.getMessage(), request.getDescription(false));
    }

    @ExceptionHandler(value = {UnProcessableEntityException.class})
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorDetails unprocessedEntityException(UnProcessableEntityException ex, WebRequest request) {
        return new ErrorDetails(ex.getMessage(), request.getDescription(false));
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDetails illegalArgumentException(IllegalArgumentException ex, WebRequest request){
        return new ErrorDetails(illegalArgumentErrorMessage(ex.getMessage()), request.getDescription(false));
    }

    private String illegalArgumentErrorMessage(String originalMessage){
        if(originalMessage.contains("No enum constant")){
            if(originalMessage.contains(GenderEnum.class.getSimpleName())){
                return String.format("Invalid gender - Accepted values are %s", Arrays.toString(GenderEnum.values()));
            }else if (originalMessage.contains(ClientStatusEnum.class.getSimpleName())){
                return String.format("Invalid status - Accepted values are %s", Arrays.toString(ClientStatusEnum.values()));
            }else{
                return originalMessage;
            }
        }else{
            return originalMessage;
        }
    }

}
