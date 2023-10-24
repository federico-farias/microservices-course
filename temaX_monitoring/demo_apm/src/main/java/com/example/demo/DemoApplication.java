package com.example.demo;

//import co.elastic.apm.attach.ElasticApmAttacher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		//ElasticApmAttacher.attach();
		SpringApplication.run(DemoApplication.class, args);
	}

}
