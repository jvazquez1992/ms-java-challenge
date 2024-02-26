package com.devsu.challenge.msjavachallengeaccount.service;

import com.devsu.challenge.msjavachallengeaccount.service.dto.GetClientResponse;

public interface ClientService {
    GetClientResponse getClient(String personIdentification);
}
