package com.nbastat.springboot.nbaStatistics.repositories;

import com.nbastat.springboot.nbaStatistics.entity.Event;
import com.nbastat.springboot.nbaStatistics.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepositroy extends JpaRepository<Event, Long> {


}
