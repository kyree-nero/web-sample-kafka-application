package com.example.app.services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MessageReceivingService {
	private String lastMessageReceived;
	
	
	@KafkaListener(topics = "${kafka.stuff.topic}", groupId = "samples-groupid")
	public void listenGroupFoo(String message) {
		lastMessageReceived = message;
	    System.out.println("Received Message in group foo: " + message);
	}


	public String getLastMessageReceived() {
		return lastMessageReceived;
	}


	public void setLastMessageReceived(String lastMessageReceived) {
		this.lastMessageReceived = lastMessageReceived;
	}
	
	
}
