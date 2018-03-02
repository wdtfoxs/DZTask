package ru.dz.restapi.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dz.restapi.dao.StatisticDao;
import ru.dz.restapi.service.StatisticService;
import ru.dz.restapi.web.dto.PeriodStatistic;
import ru.dz.restapi.web.dto.TodayStatistic;

import java.time.LocalDate;

@RestController
@RequestMapping("/rest")
public class StatisticController {

    @Autowired
    private StatisticService statisticService;

    @PostMapping(value = "/record", params = {"user", "path"})
    public ResponseEntity<TodayStatistic> record(@RequestParam Long user,
                                                 @RequestParam String path){

        statisticService.record(user, path);
        return new ResponseEntity<>(statisticService.todayStatistic(), HttpStatus.OK);
    }

    @GetMapping("/statistic")
    public ResponseEntity<PeriodStatistic> getStatistic(@RequestParam(required = false) @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate from,
                                                        @RequestParam(required = false) @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate to){

        return new ResponseEntity<>(statisticService.periodStatistic(from, to), HttpStatus.OK);
    }
}