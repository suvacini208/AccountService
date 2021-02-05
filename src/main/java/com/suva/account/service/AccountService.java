package com.suva.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.suva.account.model.AccountBalance;
import com.suva.account.model.AccountTransaction;
import com.suva.account.model.AccountTxnRequest;
import com.suva.account.model.DbSequence;
import com.suva.account.repository.AccountBalanceRepository;
import com.suva.account.repository.AccountTransactionRepository;
import com.suva.account.repository.DbSequenceRepository;

@Service
public class AccountService {

	@Autowired
	private AccountBalanceRepository accountBalanceRepository;

	@Autowired
	private AccountTransactionRepository actTxnRepo;

	@Autowired
	private DbSequenceRepository dbSeqRepo;

	public void saveAccountBalance(AccountBalance accountBalance) {
		accountBalanceRepository.save(accountBalance);
	}

	public AccountBalance getAccountBalance(String accountNumber) {
		return accountBalanceRepository.findById(accountNumber).orElse(new AccountBalance());
	}

	public Page<AccountTransaction> getAccountTransaction(AccountTxnRequest acctTxnRequest, Pageable pageable) {
		/*System.out.print("Start date :" + acctTxnRequest.getStartDt().atZone(ZoneId.systemDefault()).toString());
		System.out.print("End date :" + acctTxnRequest.getEndDt().atZone(ZoneId.systemDefault()).toString());
		return actTxnRepo.findByAccountDateRange(acctTxnRequest.getAccountNumber(),
				acctTxnRequest.getStartDt().atZone(ZoneId.systemDefault()).toString(), acctTxnRequest.getEndDt().atZone(ZoneId.systemDefault()).toString(), pageable);*/
		
		return actTxnRepo.findByAccountDateRange(acctTxnRequest.getAccountNumber(),
				acctTxnRequest.getStartDt(), acctTxnRequest.getEndDt(), pageable);
	}

	public void saveAccounTxn(AccountTransaction accountTransaction) {
		long seqId = getSequence();
		accountTransaction.setId(seqId);
		actTxnRepo.save(accountTransaction);
	}

	private long getSequence() {
		DbSequence dbSeq = dbSeqRepo.findById(AccountTransaction.DB_SEQ).orElse(new DbSequence());
		long seqId = dbSeq.getSeq() + 1;
		dbSeq.setSeq(seqId);
		dbSeq.setId(AccountTransaction.DB_SEQ);
		dbSeqRepo.save(dbSeq);

		return seqId;
	}
}
