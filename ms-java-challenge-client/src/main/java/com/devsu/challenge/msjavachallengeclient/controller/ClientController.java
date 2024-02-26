package com.devsu.challenge.msjavachallengeclient.controller;

import com.devsu.challenge.msjavachallengeclient.models.*;
import com.devsu.challenge.msjavachallengeclient.server.ClienteApi;
import com.devsu.challenge.msjavachallengeclient.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class ClientController implements ClienteApi {
    private final ClientService clientService;
    @Override
    public ResponseEntity<GetClientResponse> getClient(String personIdentification) throws Exception {
        GetClientResponse getClientResponse = clientService.getClient(personIdentification);
        return ResponseEntity.ok(getClientResponse);
    }

    @Override
    public ResponseEntity<Void> deleteClient(String personIdentification) throws Exception {
        clientService.deleteClient(personIdentification);
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<PostClientResponse> postClient(PostClientRequest postClientRequest) throws Exception {
        PostClientResponse postClientResponse = clientService.postClient(postClientRequest);
        return ResponseEntity.ok(postClientResponse);
    }

    @Override
    public ResponseEntity<PutClientResponse> putClient(String personIdentification, PutClientRequest putClientRequest) throws Exception {
        PutClientResponse putClientResponse = clientService.putClient(personIdentification,putClientRequest);
        return ResponseEntity.ok(putClientResponse);
    }
}
