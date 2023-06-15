package com.hs.config;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.mapping.DefaultJackson2JavaTypeMapper;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.hs.pojo.DemoKafkaEvent;
import com.hs.pojo.HSConstants;

@Configuration
public class KafkaProducerConfig {
	
	@Bean
	ProducerFactory<String, DemoKafkaEvent> producerFactory() {
		Map<String, Object> configProps = new HashMap<>();
		configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, HSConstants.BOOTSTRAP_ADDRESS);
		configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		configProps.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, "20971520");
		return new DefaultKafkaProducerFactory<>(configProps, new StringSerializer(), new JsonSerializer<>());
	}

	@Bean
	KafkaTemplate<String, DemoKafkaEvent> jsonKafkaTemplate() {
		return new KafkaTemplate<String, DemoKafkaEvent>(producerFactory());
	}
}
