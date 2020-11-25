package com.nbastat.springboot.nbaStatistics;

import com.nbastat.springboot.nbaStatistics.controller.PlayerController;
import com.nbastat.springboot.nbaStatistics.entity.Player;
import com.nbastat.springboot.nbaStatistics.loader.Loader;
import com.nbastat.springboot.nbaStatistics.repositories.PlayerRepository;
import com.nbastat.springboot.nbaStatistics.service.PlayerService;
import com.nbastat.springboot.nbaStatistics.service.PlayerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
@EnableJpaRepositories
public class NbaStatisticsApplication {

	@Autowired
	static private PlayerServiceImpl service;

	public static void main(String[] args) {
		SpringApplication.run(NbaStatisticsApplication.class, args);
	}

}
