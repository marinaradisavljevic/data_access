/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package silab.nst.dan9.dataaccess.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author laptop-02
 */

@ComponentScan(basePackages = {
        "silab.nst.dan9.dataaccess.repository",
        "silab.nst.dan9.dataaccess.main",
        "silab.nst.dan9.dataaccess.service.impl",
        "silab.nst.dan9.dataaccess.repository"
})


public class Config {
    @Bean(name = "connection")
    public Connection jdbcConnection() throws SQLException {
        String connectionUrl = "jdbc:mysql://localhost:3306/users";
        Connection connection = DriverManager.getConnection(connectionUrl, "******", "*******");
        return connection;
    }
    
}
