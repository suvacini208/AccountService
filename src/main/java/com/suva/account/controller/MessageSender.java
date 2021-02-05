package com.suva.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.suva.account.model.AccountBalance;
import com.suva.account.model.AccountTransaction;
import com.suva.account.producer.AccountBalanceProducer;
import com.suva.account.producer.AccountTransactionProducer;

@RestController/*("/send")*/
public class MessageSender {

	@Autowired
	private AccountBalanceProducer balanceProducer;
	
	@Autowired
	private AccountTransactionProducer txnProducer;
	
	@PostMapping("/accountBalance")
	public void sendAccountBalance(@RequestBody AccountBalance accountBalance) {
		balanceProducer.send(accountBalance);
	}
	
	@PostMapping("/accountTransaction")
	public void sendAccountBalance(@RequestBody AccountTransaction accountTxn) {
		txnProducer.sent(accountTxn);
	}
	
}
