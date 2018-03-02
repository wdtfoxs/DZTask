package ru.dz.restapi.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class TodayStatistic {
    private int all_visits;
    private int unique_visits;
}
