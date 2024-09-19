package com.example.ecthone.DAO;

import com.example.ecthone.Model.Person;
import com.example.ecthone.Model.Role;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // RowMapper для преобразования результата запроса в объект Person
    private RowMapper<Person> personRowMapper = new RowMapper<>() {
        @Override
        public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
            Person person = new Person();
            person.setId(rs.getInt("id"));
            person.setFio(rs.getString("fio"));
            person.setRole(Role.getRole(rs.getString("role")));
            person.setEmail(rs.getString("email"));
            person.setPhone(rs.getString("phone"));
            person.setVerification(rs.getBoolean("verified"));
            person.setPassword(rs.getString("password"));
            return person;
        }
    };

    public Person findById(int id) {
        String sql = "SELECT * FROM person WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, personRowMapper, id);
    }
    public Person findByName(String fio) {
        String sql = "SELECT * FROM person WHERE fio = ?";
        return jdbcTemplate.queryForObject(sql, personRowMapper, fio);
    }
    public Person findByPhone(String phone) {
        String sql = "SELECT * FROM person WHERE phone = ?";
        return jdbcTemplate.queryForObject(sql, personRowMapper, phone);
    }
    public Person findByEmail(String email) {
        String sql = "SELECT * FROM person WHERE email = ?";
        return jdbcTemplate.queryForObject(sql, personRowMapper, email);
    }

    public List<Person> findAll() {
        String sql = "SELECT * FROM person";
        return jdbcTemplate.query(sql, personRowMapper);
    }

    public void save(Person person) {
        String sql = "INSERT INTO person (fio, role, email, phone, verified,password) VALUES (?, ?, ?, ?, ?, ?)";
        String fio = person.getFio();
        String role = person.getRole().toString();
        String email = person.getEmail();
        String phone = person.getPhone();
        boolean verified = person.getVerification();
        String password = person.getPassword();
        jdbcTemplate.update(sql, fio, role, email, phone, verified,password);
    }

    public void update(Person person) {
        String sql = "UPDATE person SET fio = ?, role = ?, email = ?, phone = ?, verified = ?, password = ? WHERE id = ?";
        jdbcTemplate.update(sql, person.getFio(), person.getRole().toString(), person.getEmail(), person.getPhone(), person.getVerification(), person.getPassword(),person.getId());
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM person WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
