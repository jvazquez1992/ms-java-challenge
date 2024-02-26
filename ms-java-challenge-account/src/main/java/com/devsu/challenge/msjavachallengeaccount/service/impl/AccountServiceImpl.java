package com.devsu.challenge.msjavachallengeaccount.service.impl;

import com.devsu.challenge.msjavachallengeaccount.domain.AccountEntity;
import com.devsu.challenge.msjavachallengeaccount.exception.EntityNotFoundException;
import com.devsu.challenge.msjavachallengeaccount.exception.AlreadyExistsEntityException;
import com.devsu.challenge.msjavachallengeaccount.models.*;
import com.devsu.challenge.msjavachallengeaccount.repository.AccountRepository;
import com.devsu.challenge.msjavachallengeaccount.service.AccountService;
import com.devsu.challenge.msjavachallengeaccount.service.ClientService;
import com.devsu.challenge.msjavachallengeaccount.service.MovementService;
import com.devsu.challenge.msjavachallengeaccount.service.dto.GetClientResponse;
import com.devsu.challenge.msjavachallengeaccount.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.devsu.challenge.msjavachallengeaccount.util.ClassManagementUtil.updateFields;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final ClientService clientService;
    private final MovementService movementService;
    private final AccountRepository accountRepository;

    @Override
    @Transactional(readOnly = true)
    public List<GetAccountResponse> getAccount(String personIdentification, String accountNumber) {
        List<AccountEntity> getAccountResponses = new ArrayList<>();
        GetClientResponse getClientResponse;
        if(Objects.nonNull(personIdentification)){
            getClientResponse = clientService.getClient(personIdentification);
            if(Objects.nonNull(accountNumber)){
                getAccountResponses.add(findByAccountNumberAndPerson(getClientResponse,accountNumber));
            }else{
                getAccountResponses.addAll(findByPersonId(getClientResponse));
            }
        }else{
            getClientResponse = null;
            getAccountResponses.add(findByAccountNumber(accountNumber));
        }
        return getAccountResponses.stream().map(account -> Mapper.INSTANCE.toGetAccountResponse(account, getClientResponse)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PostAccountResponse postAccount(PostAccountRequest postAccountRequest){
        AccountEntity account = verifyAccountNumber(postAccountRequest.getAccountNumber());
        if(Objects.nonNull(account)){
            throw new AlreadyExistsEntityException(String.format("Account with account number %s already exits", postAccountRequest.getAccountNumber()));
        }else{
            GetClientResponse client = clientService.getClient(postAccountRequest.getPersonIdentification());
            AccountEntity accountToSave = Mapper.INSTANCE.toAccountEntity(postAccountRequest, client);
            return Mapper.INSTANCE.toPostAccountResponse(accountRepository.save(accountToSave));
        }
    }
    @Override
    @Transactional
    public PutAccountResponse putAccount(String accountNumber, PutAccountRequest putAccountRequest){
        AccountEntity accountEntity = findByAccountNumber(accountNumber);
        updateFields(accountEntity,putAccountRequest);
        return Mapper.INSTANCE.toPutAccountResponse(accountRepository.save(accountEntity));
    }

    @Override
    @Transactional
    public void deleteAccount(String accountNumber){
        AccountEntity account = findByAccountNumber(accountNumber);
        movementService.deleteByAccount(account.getAccountId());
        accountRepository.deleteById(account.getAccountId());
    }

    private AccountEntity findByAccountNumberAndPerson(GetClientResponse getClientResponse, String accountNumber){
        return accountRepository
                .findByAccountNumberAndPersonId(accountNumber, getClientResponse.getPersonId())
                .orElseThrow(() -> new EntityNotFoundException(String.format("La cuenta %s no pertenece al cliente %s", accountNumber, getClientResponse.getIdentification())));
    }

    private List<AccountEntity> findByPersonId(GetClientResponse getClientResponse){
        List<AccountEntity> accountEntities =accountRepository.findByPersonId(getClientResponse.getPersonId());
        if(accountEntities.isEmpty()){
            throw new EntityNotFoundException(String.format("No se encontraron cuentas para el usuario con identificación %s", getClientResponse.getIdentification()));
        }
        return accountEntities;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccountEntity> getAccountEntities(GetClientResponse getClientResponse, String accountNumber) {
        List<AccountEntity> getAccountResponses = new ArrayList<>();
        if(Objects.nonNull(getClientResponse)){
            if(Objects.nonNull(accountNumber)){
                getAccountResponses.add(findByAccountNumberAndPerson(getClientResponse,accountNumber));
            }else{
                getAccountResponses.addAll(findByPersonId(getClientResponse));
            }
        }else{
            getAccountResponses.add(findByAccountNumber(accountNumber));
        }
        return getAccountResponses;
    }

    @Override
    public AccountEntity findByAccountNumber(String accountNumber){
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new EntityNotFoundException(String.format("La cuenta %s no existe", accountNumber)));
    }

    private AccountEntity verifyAccountNumber(String accountNumber){
        return accountRepository.findByAccountNumber(accountNumber).orElse(null);
    }

}
