package com.devsu.challenge.msjavachallengeclient.repository;

import com.devsu.challenge.msjavachallengeclient.domain.ClientEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends BaseRepository<ClientEntity> {
    Optional<ClientEntity> findByIdentification(String identification);

}
