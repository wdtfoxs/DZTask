package ru.dz.restapi.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.dz.restapi.dto.PeriodStatistic;
import ru.dz.restapi.dto.TodayStatistic;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.HashMap;

@Repository
public class StatisticDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void record(Long user, String path) {
        String RECORD =
                "INSERT INTO user_statistic (user_id, path, date) VALUES (?, ?, ?) " +
                        "ON CONFLICT (user_id, path, date) DO UPDATE SET count_visit = user_statistic.count_visit + 1";
        jdbcTemplate.update(RECORD, user, path, LocalDate.now());
    }

    public TodayStatistic todayStatistic() {
        String TODAY_STATISTIC =
                "SELECT sum(count_visit) AS all_visits, count(DISTINCT user_id) AS unique_visits FROM user_statistic WHERE date = now()::DATE GROUP BY date";
        return jdbcTemplate.query(TODAY_STATISTIC, (ResultSet rs) -> {
            rs.next();
            return new TodayStatistic(rs.getInt("all_visits"), rs.getInt("unique_visits"));
        });
    }


    public PeriodStatistic periodStatistic(LocalDate from, LocalDate to) {
        String PERIOD_STATISTIC =
                "WITH result AS (SELECT sum(count_visit) AS cnt, user_id AS users, count(DISTINCT user_id) AS pages FROM user_statistic " +
                        "WHERE (:from::DATE IS NULL  OR date >= :from) AND (:to::DATE IS NULL OR date <= :to) GROUP BY user_id) " +
                        "SELECT coalesce(sum(cnt), 0)  AS all_visits, count(*) AS unique_visits, " +
                        "   (SELECT count(*) FROM result WHERE pages >=10) AS permanent_visitors FROM result;";
        HashMap<String, Object> map = new HashMap<>();
        map.put("from", from);
        map.put("to", to);
        return namedParameterJdbcTemplate.query(PERIOD_STATISTIC, map, (ResultSet rs) -> {
            rs.next();
            return new PeriodStatistic(rs.getInt("all_visits"), rs.getInt("unique_visits"), rs.getInt("permanent_visitors"));
        });
    }
}
