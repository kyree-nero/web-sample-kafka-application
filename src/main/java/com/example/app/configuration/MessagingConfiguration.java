package com.example.app.configuration;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

@Configuration
public class MessagingConfiguration {
	 @Value(value = "${kafka.bootstrapAddress}")
	    private String bootstrapAddress;
	 
	 @PostConstruct public void postConstruct() {
		 System.out.println("bootstrap address "+bootstrapAddress);
	 }
	 
	 public ConsumerFactory<String, String> consumerFactory(String groupId) {
	        Map<String, Object> props = new HashMap<>();
	        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
	        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
	        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	        return new DefaultKafkaConsumerFactory<>(props);
	    }

	    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(String groupId) {
	        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
	        factory.setConsumerFactory(consumerFactory(groupId));
	        return factory;
	    }
	    
	    @Bean
	    public ConcurrentKafkaListenerContainerFactory<String, String> fooKafkaListenerContainerFactory() {
	        return kafkaListenerContainerFactory("samples-groupid");
	    }
}
