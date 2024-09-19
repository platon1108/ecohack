package com.example.ecthone.DAO;


import com.example.ecthone.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EventDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void EventDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Event> personRowMapper = new RowMapper<>() {
        @Override
        public Event mapRow(ResultSet rs, int rowNum) throws SQLException {

            String name = rs.getString("name");
            String description = rs.getString("description");
            Format format =  Format.getFormat(rs.getString("format"));
            Duration duration = Duration.getDuration(rs.getString("duration"));
            String address = rs.getString("address");
            Integer personid = rs.getInt("personid");
            String date = rs.getString("eventdate");
            String type = rs.getString("eventicom");
            Event event = new Event(name,description,format,duration,address,personid,date,type);
            event.setId(rs.getInt("id"));
            return event;
        }
    };

    public Event findById(int id) {
        String sql = "SELECT * FROM event WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, personRowMapper, id);
    }
    public Event findByName(String name) {
        String sql = "SELECT * FROM event WHERE name = ?";
        return jdbcTemplate.queryForObject(sql, personRowMapper, name);
    }

    public List<Event> findAll() {
        String sql = "SELECT * FROM event";
        return jdbcTemplate.query(sql, personRowMapper);
    }
    public List<Event> findPersonId(Integer personid) {
        String sql = "SELECT * FROM event WHERE personid = ?";
        return jdbcTemplate.query(sql, personRowMapper,personid);
    }

    public void save(Event event) {
        String sql = "INSERT INTO event (name, description, format, duration, address,personid,eventdate,eventicom) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        String name = event.getName();
        String description = event.getDescription();
        String format = event.getFormat().toString();
        String duration = event.getDuration().toString();
        String address = event.getAddress();
        Integer personid = event.getPersonid();
        String date =  event.getEventDate();
        String type = event.getEventIcon();
        jdbcTemplate.update(sql,name,description,format,duration,address,personid,date,type);
    }

    public void update(Event event) {
        String sql = "UPDATE event SET name = ?, description = ?, format = ?, duration = ?, address = ?,personid = ?, eventdate  = ?,eventicom = ?  WHERE id = ?";
        String name = event.getName();
        String description = event.getDescription();
        String format = event.getFormat().toString();
        String duration = event.getDuration().toString();
        String address = event.getAddress();
        Integer personid = event.getPersonid();
        String date =  event.getEventDate();
        String type = event.getEventIcon();
        jdbcTemplate.update(sql,name,description,format,duration,address,personid,date,type,event.getId());
    }

    public void deleteById(Integer id) {
        String sql = "DELETE FROM event WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }


}
