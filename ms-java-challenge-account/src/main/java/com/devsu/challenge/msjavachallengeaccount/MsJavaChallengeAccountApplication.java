package com.devsu.challenge.msjavachallengeaccount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;

@EnableFeignClients
@SpringBootApplication
public class MsJavaChallengeAccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsJavaChallengeAccountApplication.class, args);
	}

}
