package com.idk.movierecommendation.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public UserModel getUserByName(String username) {
        String sql = "SELECT * FROM users WHERE username=?";
        UserModel user = jdbcTemplate.queryForObject(sql, new UserRowMapper(), username);
        return user;
    }

    public UserModel postUser(UserModel userModel){
        String sql = "INSERT INTO users VALUES(?,?,?,?)";
        int update = jdbcTemplate.update(sql, null, userModel.getUsername(), userModel.getPassword(), userModel.getEmail());
        return userModel;
    }

    public UserModel updateUser(UserModel userModel){
        String sql = "UPDATE INTO users SET username=?, password=?, email=? WHERE id=?";
        int status = jdbcTemplate.update(sql, userModel.getUsername(), userModel.getPassword(), userModel.getEmail(), userModel.getId());
        return userModel;
    }
}

