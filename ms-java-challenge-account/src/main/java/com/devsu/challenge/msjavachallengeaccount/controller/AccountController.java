package com.devsu.challenge.msjavachallengeaccount.controller;

import com.devsu.challenge.msjavachallengeaccount.models.*;
import com.devsu.challenge.msjavachallengeaccount.server.CuentaApi;
import com.devsu.challenge.msjavachallengeaccount.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountController implements CuentaApi {

    private final AccountService accountService;

    @Override
    public ResponseEntity<Void> deleteAccount(String accountNumber) throws Exception {
        accountService.deleteAccount(accountNumber);
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<List<GetAccountResponse>> getAccount(String personIdentification, String accountNumber) throws Exception {
        return ResponseEntity.ok(accountService.getAccount(personIdentification,accountNumber)
        );
    }

    @Override
    public ResponseEntity<PostAccountResponse> postAccount(PostAccountRequest postAccountRequest) throws Exception {
        return ResponseEntity.ok(accountService.postAccount(postAccountRequest));
    }

    @Override
    public ResponseEntity<PutAccountResponse> putAccount(String accountNumber, PutAccountRequest putAccountRequest) throws Exception {
        return ResponseEntity.ok(accountService.putAccount(accountNumber,putAccountRequest));
    }
}
