package ua.bolshak.model.dao.daoImpl;


import org.apache.log4j.Logger;
import ua.bolshak.model.MysqlConnectionPool;
import ua.bolshak.model.dao.idao.ShipIDao;
import ua.bolshak.model.dao.util.ColumnName;
import ua.bolshak.model.dao.util.SqlQuery;
import ua.bolshak.model.entity.*;
import ua.bolshak.model.service.BonusService;
import ua.bolshak.model.service.TicketTypeService;

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
    public List<Ship> findAllByTicketType(TicketType ticketType) {
        List<Ship> ships = new ArrayList<>();
        try (Connection connection = MysqlConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.FIND_ALL_SHIPS_BY_TICKET_TYPE)) {
            preparedStatement.setInt(1, ticketType.getId());
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
    public Ship findByUser(User user) {
        Ship ship = null;
        try (Connection connection = MysqlConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.FIND_SHIPS_BY_USER)) {
            preparedStatement.setInt(1, user.getId());
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
    public void add(Ship ship) {
        try (Connection connection = MysqlConnectionPool.getConnection();
             PreparedStatement psForAddShip = connection.prepareStatement(SqlQuery.ADD_SHIP)) {
            psForAddShip.setString(1, ship.getName());
            psForAddShip.setString(2, ship.getNumber());
            psForAddShip.setInt(3, ship.getNumberOfSeats());
            psForAddShip.setDouble(4, ship.getPricePerSeat());
            psForAddShip.setInt(5, ship.getType().getId());
            psForAddShip.executeUpdate();
            ship.setId(findByNumber(ship.getNumber()).getId());
            addBonuses(ship);
            addTicketTypes(ship);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void update(Ship ship) {
        try (Connection connection = MysqlConnectionPool.getConnection();
             PreparedStatement psForUpdateUser = connection.prepareStatement(SqlQuery.UPDATE_SHIP)) {
            psForUpdateUser.setString(1, ship.getName());
            psForUpdateUser.setString(2, ship.getNumber());
            psForUpdateUser.setInt(3, ship.getNumberOfSeats());
            psForUpdateUser.setDouble(4, ship.getPricePerSeat());
            psForUpdateUser.setInt(5, ship.getType().getId());
            psForUpdateUser.setInt(6, ship.getId());
            updateTicketTypes(ship);
            updateBonuses(ship);
            psForUpdateUser.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void updateTicketTypes(Ship ship) {
        try (Connection connection = MysqlConnectionPool.getConnection();
             PreparedStatement psFindAllIdTicketTypesByShip = connection.prepareStatement(SqlQuery.FIND_ALL_ID_TICKET_TYPES_BY_SHIP)){
            List<TicketType> ticketTypes = new ArrayList<>();
            psFindAllIdTicketTypesByShip.setInt(1, ship.getId());
            try (ResultSet resultSet = psFindAllIdTicketTypesByShip.executeQuery()) {
                while (resultSet.next()) {
                    ticketTypes.add(TicketTypeService.findById(resultSet.getInt(ColumnName.SHIPS_HAS_ID_TICKET_TYPE)));
                }
            }
            try (PreparedStatement psForDeleteShipsTicketType = connection.prepareStatement(SqlQuery.DELETE_TICKET_TYPES_BY_SHIP_IN_SHIP_HAS_TICKET_TYPE);
                 PreparedStatement psForAddShipsTicketType = connection.prepareStatement(SqlQuery.ADD_TICKET_TYPE_FOR_SHIP)) {
                for (TicketType ticketType : ship.getTicketTypes()) {
                    if (!ticketTypes.contains(ticketType)) {
                        psForAddShipsTicketType.setInt(1, ticketType.getId());
                        psForAddShipsTicketType.setInt(2, ship.getId());
                        psForAddShipsTicketType.addBatch();
                        ticketTypes.remove(ticketType);
                    } else {
                        ticketTypes.remove(ticketType);
                    }
                }
                psForAddShipsTicketType.executeBatch();
                if (!ticketTypes.isEmpty()) {
                    for (TicketType ticketType : ticketTypes) {
                        psForDeleteShipsTicketType.setInt(1, ship.getId());
                        psForDeleteShipsTicketType.setInt(2, ticketType.getId());
                        psForDeleteShipsTicketType.addBatch();
                    }
                    psForDeleteShipsTicketType.executeBatch();
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void updateBonuses(Ship ship) {
        try (Connection connection = MysqlConnectionPool.getConnection();
             PreparedStatement psFindAllIdBonusesByShip = connection.prepareStatement(SqlQuery.SELECT_ALL_ID_BONUSES_BY_SHIP)) {
            List<Bonus> bonuses = new ArrayList<>();
            psFindAllIdBonusesByShip.setInt(1, ship.getId());
            try (ResultSet resultSet = psFindAllIdBonusesByShip.executeQuery()) {
                while (resultSet.next()) {
                    bonuses.add(BonusService.findById(resultSet.getInt(ColumnName.SHIPS_BONUSE_BY_TICKET_TYPE)));
                }
            }
            try (PreparedStatement psForAddBonusForShip = connection.prepareStatement(SqlQuery.ADD_BONUS_FOR_SHIP);
                 PreparedStatement psForDeleteShipsBonusesByTicketType = connection.prepareStatement(SqlQuery.DELETE_SHIPS_BONUSES_BY_TICKET_TYPE);
                 PreparedStatement psForDeleteShipHasBonusByShipAndBonus = connection.prepareStatement(SqlQuery.DELETE_SHIPS_BONUSES_BY_TICKET_TYPE_AND_SHIP);
                 PreparedStatement psFindAllBonusesByTicketTypeAndShip = connection.prepareStatement(SqlQuery.FIND_ALL_BONUSES_BY_TICKET_TYPE_AND_SHIP);
                 PreparedStatement psFindTicketTypeByShipAndBonus = connection.prepareStatement(SqlQuery.FIND_ALL_TICKET_TYPE_BY_SHIP_AND_BONUS);
                 PreparedStatement psForUpdateShipsTicketType = connection.prepareStatement(SqlQuery.UPDATE_SHIP_HAS_TICKET_TYPE_AND_SHIP)) {
                for (Bonus bonus : ship.getBonuses()) {
                    if (!bonuses.contains(bonus)) {
                        psForAddBonusForShip.setInt(2, bonus.getId());
                        psForAddBonusForShip.setInt(1, ship.getId());
                        psForAddBonusForShip.addBatch();
                        bonuses.remove(bonus);
                    } else {
                        bonuses.remove(bonus);
                    }
                }
                psForAddBonusForShip.executeBatch();
                if (!bonuses.isEmpty()) {
                    for (Bonus bonus : bonuses) {
                        List<TicketType> ticketTypesByBonusAndShip = new ArrayList<>();
                        psFindTicketTypeByShipAndBonus.setInt(1, ship.getId());
                        psFindTicketTypeByShipAndBonus.setInt(2, bonus.getId());
                        try (ResultSet resultSet = psFindTicketTypeByShipAndBonus.executeQuery()) {
                            while (resultSet.next()) {
                                ticketTypesByBonusAndShip.add(TicketTypeService.findById(resultSet.getInt(ColumnName.SHIPS_HAS_ID_TICKET_TYPE)));
                            }
                        }
                        for (TicketType ticketType : ticketTypesByBonusAndShip) {
                            List<Bonus> bonusesByTicketType = new ArrayList<>();
                            psFindAllBonusesByTicketTypeAndShip.setInt(1, ship.getId());
                            psFindAllBonusesByTicketTypeAndShip.setInt(2, ticketType.getId());
                            try (ResultSet resultSet = psFindAllBonusesByTicketTypeAndShip.executeQuery()) {
                                while (resultSet.next()) {
                                    bonusesByTicketType.add(BonusService.findById(resultSet.getInt(ColumnName.SHIPS_BONUSE_BY_TICKET_TYPE)));
                                }
                            }
                            if (bonusesByTicketType.size() == 1){
                                psForUpdateShipsTicketType.setInt(1, ticketType.getId());
                                psForUpdateShipsTicketType.setInt(2, ship.getId());
                                psForUpdateShipsTicketType.executeUpdate();
                            } else {
                                psForDeleteShipsBonusesByTicketType.setInt(1, ship.getId());
                                psForDeleteShipsBonusesByTicketType.setInt(2, bonus.getId());
                                psForDeleteShipsBonusesByTicketType.addBatch();
                            }
                        }
                        psForDeleteShipHasBonusByShipAndBonus.setInt(1, ship.getId());
                        psForDeleteShipHasBonusByShipAndBonus.setInt(2, bonus.getId());
                        psForDeleteShipHasBonusByShipAndBonus.addBatch();
                    }
                    psForAddBonusForShip.executeBatch();
                    psForDeleteShipHasBonusByShipAndBonus.executeBatch();
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

//    private void updateTicketTypeAndBonus(Ship ship) {
//        try (Connection connection = MysqlConnectionPool.getConnection();
//             PreparedStatement psFindAllIdTicketTypesByShip = connection.prepareStatement(SqlQuery.FIND_ALL_ID_TICKET_TYPES_BY_SHIP);
//             PreparedStatement psFindAllIdBonusesByShip = connection.prepareStatement(SqlQuery.SELECT_ALL_ID_BONUSES_BY_SHIP)) {
//            List<TicketType> ticketTypes = new ArrayList<>();
//            List<Bonus> bonuses = new ArrayList<>();
//            psFindAllIdTicketTypesByShip.setInt(1, ship.getId());
//            psFindAllIdBonusesByShip.setInt(1, ship.getId());
//            try (ResultSet resultSet = psFindAllIdTicketTypesByShip.executeQuery()) {
//                while (resultSet.next()) {
//                    ticketTypes.add(TicketTypeService.findById(resultSet.getInt(ColumnName.SHIPS_HAS_ID_TICKET_TYPE)));
//                }
//            }
//            try (ResultSet resultSet = psFindAllIdBonusesByShip.executeQuery()) {
//                while (resultSet.next()) {
//                    bonuses.add(BonusService.findById(resultSet.getInt(ColumnName.SHIPS_BONUSE_BY_TICKET_TYPE)));
//                }
//            }
//            try (PreparedStatement psForDeleteShipsTicketType = connection.prepareStatement(SqlQuery.DELETE_TICKET_TYPES_BY_SHIP_IN_SHIP_HAS_TICKET_TYPE);
//                 PreparedStatement psForAddShipsTicketType = connection.prepareStatement(SqlQuery.ADD_TICKET_TYPE_FOR_SHIP)) {
//                for (TicketType ticketType : ship.getTicketTypes()) {
//                    if (!ticketTypes.contains(ticketType)) {
//                        psForAddShipsTicketType.setInt(1, ticketType.getId());
//                        psForAddShipsTicketType.setInt(2, ship.getId());
//                        psForAddShipsTicketType.addBatch();
//                        ticketTypes.remove(ticketType);
//                    } else {
//                        ticketTypes.remove(ticketType);
//                    }
//                }
//                psForAddShipsTicketType.executeBatch();
//                if (!ticketTypes.isEmpty()) {
//                    for (TicketType ticketType : ticketTypes) {
//                        psForDeleteShipsTicketType.setInt(1, ship.getId());
//                        psForDeleteShipsTicketType.setInt(2, ticketType.getId());
//                        psForDeleteShipsTicketType.addBatch();
//                    }
//                    psForDeleteShipsTicketType.executeBatch();
//                }
//            }
//            try (PreparedStatement psForAddBonusForShip = connection.prepareStatement(SqlQuery.ADD_BONUS_FOR_SHIP);
//                 PreparedStatement psForDeleteShipsBonusesByTicketType = connection.prepareStatement(SqlQuery.DELETE_SHIPS_BONUSES_BY_TICKET_TYPE);
//                 PreparedStatement psForDeleteShipHasBonusByShipAndBonus = connection.prepareStatement(SqlQuery.DELETE_SHIPS_BONUSES_BY_TICKET_TYPE_AND_SHIP);
//                 PreparedStatement psFindAllBonusesByTicketTypeAndShip = connection.prepareStatement(SqlQuery.FIND_ALL_BONUSES_BY_TICKET_TYPE_AND_SHIP);
//                 PreparedStatement psFindTicketTypeByShipAndBonus = connection.prepareStatement(SqlQuery.FIND_ALL_TICKET_TYPE_BY_SHIP_AND_BONUS);
//                 PreparedStatement psForUpdateShipsTicketType = connection.prepareStatement(SqlQuery.UPDATE_SHIP_HAS_TICKET_TYPE_AND_SHIP)) {
//                for (Bonus bonus : ship.getBonuses()) {
//                    if (!bonuses.contains(bonus)) {
//                        psForAddBonusForShip.setInt(1, ship.getId());
//                        psForAddBonusForShip.setInt(2, bonus.getId());
//                        psForAddBonusForShip.addBatch();
//                        bonuses.remove(bonus);
//                    } else {
//                        bonuses.remove(bonus);
//                    }
//                }
//                psForAddBonusForShip.executeBatch();
//                if (!bonuses.isEmpty()) {
//                    for (Bonus bonus : bonuses) {
//                        List<TicketType> ticketTypesByBonusAndShip = new ArrayList<>();
//                        psFindTicketTypeByShipAndBonus.setInt(1, ship.getId());
//                        psFindTicketTypeByShipAndBonus.setInt(2, bonus.getId());
//                        try (ResultSet resultSet = psFindTicketTypeByShipAndBonus.executeQuery()) {
//                            while (resultSet.next()) {
//                                ticketTypesByBonusAndShip.add(TicketTypeService.findById(resultSet.getInt(ColumnName.SHIPS_HAS_ID_TICKET_TYPE)));
//                            }
//                        }
//                        for (TicketType ticketType : ticketTypesByBonusAndShip) {
//                            List<Bonus> bonusesByTicketType = new ArrayList<>();
//                            psFindAllBonusesByTicketTypeAndShip.setInt(1, ship.getId());
//                            psFindAllBonusesByTicketTypeAndShip.setInt(2, ticketType.getId());
//                            try (ResultSet resultSet = psFindAllBonusesByTicketTypeAndShip.executeQuery()) {
//                                while (resultSet.next()) {
//                                    bonusesByTicketType.add(BonusService.findById(resultSet.getInt(ColumnName.SHIPS_BONUSE_BY_TICKET_TYPE)));
//                                }
//                            }
//                            if (bonusesByTicketType.size() == 1){
//                                psForUpdateShipsTicketType.setInt(1, ticketType.getId());
//                                psForUpdateShipsTicketType.setInt(2, ship.getId());
//                                psForUpdateShipsTicketType.executeUpdate();
//                            } else {
//                                psForDeleteShipsBonusesByTicketType.setInt(1, ship.getId());
//                                psForDeleteShipsBonusesByTicketType.setInt(2, bonus.getId());
//                                psForDeleteShipsBonusesByTicketType.addBatch();
//                            }
//                        }
//                        psForDeleteShipHasBonusByShipAndBonus.setInt(1, ship.getId());
//                        psForDeleteShipHasBonusByShipAndBonus.setInt(2, bonus.getId());
//                        psForDeleteShipHasBonusByShipAndBonus.addBatch();
//                    }
//                    psForAddBonusForShip.executeBatch();
//                    psForDeleteShipHasBonusByShipAndBonus.executeBatch();
//                }
//            }
//        } catch (SQLException e) {
//            LOGGER.error(e.getMessage());
//        }
//    }


    @Override
    public void delete(Ship ship) {
        try (Connection connection = MysqlConnectionPool.getConnection();
             PreparedStatement psForDeleteBonuses = connection.prepareStatement(SqlQuery.DELETE_ALL_SHIP_HAS_BONUSES);
             PreparedStatement psDeleteShip = connection.prepareStatement(SqlQuery.DELETE_SHIP);
             PreparedStatement psForDeleteTicketTypesHasBonuses = connection.prepareStatement(SqlQuery.DELETE_ALL_SHIP_HAS_TICKET_TYPES_HAS_BONUSES)) {
            if (!ship.getBonuses().isEmpty()) {
                psForDeleteBonuses.setInt(1, ship.getId());
                psForDeleteBonuses.executeUpdate();
            }
            if (!ship.getTicketTypes().isEmpty()) {
                psForDeleteTicketTypesHasBonuses.setInt(1, ship.getId());
                psForDeleteTicketTypesHasBonuses.executeUpdate();
            }

            psDeleteShip.setInt(1, ship.getId());
            psDeleteShip.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private static void addBonuses(Ship ship) {
        try (Connection connection = MysqlConnectionPool.getConnection();
             PreparedStatement psForAddBonuses = connection.prepareStatement(SqlQuery.ADD_BONUS_FOR_SHIP)) {
            for (Bonus bonus : ship.getBonuses()) {
                psForAddBonuses.setInt(1, ship.getId());
                psForAddBonuses.setInt(2, bonus.getId());
                psForAddBonuses.addBatch();
            }
            psForAddBonuses.executeBatch();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private static void addTicketTypes(Ship ship) {
        try (Connection connection = MysqlConnectionPool.getConnection();
             PreparedStatement psForAddTicketTypes = connection.prepareStatement(SqlQuery.ADD_TICKET_TYPE_FOR_SHIP)) {
            for (TicketType ticketType : ship.getTicketTypes()) {
                psForAddTicketTypes.setInt(1, ticketType.getId());
                psForAddTicketTypes.setInt(2, ship.getId());
                psForAddTicketTypes.addBatch();
            }
            psForAddTicketTypes.executeBatch();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
