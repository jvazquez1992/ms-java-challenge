package com.devsu.challenge.msjavachallengeclient.service.impl;

import com.devsu.challenge.msjavachallengeclient.domain.ClientEntity;
import com.devsu.challenge.msjavachallengeclient.exception.AlreadyExistsEntityException;
import com.devsu.challenge.msjavachallengeclient.exception.NotFoundEntityException;
import com.devsu.challenge.msjavachallengeclient.models.*;
import com.devsu.challenge.msjavachallengeclient.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.*;


@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {

    @InjectMocks
    ClientServiceImpl clientService;

    @Mock
    ClientRepository clientRepository;

    @Test
    void shouldGetClientWhenClientExists() {
        when(clientRepository.findByIdentification(anyString())).thenReturn(Optional.of(mock(ClientEntity.class)));
        GetClientResponse getClientResponse = clientService.getClient("personIdentification");
        assertNotNull(getClientResponse);
    }

    @Test
    void shouldThrowErrorWhenClientDoNotExists(){
        when(clientRepository.findByIdentification(anyString())).thenReturn(Optional.empty());
        assertThatExceptionOfType(NotFoundEntityException.class)
                .isThrownBy(() -> clientService.getClient("personIdentification"));
    }

    @Test
    void shouldDeleteClient() {
        when(clientRepository.findByIdentification(anyString())).thenReturn(Optional.of(mock(ClientEntity.class)));
        clientService.deleteClient("personIdentification");
        verify(clientRepository,times(1)).deleteById(anyLong());
    }

    @Test
    void shouldThrowErrorWhenDeleteClientNotExists() {
        when(clientRepository.findByIdentification(anyString())).thenReturn(Optional.empty());
        assertThatExceptionOfType(NotFoundEntityException.class)
                .isThrownBy(() -> clientService.deleteClient("personIdentification"));
    }

    @Test
    void shouldPostClient() {
        when(clientRepository.findByIdentification(anyString())).thenReturn(Optional.empty());
        PostClientResponse postClientResponse = clientService.postClient(postClientRequest());
        assertNotNull(postClientResponse);
    }

    @Test
    void shouldThrowErrorPostClientWhenClientExists() {
        when(clientRepository.findByIdentification(anyString())).thenReturn(Optional.of(mock(ClientEntity.class)));
        assertThatExceptionOfType(AlreadyExistsEntityException.class)
                .isThrownBy(() -> clientService.postClient(postClientRequest()));
    }

    @Test
    void shouldThrowErrorPostClientWhenInvalidGender() {
        when(clientRepository.findByIdentification(anyString())).thenReturn(Optional.empty());
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> clientService.postClient(postClientRequest().gender("GENERO NO VALIDO")));
    }

    @Test
    void shouldPutClient() {
        when(clientRepository.findByIdentification(anyString())).thenReturn(Optional.of(mock(ClientEntity.class)));
        PutClientResponse putClientResponse = clientService.putClient("personIdentification", putClientRequest());
        assertNotNull(putClientResponse);
    }

    @Test
    void shouldThrowErrorPutClientWhenClientNotExists(){
        when(clientRepository.findByIdentification(anyString())).thenReturn(Optional.empty());
        assertThatExceptionOfType(NotFoundEntityException.class)
                .isThrownBy(() -> clientService.putClient("personIdentification",putClientRequest()));
    }

    private PostClientRequest postClientRequest(){
        return new PostClientRequest().identification("identification");
    }

    private PutClientRequest putClientRequest(){
        return new PutClientRequest().phone("123456789");
    }

}