package ru.dz.restapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.dz.restapi.dao.StatisticDao;
import ru.dz.restapi.dto.PeriodStatistic;
import ru.dz.restapi.dto.TodayStatistic;

import java.time.LocalDate;

@Service
public class StatisticService {
    @Autowired
    private StatisticDao dao;

    @Async
    @Transactional
    public void record(Long user, String path) {
        dao.record(user, path);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public TodayStatistic todayStatistic() {
        return dao.todayStatistic();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public PeriodStatistic periodStatistic(LocalDate from, LocalDate to) {
        return dao.periodStatistic(from, to);
    }
}
