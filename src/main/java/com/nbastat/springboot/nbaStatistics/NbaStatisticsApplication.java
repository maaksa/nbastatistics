package com.nbastat.springboot.nbaStatistics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class NbaStatisticsApplication {

	public static void main(String[] args) {
		SpringApplication.run(NbaStatisticsApplication.class, args);
	}


}
