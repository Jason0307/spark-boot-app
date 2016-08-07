package org.zhubao.spark.bootapp;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.zhubao.spark.bootapp.config.ConnectionSettings;

@SpringBootApplication
public class SparkBootAppApplication {

	@Autowired
	private ConnectionSettings settings;
	@PostConstruct
	public void logProperty() {
//		settings.setUsername("zhubao");
		String username = settings.getUsername();
		System.out.println(username);
	}
	public static void main(String[] args) {
		SpringApplication.run(SparkBootAppApplication.class, args);
	}
}
