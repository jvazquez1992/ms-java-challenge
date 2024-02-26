package com.devsu.challenge.msjavachallengeclient.service.impl;

import com.devsu.challenge.msjavachallengeclient.domain.ClientEntity;
import com.devsu.challenge.msjavachallengeclient.domain.enums.ClientStatusEnum;
import com.devsu.challenge.msjavachallengeclient.exception.AlreadyExistsEntityException;
import com.devsu.challenge.msjavachallengeclient.exception.NotFoundEntityException;
import com.devsu.challenge.msjavachallengeclient.models.*;
import com.devsu.challenge.msjavachallengeclient.repository.ClientRepository;
import com.devsu.challenge.msjavachallengeclient.service.ClientService;
import com.devsu.challenge.msjavachallengeclient.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

import static com.devsu.challenge.msjavachallengeclient.util.ClassManagementUtil.updateFields;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    @Override
    @Transactional(readOnly = true)
    public GetClientResponse getClient(String personIdentification){
        ClientEntity clientEntity = findClient(personIdentification);
        return Mapper.INSTANCE.toGetClientResponse(clientEntity);
    }

    @Override
    @Transactional
    public void deleteClient(String personIdentification){
        ClientEntity clientEntity = findClient(personIdentification);
        clientRepository.deleteById(clientEntity.getPersonId());
    }

    @Override
    @Transactional
    public PostClientResponse postClient(PostClientRequest postClientRequest){
        ClientEntity personEntity = verifyClient(postClientRequest.getIdentification());
        if(Objects.nonNull(personEntity)){
            throw new AlreadyExistsEntityException(String.format("Client with id %s already exits", postClientRequest.getIdentification()));
        }else{
            ClientEntity clientEntity = saveClient(Mapper.INSTANCE.toClientEntityFromRequest(postClientRequest, ClientStatusEnum.ENABLED.name()));
            return Mapper.INSTANCE.toPostClientResponse(clientEntity);
        }
    }

    @Override
    public PutClientResponse putClient(String personIdentification, PutClientRequest putClientRequest){
        ClientEntity clientEntity = findClient(personIdentification);
        updateFields(clientEntity, putClientRequest);
        return Mapper.INSTANCE.toPutClientResponse(clientRepository.save(clientEntity));
    }

    private ClientEntity findClient(String personIdentification){
        return clientRepository.findByIdentification(personIdentification).orElseThrow(() -> new NotFoundEntityException(String.format("Client with id %s not found", personIdentification)));
    }
    private ClientEntity verifyClient(String personIdentification){
        return clientRepository.findByIdentification(personIdentification).orElse(null);
    }
    private ClientEntity saveClient(ClientEntity clientEntity){
        return clientRepository.save(clientEntity);
    }

}
