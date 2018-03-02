package ru.dz.restapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PeriodStatistic {
    private int all_visits;
    private int unique_visits;
    private int permanent_visitors;
}
