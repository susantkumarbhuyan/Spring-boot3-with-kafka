package com.hs.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.hs.pojo.DemoKafkaEvent;
import com.hs.pojo.HSConstants;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

	@Bean
	ConsumerFactory<String, DemoKafkaEvent> consumerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, HSConstants.BOOTSTRAP_ADDRESS);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, HSConstants.GROUP_ID);
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		 props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
		return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>());
	}

	@Bean
	ConcurrentKafkaListenerContainerFactory<String, DemoKafkaEvent> kafkaListenerContainerFactory() {

		ConcurrentKafkaListenerContainerFactory<String, DemoKafkaEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		factory.setConcurrency(3);
        factory.getContainerProperties().setPollTimeout(3000);
		return factory;
	}
}