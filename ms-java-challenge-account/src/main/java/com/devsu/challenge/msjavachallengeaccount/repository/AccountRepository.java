package com.devsu.challenge.msjavachallengeaccount.repository;

import com.devsu.challenge.msjavachallengeaccount.domain.AccountEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<AccountEntity, Long> {

    Optional<AccountEntity> findByAccountNumberAndPersonId(String accountNumber, Long personId);

    Optional<AccountEntity> findByAccountNumber(String accountNumber);

    List<AccountEntity> findByPersonId(Long personId);

}
