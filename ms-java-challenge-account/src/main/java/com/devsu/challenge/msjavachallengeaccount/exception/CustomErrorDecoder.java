package com.devsu.challenge.msjavachallengeaccount.exception;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        if (response.status() == 404) {
            return new ClientException("El cliente con la identificación proporcionada no existe");
        }
        return new ClientException("Ha ocurrido un error, vuelta a intentarlo más tarde");
    }
}
