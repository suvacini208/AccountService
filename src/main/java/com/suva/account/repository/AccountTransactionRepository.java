package com.suva.account.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.suva.account.model.AccountTransaction;

public interface AccountTransactionRepository extends PagingAndSortingRepository<AccountTransaction, Long> {

	@Query("{'accountNumber' : ?0, 'transactionTs' : {'$gt' : ?1, '$lt' : ?2}}")
	Page<AccountTransaction> findByAccountDateRange(String accountNumber, Date startDate, Date endDate,
			Pageable pageable);
	
}
