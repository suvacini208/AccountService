package com.suva.account.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.suva.account.model.AccountBalance;
import com.suva.account.model.AccountTransaction;

@Configuration
@EnableKafka
public class AccountListenerConfiguration {

	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServers;

	@Bean
	public Map<String, Object> consumerConfigs() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "account");
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		return props;
	}

	/*
	 * @Bean public ConsumerFactory<String, AccountBalance> actBalanceConsumer() {
	 * return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new
	 * StringDeserializer(), new JsonDeserializer<>(AccountBalance.class)); }
	 */

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, AccountBalance> acctBalListenerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, AccountBalance> factory = 
				new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(),
				new JsonDeserializer<>(AccountBalance.class)));
		return factory;
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, AccountTransaction> acctTxnListernerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, AccountTransaction> factory = 
				new ConcurrentKafkaListenerContainerFactory<String, AccountTransaction>();
		factory.setConsumerFactory(new DefaultKafkaConsumerFactory<String, AccountTransaction>(consumerConfigs(),
				new StringDeserializer(), new JsonDeserializer<>(
						AccountTransaction.class)));
		return factory;
	}
}
