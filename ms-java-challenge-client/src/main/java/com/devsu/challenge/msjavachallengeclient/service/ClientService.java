package com.devsu.challenge.msjavachallengeclient.service;

import com.devsu.challenge.msjavachallengeclient.models.*;
import org.springframework.transaction.annotation.Transactional;

public interface ClientService {
    GetClientResponse getClient(String personIdentification);

    void deleteClient(String personIdentification);

    @Transactional
    PostClientResponse postClient(PostClientRequest postClientRequest);

    PutClientResponse putClient(String personIdentification, PutClientRequest putClientRequest);
}
