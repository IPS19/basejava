package com.urise.webapp.util;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.sql.ConnectionFactory;

import java.sql.*;

public class SqlHelper {
    private final ConnectionFactory connectionFactory;


    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;

    }

    public <T> T connectAndDo(String sqlQuery, SqlExecutor<T> sqlExecutor) {

        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlQuery)) {

            return sqlExecutor.execute(ps);

        } catch (SQLException e) {
            if (e.getSQLState().equals("23505")) {
                throw new ExistStorageException("");
            }
            throw new StorageException(e);
        }
    }
}

