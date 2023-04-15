package com.eazybyte.springschoolproject.repository;

import com.eazybyte.springschoolproject.model.Holiday;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HolidayRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Holiday> findAllHolidays(){
        String sql = " select * from holidays";
        var rowMapper = BeanPropertyRowMapper.newInstance(Holiday.class);
        return jdbcTemplate.query(sql,rowMapper);
    }
}
