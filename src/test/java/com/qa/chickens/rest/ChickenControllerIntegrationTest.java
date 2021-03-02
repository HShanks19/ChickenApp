package com.qa.chickens.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.chickens.domain.Chicken;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:chicken-schema.sql", "classpath:chicken-data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class ChickenControllerIntegrationTest {

		@Autowired 
		private MockMvc mockMVC;
		
		@Autowired
		private ObjectMapper mapper;
		// exact same object mapper as Spring. Advantage: Test gives same results.
		
		private long id = 1L;
		
		private final Chicken testChickenFromDB = new Chicken(this.id, "Henry", 4, "White", "Canadian Chicken");
		
		@Test
		void testCreate() throws Exception {
			
			Chicken newChicken = new Chicken ("Henry", 2, "White", "Normal Chicken");
			
			String newChickenAsJSON = this.mapper.writeValueAsString(newChicken);
			
			RequestBuilder mockRequest = post("/createChicken").contentType(MediaType.APPLICATION_JSON).content(newChickenAsJSON);
			
			Chicken savedChicken = new Chicken(3L, "Henry", 2, "White", "Normal Chicken");
			
			String savedChickenAsJSON = this.mapper.writeValueAsString(savedChicken);
			
			// create the "saved" chicken
			ResultMatcher matchStatus = status().isCreated();
			ResultMatcher matchBody = content().json(savedChickenAsJSON);
			this.mockMVC.perform(mockRequest).andExpect(matchStatus).andExpect(matchBody);
		}

		@Test
		void readTest() throws Exception {
			List<Chicken> allChickens = List.of(testChickenFromDB);
			String testChickenAsJSON = this.mapper.writeValueAsString(allChickens);

			RequestBuilder mockRequest = get("/getChickens");

			ResultMatcher checkStatus = status().isOk();
			ResultMatcher checkBody = content().json(testChickenAsJSON);

			this.mockMVC.perform(mockRequest).andExpect(checkStatus).andExpect(checkBody);
		}
		
		@Test
		void testDelete() throws Exception {
			
			RequestBuilder mockRequest = delete("/removeChicken/" + this.testChickenFromDB.getId());
			
			ResultMatcher checkStatus = status().isNoContent();
			
			this.mockMVC.perform(mockRequest).andExpect(checkStatus);
		}
		
		@Test
		void testUpdate() throws Exception {
			
			long updateId = 2L;
			
			Chicken newChicken = new Chicken("Holly", 3, "Brown","British Chicken");
			
			Chicken updatedChicken = new Chicken (updateId, newChicken.getName(), newChicken.getAge(), newChicken.getColour(), newChicken.getBreed());
			
			String newChickenAsJSON = this.mapper.writeValueAsString(newChicken);
			
			RequestBuilder mockRequest = put("/updateChicken/" + updateId).contentType(MediaType.APPLICATION_JSON).content(newChickenAsJSON);
			
			ResultMatcher checkStatus = status().isAccepted();
			ResultMatcher checkBody = content().json(this.mapper.writeValueAsString(updatedChicken));
			
			this.mockMVC.perform(mockRequest).andExpect(checkStatus).andExpect(checkBody);
		}
		
}
