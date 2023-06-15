package com.hs.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import com.hs.pojo.HSConstants;

@Configuration
public class KafkaTopicConfig {

	@Bean
	NewTopic topic() {
		return TopicBuilder.name(HSConstants.TOPIC_NAME).build();
	}

	@Bean
	KafkaAdmin kafkaAdmin() {
		Map<String, Object> configs = new HashMap<>();
		configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, HSConstants.BOOTSTRAP_ADDRESS);
		return new KafkaAdmin(configs);
	}

}
