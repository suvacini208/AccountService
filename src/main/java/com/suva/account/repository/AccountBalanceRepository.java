package com.suva.account.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.suva.account.model.AccountBalance;

public interface AccountBalanceRepository extends MongoRepository<AccountBalance, String> {

	
}
