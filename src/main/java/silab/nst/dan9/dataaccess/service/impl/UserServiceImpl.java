/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package silab.nst.dan9.dataaccess.service.impl;

import org.springframework.stereotype.Component;
import silab.nst.dan9.dataaccess.domain.User;
import silab.nst.dan9.dataaccess.repository.StatsRepository;
import silab.nst.dan9.dataaccess.repository.UserRepository;
import silab.nst.dan9.dataaccess.service.UserService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author laptop-02
 */


@Component
public class UserServiceImpl implements UserService{
    private static final String USERS_TABLE_NAME = "users";
    private final UserRepository userRepository;
    private final StatsRepository statsRepository;
    private Connection connection;

    public UserServiceImpl(UserRepository userRepository, StatsRepository statsRepository, Connection connection) {
        this.userRepository = userRepository;
        this.statsRepository = statsRepository;
        this.connection = connection;
    }
    
    @Override
    public User add(User user) {
        User insertedUser = null;

        try {
            connection.setAutoCommit(false);
            List<User> allUsers = userRepository.getAllUsers();
            insertedUser = userRepository.add(user);
            if (insertedUser.getId() != null) {
                int affectedRows = statsRepository.updateStats(USERS_TABLE_NAME, allUsers.size() + 1);
                if (affectedRows > 0) {
                    connection.commit();
                } else {
                    connection.rollback();
                }
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return insertedUser;
    }

    @Override
    public void delete(Long id) {
        try {
            connection.setAutoCommit(false);
            List<User> allUsers = userRepository.getAllUsers();
            userRepository.delete(id);
            int affectedRows = statsRepository.updateStats(USERS_TABLE_NAME, allUsers.size() - 1);
            if (affectedRows > 0) {
                connection.commit();
            } else {
                connection.rollback();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public User update(User user) {
        try {
            userRepository.update(user);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> allUsers = new ArrayList<>();
        try {
            allUsers = userRepository.getAllUsers();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return allUsers;
    }

}
