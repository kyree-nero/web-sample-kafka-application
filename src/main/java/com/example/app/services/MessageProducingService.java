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
		private String data;


		public void setData(String data) {
			this.data = data;
		}


		public String getData() {
			return data;
		}
		
		
	}
	
	@Autowired KafkaTemplate<String, String> kafkaTemplate;
	@Value(value = "${kafka.stuff.topic}") String stuffTopic;
	
	public Content doStuff() {
		
		LocalDateTime now = LocalDateTime.now();
		String s = "doStuff..." + now.toString();
		Content c = new Content();
		c.setData(s);
		kafkaTemplate.send(stuffTopic, c.getData());
		return c;
	}
}	
