package com.suva.account.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.suva.account.model.AccountTransaction;
import com.suva.account.utils.CommonUtils;

@Service
public class AccountTransactionProducer {
	
	@Autowired
	private KafkaTemplate<String, AccountTransaction> txnKafkaTemplate;
	
	public void sent(AccountTransaction accountTxn) {
        Message<AccountTransaction> message = MessageBuilder
                .withPayload(accountTxn)
                .setHeader(KafkaHeaders.TOPIC, CommonUtils.ACCT_TXN_TOPIC)
                .build();
        
        txnKafkaTemplate.send(message);
		System.out.println(String.format("Messent sent to %s successfully", CommonUtils.ACCT_TXN_TOPIC));
	}
}
