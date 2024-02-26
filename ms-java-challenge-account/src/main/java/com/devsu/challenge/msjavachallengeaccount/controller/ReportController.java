package com.devsu.challenge.msjavachallengeaccount.controller;

import com.devsu.challenge.msjavachallengeaccount.models.GetReportResponse;
import com.devsu.challenge.msjavachallengeaccount.server.ReportesApi;
import com.devsu.challenge.msjavachallengeaccount.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReportController implements ReportesApi {

    private final ReportService reportService;

    @Override
    public ResponseEntity<List<GetReportResponse>> getReport(Date startDate, Date endDate, String personIdentification, String accountNumber) throws Exception {
        return ResponseEntity.ok(reportService.getReport(startDate,endDate,personIdentification,accountNumber));
    }
}
