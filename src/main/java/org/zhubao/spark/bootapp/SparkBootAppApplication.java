package org.zhubao.spark.bootapp;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.zhubao.spark.bootapp.config.ConnectionSettings;
import org.zhubao.spark.bootapp.job.WordCount;

@SpringBootApplication
public class SparkBootAppApplication implements CommandLineRunner {

	@Autowired
	private ConnectionSettings settings;

	@Autowired
	private WordCount wordCount;

	@PostConstruct
	public void logProperty() {
		// settings.setUsername("zhubao");
		String username = settings.getUsername();
		System.out.println(username);
	}

	@Override
	public void run(String... args) throws Exception {
		wordCount.count();
	}

	public static void main(String[] args) {
		new SpringApplicationBuilder(SparkBootAppApplication.class).web(false).run(args);
	}
}
