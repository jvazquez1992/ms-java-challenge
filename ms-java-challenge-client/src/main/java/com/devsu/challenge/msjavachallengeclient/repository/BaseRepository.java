package com.devsu.challenge.msjavachallengeclient.repository;

import com.devsu.challenge.msjavachallengeclient.domain.PersonEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T extends PersonEntity> extends CrudRepository<T,Long> {
}
