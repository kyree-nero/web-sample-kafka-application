package com.example.app;

import java.time.Instant;
import java.util.Date;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.MOCK)
@ActiveProfiles("test")
@TestPropertySource(
		locations = {"/config/application-test.yml"}, 
		properties ="management.server.port= " //need this to override management port
)

public abstract class AbstractIT {
	@Autowired MockMvc mockMvc;
	@Autowired WebApplicationContext webApplicationContext;
	protected Instant startInstant = Instant.ofEpochMilli(new Date().getTime());
	
	
}
