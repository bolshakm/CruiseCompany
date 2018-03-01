package ua.bolshak.model.dao.daoImpl;

import org.apache.log4j.Logger;
import ua.bolshak.model.MysqlConnectionPool;
import ua.bolshak.model.dao.idao.PortIDao;
import ua.bolshak.model.dao.util.ColumnName;
import ua.bolshak.model.dao.util.SqlQuery;
import ua.bolshak.model.entity.Cruise;
import ua.bolshak.model.entity.Excursion;
import ua.bolshak.model.entity.Port;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PortDao implements PortIDao {
    private static PortDao instance;
    private static Logger LOGGER = Logger.getLogger(PortDao.class);

    private PortDao(){}

    public synchronized static PortDao getInstance() {
        if (instance == null){
            instance = new PortDao();
        }
        return instance;
    }

    private Port initialization(ResultSet resultSet) throws SQLException {
        Port port = new Port();
        port.setId(resultSet.getInt(ColumnName.ID_PORT));
        port.setName(resultSet.getString(ColumnName.PORT_NAME));
        port.setCity(resultSet.getString(ColumnName.PORT_CITY));
        port.setCountry(resultSet.getString(ColumnName.PORT_COUNTRY));
        return port;
    }

    @Override
    public List<Port> findAll() {
        List<Port> ports = new ArrayList<>();
        try (Connection connection = MysqlConnectionPool.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SqlQuery.FIND_ALL_PORTS)){
            while (resultSet.next()){
                ports.add(initialization(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return ports;
    }

    @Override
    public List<Port> findAllByCruise(Cruise cruise) {
        List<Port> ports = new ArrayList<>();
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.FIND_ALL_PORTS_BY_CRUISE)){
            preparedStatement.setInt(1, cruise.getId());
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    ports.add(initialization(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return ports;
    }

    @Override
    public Port findById(int id) {
        Port port = null;
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.FIND_PORT_BY_ID)){
            preparedStatement.setInt(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    port = initialization(resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return port;
    }

    @Override
    public Port findByExcursion(Excursion excursion) {
        Port port = null;
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.FIND_PORT_BY_EXCURSION)){
            preparedStatement.setInt(1, excursion.getId());
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    port = initialization(resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return port;
    }

    @Override
    public void add(Port port) {
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.ADD_PORT)){
            preparedStatement.setString(1, port.getName());
            preparedStatement.setString(2, port.getCity());
            preparedStatement.setString(3, port.getCountry());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void update(Port port) {
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.UPDATE_PORT)){
            preparedStatement.setString(1, port.getName());
            preparedStatement.setString(2, port.getCity());
            preparedStatement.setString(3, port.getCountry());
            preparedStatement.setInt(4, port.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void delete(Port port) {
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.DELETE_PORT)){
            preparedStatement.setInt(1, port.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
