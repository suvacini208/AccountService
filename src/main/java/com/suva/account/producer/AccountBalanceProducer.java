package com.suva.account.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.suva.account.model.AccountBalance;
import static com.suva.account.utils.CommonUtils.ACCT_BALANCE_TOPIC;;

@Service
public class AccountBalanceProducer {
	
	
	
	@Autowired
	private KafkaTemplate<String, AccountBalance> balKafkaTemplate;
	
	public void send(AccountBalance accountBalance) {
        Message<AccountBalance> message = MessageBuilder
                .withPayload(accountBalance)
                .setHeader(KafkaHeaders.TOPIC, ACCT_BALANCE_TOPIC)
                .build();
        
		balKafkaTemplate.send(message);
		System.out.println(String.format("Messent sent to %s successfully", ACCT_BALANCE_TOPIC));
	}

}
