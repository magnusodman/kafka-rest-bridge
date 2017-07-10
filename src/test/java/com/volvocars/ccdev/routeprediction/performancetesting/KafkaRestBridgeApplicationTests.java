package com.volvocars.ccdev.routeprediction.performancetesting;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import javax.servlet.ServletContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class KafkaRestBridgeApplicationTests {

	@Autowired
	BridgeController controller;

	@Autowired
	MockMvc mockMvc;

	@Test
	public void contextLoads() {
		assertThat(controller).isNotNull();
	}

	@Test
	public void postShouldReturnHttpOk() throws Exception {
		String vin = "YV1234567890";
		double lon = 11.8457;
		double lat = 57.7262;
		long timestamp = 1499688903114L;
		PositionMessage positionMessage = new PositionMessage(vin, lon, lat, timestamp);
		String json = new ObjectMapper().writeValueAsString(positionMessage);

		RequestBuilder requestBuilder = post("/send").contentType(MediaType.APPLICATION_JSON).content(json);
		mockMvc.perform(requestBuilder).andExpect(status().isOk());
	}

}
