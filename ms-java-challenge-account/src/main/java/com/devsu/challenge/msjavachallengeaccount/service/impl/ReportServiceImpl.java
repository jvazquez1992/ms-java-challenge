package com.devsu.challenge.msjavachallengeaccount.service.impl;

import com.devsu.challenge.msjavachallengeaccount.domain.AccountEntity;
import com.devsu.challenge.msjavachallengeaccount.domain.MovementEntity;
import com.devsu.challenge.msjavachallengeaccount.models.GetReportResponse;
import com.devsu.challenge.msjavachallengeaccount.service.AccountService;
import com.devsu.challenge.msjavachallengeaccount.service.ClientService;
import com.devsu.challenge.msjavachallengeaccount.service.MovementService;
import com.devsu.challenge.msjavachallengeaccount.service.ReportService;
import com.devsu.challenge.msjavachallengeaccount.service.dto.GetClientResponse;
import com.devsu.challenge.msjavachallengeaccount.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ClientService clientService;
    private final AccountService accountService;
    private final MovementService movementService;

    @Override
    @Transactional(readOnly = true)
    public List<GetReportResponse> getReport(Date startDate, Date endDate, String personIdentification, String accountNumber){
        List<GetReportResponse> response = new ArrayList<>();
        GetClientResponse getClientResponse = clientService.getClient(personIdentification);
        List<AccountEntity> accounts = accountService.getAccountEntities(getClientResponse, accountNumber);
        accounts.forEach(getAccountResponse -> {
            List<MovementEntity> movements = movementService.findMovementsByAccountInDateRange(getAccountResponse.getAccountId(),startDate,endDate);
            GetReportResponse getReportResponse = Mapper.INSTANCE.toGetReportResponse(getClientResponse,getAccountResponse);
            getReportResponse.setDetails(movements.stream().map(Mapper.INSTANCE::toGetReportDetailsResponse).collect(Collectors.toList()));
            response.add(getReportResponse);
        });
        return response;
    }

}
