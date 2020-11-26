package com.nbastat.springboot.nbaStatistics;

import com.nbastat.springboot.nbaStatistics.service.PlayerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories
@EnableConfigurationProperties
public class NbaStatisticsApplication {

	public static void main(String[] args) {
		SpringApplication.run(NbaStatisticsApplication.class, args);
	}

}
