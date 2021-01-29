package com.example.app.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageProducingService {
	
	
	public static class Content {
		private String messageData;

		public String getMessageData() {
			return messageData;
		}

		public void setMessageData(String messageData) {
			this.messageData = messageData;
		}


	
		
	}
	
	@Autowired KafkaTemplate<String, String> kafkaTemplate;
	@Value(value = "${kafka.stuff.topic}") String stuffTopic;
	
	public Content doStuff() {
		
		LocalDateTime now = LocalDateTime.now();
		String s = "doStuff..." + now.toString();
		Content c = new Content();
		c.setMessageData(s);
		kafkaTemplate.send(stuffTopic, c.getMessageData());
		return c;
	}
}	
