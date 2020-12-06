/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package silab.nst.dan9.dataaccess.repository;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author laptop-02
 */

@Component
public class StatsRepository {
    private Connection connection;

    public StatsRepository(Connection connection) {
        this.connection = connection;
    }

    public int updateStats(String tableName, int numberOfRows) throws SQLException {

        String sql = "INSERT INTO stats (table_name, number_of_rows) VALUES(?, ?) ON DUPLICATE KEY UPDATE    \n" +
                "number_of_rows=?";
        connection.setAutoCommit(false);
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, tableName);
        statement.setInt(2, numberOfRows);
        statement.setInt(3, numberOfRows);
        return statement.executeUpdate();
    }
}
