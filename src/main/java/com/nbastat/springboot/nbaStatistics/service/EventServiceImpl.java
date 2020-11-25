package com.nbastat.springboot.nbaStatistics.service;

import com.nbastat.springboot.nbaStatistics.entity.Event;
import com.nbastat.springboot.nbaStatistics.repositories.EventRepositroy;
import com.nbastat.springboot.nbaStatistics.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EventServiceImpl implements EventService {

    private EventRepositroy eventRepositroy;

    @Autowired
    EventServiceImpl(EventRepositroy eventRepositroy) {
        this.eventRepositroy = eventRepositroy;
    }


    @Override
    public void save(Event event) {
        eventRepositroy.save(event);
    }
}
