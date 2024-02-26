package com.devsu.challenge.msjavachallengeaccount.service;

import com.devsu.challenge.msjavachallengeaccount.domain.AccountEntity;
import com.devsu.challenge.msjavachallengeaccount.models.*;
import com.devsu.challenge.msjavachallengeaccount.service.dto.GetClientResponse;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    List<GetAccountResponse> getAccount(String personIdentification, String accountNumber);

    @Transactional
    PostAccountResponse postAccount(PostAccountRequest postAccountRequest);

    @Transactional
    PutAccountResponse putAccount(String accountNumber, PutAccountRequest putAccountRequest);

    @Transactional
    void deleteAccount(String accountNumber);

    @Transactional(readOnly = true)
    List<AccountEntity> getAccountEntities(GetClientResponse getClientResponse, String accountNumber);

    AccountEntity findByAccountNumber(String accountNumber);
}
