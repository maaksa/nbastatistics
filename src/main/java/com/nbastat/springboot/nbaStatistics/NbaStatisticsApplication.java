package com.nbastat.springboot.nbaStatistics;

import loader.Loader;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NbaStatisticsApplication {

	public static void main(String[] args) {
		//SpringApplication.run(NbaStatisticsApplication.class, args);

		Loader.getInstance().loadPlayers();
	}


}
