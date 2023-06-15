package com.hs.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hs.pojo.DemoKafkaEvent;

@RestController
@RequestMapping("/producer")
public class HsProducerController {
	@Autowired
	private HsKafkaProducer hsKafkaProducer;

	@PostMapping("/event")
	public String produceEvent(@RequestBody DemoKafkaEvent demoKafkaEvent) {
		return hsKafkaProducer.produceEvent(demoKafkaEvent);
	}
	
	@GetMapping("/get")
	public String getMethod() {
		return "hii";
	}
}
