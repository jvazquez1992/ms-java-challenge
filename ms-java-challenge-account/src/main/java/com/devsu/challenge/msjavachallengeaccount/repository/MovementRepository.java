package com.devsu.challenge.msjavachallengeaccount.repository;

import com.devsu.challenge.msjavachallengeaccount.domain.MovementEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MovementRepository extends CrudRepository<MovementEntity, Long> {

    @Query("from movement where account.accountId = :accountId")
    List<MovementEntity> findByAccountId(Long accountId);

    @Query("from movement where account.accountId = :accountId and date >= :startDate and date <= :endDate")
    List<MovementEntity> findByAccountIdInRange(Long accountId, Date startDate, Date endDate);

}
