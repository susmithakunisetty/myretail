package com.target.casestudy.myretail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


@EnableFeignClients(basePackages = {"com.target.casestudy.myretail.remoteclient", "com.target.casestudy.myretail.service"})
@SpringBootApplication
@ComponentScan({ "com.target.casestudy.myretail.*" })
public class MyretailApplication {

	public static void main(String[] args) {

		SpringApplication.run(MyretailApplication.class, args);
		System.out.println("Hello World");
	}

}
