package com.hs.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.hs.pojo.DemoKafkaEvent;
import com.hs.pojo.HSConstants;

@Service
public class HsKafkaConsumer {
	private static final Logger LOGGER = LoggerFactory.getLogger(HsKafkaConsumer.class);

	@KafkaListener(topics = HSConstants.TOPIC_NAME, groupId = HSConstants.GROUP_ID)
	public void consumeEvent(DemoKafkaEvent demoKafkaEvent) {
		LOGGER.info(String.format("Json message recieved -> %s", demoKafkaEvent.toString()));
	}
}
