package com.devsu.challenge.msjavachallengeaccount.service.impl;

import com.devsu.challenge.msjavachallengeaccount.domain.AccountEntity;
import com.devsu.challenge.msjavachallengeaccount.domain.MovementEntity;
import com.devsu.challenge.msjavachallengeaccount.domain.enums.MovementTypeEnum;
import com.devsu.challenge.msjavachallengeaccount.exception.EntityNotFoundException;
import com.devsu.challenge.msjavachallengeaccount.exception.UnProcessableEntityException;
import com.devsu.challenge.msjavachallengeaccount.models.*;
import com.devsu.challenge.msjavachallengeaccount.repository.AccountRepository;
import com.devsu.challenge.msjavachallengeaccount.repository.MovementRepository;
import com.devsu.challenge.msjavachallengeaccount.service.ClientService;
import com.devsu.challenge.msjavachallengeaccount.service.MovementService;
import com.devsu.challenge.msjavachallengeaccount.service.dto.GetClientResponse;
import com.devsu.challenge.msjavachallengeaccount.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static com.devsu.challenge.msjavachallengeaccount.util.ClassManagementUtil.updateFields;

@Service
@RequiredArgsConstructor
public class MovementServiceImpl implements MovementService {

    private final ClientService clientService;
    private final AccountRepository accountRepository;
    private final MovementRepository movementRepository;

    @Override
    @Transactional
    public void deleteByAccount(Long accountId){
        List<MovementEntity> movements = movementRepository.findByAccountId(accountId);
        movements.forEach(movementEntity -> movementRepository.deleteById(movementEntity.getMovementId()));
    }

    @Override
    @Transactional(readOnly = true)
    public GetMovementResponse getMovement(BigDecimal movementId){
        MovementEntity movementEntity = findMovementEntity(movementId);
        return Mapper.INSTANCE.toGetMovementResponse(movementEntity);
    }

    @Override
    @Transactional
    public PostMovementResponse postMovement(PostMovementRequest postMovementRequest){
        GetClientResponse getClientResponse = clientService.getClient(postMovementRequest.getPersonIdentification());
        AccountEntity accountEntity = findByAccountNumberAndPerson(getClientResponse, postMovementRequest.getAccountNumber());
        MovementEntity movementEntity = Mapper.INSTANCE.toMovementEntity(postMovementRequest, accountEntity);
        setMovementType(movementEntity);
        validateBalance(movementEntity,accountEntity);
        BigDecimal finalAmount = calculateFinalAmount(movementEntity);
        movementEntity.setFinalBalance(finalAmount);
        updateAccountBalance(accountEntity,finalAmount);
        return Mapper.INSTANCE.toPostMovementResponse(movementRepository.save(movementEntity));
    }

    @Override
    @Transactional
    public PutMovementResponse putMovement(BigDecimal movementId, PutMovementRequest putMovementRequest){
        MovementEntity movement = findMovementEntity(movementId);
        updateFields(movement,putMovementRequest);
        return Mapper.INSTANCE.toPutMovementResponse(movementRepository.save(movement));
    }

    @Override
    @Transactional
    public void deleteMovement(BigDecimal movementId){
        MovementEntity movementEntity = findMovementEntity(movementId);
        AccountEntity accountEntity = movementEntity.getAccount();
        updateAccountBalanceByDeleteMovement(accountEntity,movementEntity);
        movementRepository.deleteById(movementEntity.getMovementId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovementEntity> findMovementsByAccountInDateRange(Long accountId, Date startDate, Date endDate){
        return movementRepository.findByAccountIdInRange(accountId,startDate,endDate);
    }

    private MovementEntity findMovementEntity(BigDecimal movementId){
        return movementRepository.findById(movementId.longValue()).orElseThrow(() -> new EntityNotFoundException("Movement not found"));
    }

    private void setMovementType(MovementEntity movementType){
        movementType.setMovementType(movementType.getAmount().compareTo(BigDecimal.ZERO)>0?MovementTypeEnum.INGRESO:MovementTypeEnum.EGRESO);
    }

    private BigDecimal calculateFinalAmount(MovementEntity movementEntity){
        if(movementEntity.getMovementType().equals(MovementTypeEnum.INGRESO)){
            return movementEntity.getMainBalance().add(movementEntity.getAmount());
        }else{
            return movementEntity.getMainBalance().subtract(movementEntity.getAmount().abs());
        }
    }
    private AccountEntity findByAccountNumberAndPerson(GetClientResponse getClientResponse, String accountNumber){
        return accountRepository
                .findByAccountNumberAndPersonId(accountNumber, getClientResponse.getPersonId())
                .orElseThrow(() -> new EntityNotFoundException(String.format("La cuenta %s no pertenece al cliente %s", accountNumber, getClientResponse.getIdentification())));
    }

    private void updateAccountBalance(AccountEntity accountEntity, BigDecimal amount){
        accountEntity.setBalance(amount);
        accountRepository.save(accountEntity);
    }

    private void updateAccountBalanceByDeleteMovement(AccountEntity account, MovementEntity movementEntity){
        if(movementEntity.getMovementType().equals(MovementTypeEnum.INGRESO)){
            account.setBalance(account.getBalance().subtract(movementEntity.getAmount().abs()));
        }else{
            account.setBalance(account.getBalance().add(movementEntity.getAmount().abs()));
        }
        accountRepository.save(account);
    }

    private void validateBalance(MovementEntity movementEntity, AccountEntity account){
        if(movementEntity.getMovementType().equals(MovementTypeEnum.EGRESO)){
            if(account.getBalance().compareTo(movementEntity.getAmount().abs())<0){
                throw new UnProcessableEntityException("Saldo no disponible");
            }
        }
    }

}
