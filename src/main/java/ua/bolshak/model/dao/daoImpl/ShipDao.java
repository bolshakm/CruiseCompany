package ua.bolshak.model.dao.daoImpl;


import org.apache.log4j.Logger;
import ua.bolshak.model.MysqlConnectionPool;
import ua.bolshak.model.dao.idao.ShipIDao;
import ua.bolshak.model.dao.util.ColumnName;
import ua.bolshak.model.dao.util.SqlQuery;
import ua.bolshak.model.entity.Bonus;
import ua.bolshak.model.entity.Cruise;
import ua.bolshak.model.entity.Ship;
import ua.bolshak.model.entity.ShipType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShipDao implements ShipIDao {
    private static ShipDao instance;
    private static Logger LOGGER = Logger.getLogger(ShipDao.class);

    private ShipDao() {
    }

    public synchronized static ShipDao getInstance() {
        if (instance == null) {
            instance = new ShipDao();
        }
        return instance;
    }

    private Ship initialization(ResultSet resultSet) throws SQLException {
        Ship ship = new Ship();
        ship.setId(resultSet.getInt(ColumnName.ID_SHIP));
        ship.setName(resultSet.getString(ColumnName.SHIP_NAME));
        ship.setNumber(resultSet.getString(ColumnName.SHIP_NUMBER));
        ship.setNumberOfSeats(resultSet.getInt(ColumnName.NUMBER_OF_SEATS));
        ship.setPricePerSeat(resultSet.getDouble(ColumnName.PRICE_PER_SEAT));
        return ship;
    }

    @Override
    public List<Ship> findAll() {
        List<Ship> ships = new ArrayList<>();
        try (Connection connection = MysqlConnectionPool.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SqlQuery.FIND_ALL_SHIPS)) {
            while (resultSet.next()) {
                ships.add(initialization(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return ships;
    }

    @Override
    public List<Ship> findAllByShipType(ShipType shipType) {
        List<Ship> ships = new ArrayList<>();
        try (Connection connection = MysqlConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.FIND_ALL_SHIPS_BY_TYPE)) {
            preparedStatement.setInt(1, shipType.getId());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    ships.add(initialization(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return ships;
    }

    @Override
    public List<Ship> findAllByBonus(Bonus bonus) {
        List<Ship> ships = new ArrayList<>();
        try (Connection connection = MysqlConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.FIND_ALL_SHIPS_BY_BONUS)) {
            preparedStatement.setInt(1, bonus.getId());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    ships.add(initialization(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return ships;
    }

    @Override
    public Ship findById(int id) {
        Ship ship = null;
        try (Connection connection = MysqlConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.FIND_SHIP_BY_ID)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    ship = initialization(resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return ship;
    }

    @Override
    public Ship findByNumber(String number) {
        Ship ship = null;
        try (Connection connection = MysqlConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.FIND_SHIP_BY_NUMBER)) {
            preparedStatement.setString(1, number);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    ship = initialization(resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return ship;
    }

    @Override
    public Ship findByCruise(Cruise cruise) {
        Ship ship = null;
        try (Connection connection = MysqlConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.FIND_SHIP_BY_CRUISE)) {
            preparedStatement.setInt(1, cruise.getId());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    ship = initialization(resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return ship;
    }

    @Override
    public void add(Ship ship, ShipType shipType) {
        try (Connection connection = MysqlConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.ADD_SHIP)) {
            preparedStatement.setString(1, ship.getName());
            preparedStatement.setString(2, ship.getNumber());
            preparedStatement.setInt(3, ship.getNumberOfSeats());
            preparedStatement.setDouble(4, ship.getPricePerSeat());
            preparedStatement.setInt(5, shipType.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void addBonusesForShip(Ship ship, List<Bonus> bonuses) {
        try (Connection connection = MysqlConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.ADD_BONUS_FOR_SHIP)) {
            for (Bonus bonus : bonuses) {
                preparedStatement.setInt(1, ship.getId());
                preparedStatement.setInt(2, bonus.getId());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void update(Ship ship, ShipType shipType) {
        try (Connection connection = MysqlConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.UPDATE_SHIP)) {
            preparedStatement.setString(1, ship.getName());
            preparedStatement.setString(2, ship.getNumber());
            preparedStatement.setInt(3, ship.getNumberOfSeats());
            preparedStatement.setDouble(4, ship.getPricePerSeat());
            preparedStatement.setInt(5, shipType.getId());
            preparedStatement.setInt(6, ship.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void updateBonuses(Ship ship, List<Bonus> bonuses) {
        delete(ship);
        addBonusesForShip(ship, bonuses);
    }

    @Override
    public void delete(Ship ship) {
        try (Connection connection = MysqlConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.DELETE_ALL_SHIP_HAS_BONUSES)) {
            preparedStatement.setInt(1, ship.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void deleteBonuses(Ship ship) {
        try (Connection connection = MysqlConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.DELETE_SHIP)) {
            preparedStatement.setInt(1, ship.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
