package com.devsu.challenge.msjavachallengeaccount.controller;

import com.devsu.challenge.msjavachallengeaccount.models.*;
import com.devsu.challenge.msjavachallengeaccount.server.MovimientoApi;
import com.devsu.challenge.msjavachallengeaccount.service.MovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
public class MovementController implements MovimientoApi {

    private final MovementService movementService;

    @Override
    public ResponseEntity<Void> deleteMovement(BigDecimal movementId) throws Exception {
        movementService.deleteMovement(movementId);
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<GetMovementResponse> getMovement(BigDecimal movementId) throws Exception {
        return ResponseEntity.ok(movementService.getMovement(movementId));
    }

    @Override
    public ResponseEntity<PostMovementResponse> postMovement(PostMovementRequest postMovementRequest) throws Exception {
        return ResponseEntity.ok(movementService.postMovement(postMovementRequest));
    }

    @Override
    public ResponseEntity<PutMovementResponse> putMovement(BigDecimal movementId, PutMovementRequest putMovementRequest) throws Exception {
        return ResponseEntity.ok(movementService.putMovement(movementId,putMovementRequest));
    }
}
