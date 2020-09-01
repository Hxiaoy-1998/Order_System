package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan("com.example.*")
@SpringBootApplication
public class TesthelloApplication {

	public static void main(String[] args) {
		SpringApplication.run(TesthelloApplication.class, args);
	}

}
