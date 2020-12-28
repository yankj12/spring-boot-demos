package com.yan.redisson.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("TaskRedissonController")
class RedissonControllerTest {

	@Autowired
    private MockMvc mockMvc;
	
	//@Test
	void testRedissonTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/redisson/save/redis_lock_student").accept(MediaType.APPLICATION_JSON))
        	.andExpect(MockMvcResultMatchers.status().isOk())
        	.andDo(MockMvcResultHandlers.print())
        	.andReturn();
	}
	
	@Test
	void testGetUser() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/redisson/get/zhagn_san").accept(MediaType.APPLICATION_JSON))
        	.andExpect(MockMvcResultMatchers.status().isOk())
        	.andDo(MockMvcResultHandlers.print())
        	.andReturn();
	}

	@Test
	void testGetFlag() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/redisson/getFlag/LOG_CONFIG").accept(MediaType.APPLICATION_JSON))
        	.andExpect(MockMvcResultMatchers.status().isOk())
        	.andDo(MockMvcResultHandlers.print())
        	.andReturn();
	}
}
