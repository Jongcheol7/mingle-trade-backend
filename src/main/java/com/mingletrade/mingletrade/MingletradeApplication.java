package com.mingletrade.mingletrade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MingletradeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MingletradeApplication.class, args);
	}

}
