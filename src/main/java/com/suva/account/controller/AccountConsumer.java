package com.suva.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.suva.account.model.AccountBalance;
import com.suva.account.model.AccountTransaction;
import com.suva.account.service.AccountService;
import com.suva.account.utils.CommonUtils;

@RestController
public class AccountConsumer {
	
	@Autowired
	AccountService accountService;

	@KafkaListener(topics = {CommonUtils.ACCT_BALANCE_TOPIC}, containerFactory = "acctBalListenerFactory")
	public void consumeAccountBalance(@RequestBody AccountBalance accountBalance) {
		System.out.println("Kafka event consumed is: " + accountBalance);
		accountService.saveAccountBalance(accountBalance);
    }
	
	@KafkaListener(topics = {CommonUtils.ACCT_TXN_TOPIC}, containerFactory = "acctTxnListernerFactory")
	public void consumeAccountTxn(@RequestBody AccountTransaction accountTransaction) {
		System.out.println("Kafka txn event consumed is: " + accountTransaction);
		accountService.saveAccounTxn(accountTransaction);
    }	
	
}
