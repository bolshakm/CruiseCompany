package ua.bolshak.model.dao.daoImpl;

import org.apache.log4j.Logger;
import ua.bolshak.model.MysqlConnectionPool;
import ua.bolshak.model.dao.idao.ShipTypeIDao;
import ua.bolshak.model.dao.util.ColumnName;
import ua.bolshak.model.dao.util.SqlQuery;
import ua.bolshak.model.entity.Ship;
import ua.bolshak.model.entity.ShipType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShipTypeDao implements ShipTypeIDao{
    private static ShipTypeDao instance;
    private static Logger LOGGER = Logger.getLogger(ShipTypeDao.class);

    private ShipTypeDao(){}

    public synchronized static ShipTypeDao getInstance() {
        if (instance == null){
            instance = new ShipTypeDao();
        }
        return instance;
    }

    private ShipType initialization(ResultSet resultSet) throws SQLException {
        ShipType shipType = new ShipType();
        shipType.setId(resultSet.getInt(ColumnName.ID_SHIP_TYPE));
        shipType.setName(resultSet.getString(ColumnName.SHIP_TYPE_NAME));
        return shipType;
    }

    @Override
    public List<ShipType> findAll() {
        List<ShipType> shipTypes = new ArrayList<>();
        try (Connection connection = MysqlConnectionPool.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SqlQuery.FIND_ALL_SHIP_TYPES)){
            while (resultSet.next()){
                shipTypes.add(initialization(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return shipTypes;
    }

    @Override
    public ShipType findById(int id) {
        ShipType shipType = null;
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.FIND_SHIP_TYPE_BY_ID)){
            preparedStatement.setInt(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    shipType = initialization(resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return shipType;
    }

    @Override
    public ShipType findByShip(Ship ship) {
        ShipType shipType = null;
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.FIND_SHIP_TYPE_BY_SHIP)){
            preparedStatement.setInt(1, ship.getId());
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    shipType = initialization(resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return shipType;
    }

    @Override
    public void add(ShipType shipType) {
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.ADD_SHIP_TYPE)){
            preparedStatement.setString(1, shipType.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void update(ShipType shipType) {
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.UPDATE_SHIP_TYPE)){
            preparedStatement.setString(1, shipType.getName());
            preparedStatement.setInt(2, shipType.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void delete(ShipType shipType) {
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.DELETE_SHIP_TYPE)){
            preparedStatement.setInt(1, shipType.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
