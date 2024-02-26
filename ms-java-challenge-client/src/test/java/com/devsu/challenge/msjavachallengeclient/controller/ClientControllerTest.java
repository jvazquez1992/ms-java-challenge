package com.devsu.challenge.msjavachallengeclient.controller;

import com.devsu.challenge.msjavachallengeclient.models.*;
import com.devsu.challenge.msjavachallengeclient.service.ClientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class ClientControllerTest {

    @InjectMocks
    ClientController clientController;
    @Mock
    ClientService clientService;

    @Test
    void shouldGetClient() throws Exception {
        when(clientService.getClient(anyString())).thenReturn(mock(GetClientResponse.class));
        ResponseEntity<GetClientResponse> response = clientController.getClient("personIdentification");
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void shouldDeleteClient() throws Exception {
        doNothing().when(clientService).deleteClient("personIdentification");
        ResponseEntity<Void> response = clientController.deleteClient("personIdentification");
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void shouldPostClient() throws Exception {
        when(clientService.postClient(any(PostClientRequest.class))).thenReturn(mock(PostClientResponse.class));
        ResponseEntity<PostClientResponse> response = clientController.postClient(mock(PostClientRequest.class));
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void shouldPutClient() throws Exception {
        when(clientService.putClient(anyString(),any(PutClientRequest.class))).thenReturn(mock(PutClientResponse.class));
        ResponseEntity<PutClientResponse> response = clientController.putClient("personIdentification", mock(PutClientRequest.class));
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertNotNull(response.getBody());
    }
}