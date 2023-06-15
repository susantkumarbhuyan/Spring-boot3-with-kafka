package com.hs.kafka;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.hs.pojo.DemoKafkaEvent;
import com.hs.pojo.HSConstants;

@Service
public class HsKafkaProducer {

//	@Value("${spring.kafka.topic-json.name}")
//	private String topicJsonName;

	private static final Logger LOGGER = LoggerFactory.getLogger(HsKafkaProducer.class);
	@Autowired
	private KafkaTemplate<String, DemoKafkaEvent> kafkaTemplate;

	public String produceEvent(DemoKafkaEvent demoKafkaEvent) {
		// create message
		Message<DemoKafkaEvent> message = MessageBuilder.withPayload(demoKafkaEvent)
				.setHeader(KafkaHeaders.TOPIC, HSConstants.TOPIC_NAME).build();
		CompletableFuture<SendResult<String, DemoKafkaEvent>> future = kafkaTemplate.send(message);
		future.whenComplete((result, ex) -> {
			if (ex == null) {
				LOGGER.debug(
						"Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]");
			} else {
				LOGGER.error("Unable to send message=[" + message + "] due to : " + ex.getMessage());
			}
		});
		return "Produced Event Success";
	}
}
