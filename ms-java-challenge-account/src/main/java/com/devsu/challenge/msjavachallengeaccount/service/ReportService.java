package com.devsu.challenge.msjavachallengeaccount.service;

import com.devsu.challenge.msjavachallengeaccount.models.GetReportResponse;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface ReportService {
    @Transactional(readOnly = true)
    List<GetReportResponse> getReport(Date startDate, Date endDate, String personIdentification, String accountNumber);
}
