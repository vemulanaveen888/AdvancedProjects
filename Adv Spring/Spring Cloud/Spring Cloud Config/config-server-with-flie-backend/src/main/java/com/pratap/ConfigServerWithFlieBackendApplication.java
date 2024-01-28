package com.pratap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigServerWithFlieBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerWithFlieBackendApplication.class, args);
	}

}
