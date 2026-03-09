package com.deloitte.dao;

import com.deloitte.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Insert new user
    public void save(User user) {
        String sql = "INSERT INTO users (email, password, role) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, user.getEmail(), user.getPassword(), user.getRole());
    }

    // Check if email exists
    public boolean isEmailExists(String email) {
        String sql = "SELECT COUNT(*) FROM users WHERE email=?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{email}, Integer.class);
        return count != null && count > 0;
    }

    // Validate user login
    public User validateUser(String email, String password, String role) {
        String sql = "SELECT * FROM users WHERE email=? AND password=? AND LOWER(role)=LOWER(?)";
        List<User> users = jdbcTemplate.query(sql, new Object[]{email, password, role}, new UserRowMapper());
        return users.isEmpty() ? null : users.get(0);
    }
    
    // Find user by email
    public User findByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email=?";
        List<User> users = jdbcTemplate.query(sql, new Object[]{email}, new UserRowMapper());
        return users.isEmpty() ? null : users.get(0);
    }
    
    // Find user by ID
    public User findById(Long userId) {
        String sql = "SELECT * FROM users WHERE user_id=?";
        List<User> users = jdbcTemplate.query(sql, new Object[]{userId}, new UserRowMapper());
        return users.isEmpty() ? null : users.get(0);
    }

    // RowMapper for User
    private static class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setUserId(rs.getLong("user_id"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setRole(rs.getString("role"));
            return user;
        }
    }
}