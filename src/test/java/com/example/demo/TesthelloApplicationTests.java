package com.example.demo;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class TesthelloApplicationTests {
	
	
	@Autowired
	private RedisTemplate redistemplate;
	
	@Test
	void contextLoads() {
		
		
	}

}
