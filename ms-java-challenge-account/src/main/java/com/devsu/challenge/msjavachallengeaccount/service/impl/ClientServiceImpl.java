package com.devsu.challenge.msjavachallengeaccount.service.impl;

import com.devsu.challenge.msjavachallengeaccount.repository.ClientRepository;
import com.devsu.challenge.msjavachallengeaccount.service.ClientService;
import com.devsu.challenge.msjavachallengeaccount.service.dto.GetClientResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public GetClientResponse getClient(String personIdentification){
        return clientRepository.getClient(personIdentification).getBody();
    }

}
