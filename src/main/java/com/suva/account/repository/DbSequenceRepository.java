package com.suva.account.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.suva.account.model.DbSequence;

public interface DbSequenceRepository extends MongoRepository<DbSequence, String>{

}
