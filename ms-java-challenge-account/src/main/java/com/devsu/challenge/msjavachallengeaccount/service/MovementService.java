package com.devsu.challenge.msjavachallengeaccount.service;

import com.devsu.challenge.msjavachallengeaccount.domain.MovementEntity;
import com.devsu.challenge.msjavachallengeaccount.models.*;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface MovementService {
    @Transactional
    void deleteByAccount(Long accountId);

    @Transactional(readOnly = true)
    GetMovementResponse getMovement(BigDecimal movementId);

    @Transactional
    PostMovementResponse postMovement(PostMovementRequest postMovementRequest);

    @Transactional
    PutMovementResponse putMovement(BigDecimal movementId, PutMovementRequest putMovementRequest);

    @Transactional
    void deleteMovement(BigDecimal movementId);

    @Transactional(readOnly = true)
    List<MovementEntity> findMovementsByAccountInDateRange(Long accountId, Date startDate, Date endDate);
}
