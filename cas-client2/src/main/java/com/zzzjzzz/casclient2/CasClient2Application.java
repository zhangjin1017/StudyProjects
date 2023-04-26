package com.zzzjzzz.casclient2;

import net.unicon.cas.client.configuration.EnableCasClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableCasClient
public class CasClient2Application {

	public static void main(String[] args) {
		SpringApplication.run(CasClient2Application.class, args);
	}

}
