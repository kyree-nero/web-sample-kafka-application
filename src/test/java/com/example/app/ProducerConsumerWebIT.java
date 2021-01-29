package com.example.app;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.app.services.MessageReceivingService;

public class ProducerConsumerWebIT extends AbstractWebMvcIT{
	
	@Autowired MessageReceivingService service;
	@Autowired MockMvc mockMvc;
	
	@Test public void producerConsumerTest() throws Exception{
		MvcResult result = mockMvc.perform(
				MockMvcRequestBuilders.get("/produceMessage", new Object[] {})
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				
		)
				.andDo(MockMvcResultHandlers.print())
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.messageData", Matchers.notNullValue()))
		.andReturn();
		
		String contentString = result.getResponse().getContentAsString();
		Assert.assertTrue(contentString.contains(service.getLastMessageReceived()));
	
	}
}
