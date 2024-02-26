package com.devsu.challenge.msjavachallengeaccount.exception;

import com.devsu.challenge.msjavachallengeaccount.domain.enums.AccountStatusEnum;
import com.devsu.challenge.msjavachallengeaccount.domain.enums.AccountTypeEnum;
import com.devsu.challenge.msjavachallengeaccount.domain.enums.MovementTypeEnum;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Arrays;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {UnProcessableEntityException.class})
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorDetails unprocessedEntityException(UnProcessableEntityException ex, WebRequest request) {
        return new ErrorDetails(ex.getMessage(), request.getDescription(false));
    }

    @ExceptionHandler(value = {ClientException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorDetails clientException(ClientException ex, WebRequest request) {
        return new ErrorDetails(ex.getMessage(), request.getDescription(false));
    }

    @ExceptionHandler(value = {EntityNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorDetails accountNotFoundException(EntityNotFoundException ex, WebRequest request) {
        return new ErrorDetails(ex.getMessage(), request.getDescription(false));
    }

    @ExceptionHandler(value = {AlreadyExistsEntityException.class})
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ErrorDetails alreadyExistsEntityException(AlreadyExistsEntityException ex,  WebRequest request){
        return new ErrorDetails(ex.getMessage(), request.getDescription(false));
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDetails illegalArgumentException(IllegalArgumentException ex, WebRequest request){
        return new ErrorDetails(illegalArgumentErrorMessage(ex.getMessage()), request.getDescription(false));
    }

    private String illegalArgumentErrorMessage(String originalMessage){
        if(originalMessage.contains("No enum constant")){
            if(originalMessage.contains(AccountStatusEnum.class.getSimpleName())){
                return String.format("Invalid account status - Accepted values are %s", Arrays.toString(AccountStatusEnum.values()));
            }else if (originalMessage.contains(AccountTypeEnum.class.getSimpleName())){
                return String.format("Invalid account type - Accepted values are %s", Arrays.toString(AccountTypeEnum.values()));
            }else if (originalMessage.contains(MovementTypeEnum.class.getSimpleName())){
                return String.format("Invalid movement type - Accepted values are %s", Arrays.toString(MovementTypeEnum.values()));
            }else{
                return originalMessage;
            }
        }else{
            return originalMessage;
        }
    }

}
