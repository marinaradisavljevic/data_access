/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package silab.nst.dan9.dataaccess.repository;

import org.springframework.stereotype.Component;
import silab.nst.dan9.dataaccess.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author laptop-02
 */

@Component
public class UserRepository {

    private Connection connection;

    public UserRepository (Connection connection) {
        this.connection = connection;
    }

    public User add(User user) throws SQLException {
        String insertUserSql = "INSERT INTO users (first_name, last_name, username, password) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(insertUserSql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, user.getFirstname());
        statement.setString(2, user.getLastname());
        statement.setString(3, user.getUsername());
        statement.setString(4, user.getPassword());

        int affectedRows = statement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Failed to insert new user.");
        }
        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            user.setId(generatedKeys.getLong(1));
        } else {
            throw new SQLException("Error while inserting new user, no ID returned.");
        }

        return user;
    }

    
    public void delete(Long userId) throws SQLException{
        String sql = "DELETE FROM Users WHERE user_id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, userId);
        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("A user was deleted successfully!");
        } else {
            throw new SQLException("User deletion failed, 0 rows affected.");
        }
    }

    public User update(User user)throws SQLException{

        String sql = "UPDATE Users SET first_name=?, last_name=?, username=?, password=? WHERE user_id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, user.getFirstname());
        statement.setString(2, user.getLastname());
        statement.setString(3, user.getUsername());
        statement.setString(4, user.getPassword());
        statement.setLong(5, user.getId());

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated == 0) {
            throw new SQLException("User update failed, 0 rows affected.");
        }

        return user;
    }

    public List<User> getAllUsers() throws SQLException {
        String sql = "SELECT * FROM users";
        List<User> users = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            User user = new User();
            user.setId(rs.getLong("user_id"));
            user.setFirstname(rs.getString("first_name"));
            user.setLastname(rs.getString("last_name"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            users.add(user);
        }
        return users;
    }

}
