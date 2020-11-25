package com.nbastat.springboot.nbaStatistics;

import com.nbastat.springboot.nbaStatistics.loader.Loader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class NbaStatisticsCommandLine implements CommandLineRunner {

    @Autowired
    @Qualifier("loader")
    private Loader loader;

    @Override
    public void run(String... args) throws Exception {

        loader.loadTeams();
        loader.loadPlayers();
        loader.loadEvents();

    }
}
