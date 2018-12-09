package ua.bolshak.model;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

public class MysqlConnectionPool {
    private static MysqlConnectionPool instance;
    private static final Logger LOGGER = LogManager.getLogger(MysqlConnectionPool.class);


    private MysqlConnectionPool() {
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = null;


        try {
            Context context = (Context) new InitialContext().lookup("java:comp/env");
            DataSource dataSource = (DataSource) context.lookup("jdbc/cruise_company");
            connection = dataSource.getConnection();
        } catch (NamingException e) {
            LOGGER.error(e.getMessage());
        }
        return connection;
    }

    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
        close(connection, statement);
        try {
            if (resultSet != null) {resultSet.close();}
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public static void close(Connection connection, Statement statement) {
        try {
            if (statement != null) {statement.close();}
            if (connection != null) {connection.close();}
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public static MysqlConnectionPool getInstance() {
        if (instance == null){
            instance = new MysqlConnectionPool();
        }
        return instance;
    }
}
