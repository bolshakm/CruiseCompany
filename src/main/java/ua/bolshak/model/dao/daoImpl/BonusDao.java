package ua.bolshak.model.dao.daoImpl;

import org.apache.log4j.Logger;
import ua.bolshak.model.MysqlConnectionPool;
import ua.bolshak.model.dao.idao.BonusIDao;
import ua.bolshak.model.dao.util.ColumnName;
import ua.bolshak.model.dao.util.SqlQuery;
import ua.bolshak.model.entity.Bonus;
import ua.bolshak.model.entity.Ship;
import ua.bolshak.model.entity.Ticket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BonusDao  implements BonusIDao {
    private static BonusDao instance = null;
    private static Logger LOGGER = Logger.getLogger(BonusDao.class);

    private BonusDao() {
    }

    private Bonus initialization(ResultSet resultSet) throws SQLException {
        Bonus bonus = new Bonus();
        bonus.setId(resultSet.getInt(ColumnName.ID_BONUS));
        bonus.setName(resultSet.getString(ColumnName.BONUS_NAME));
        return bonus;
    }

    public synchronized static BonusDao getInstance() {
        if (instance == null){
            return new BonusDao();
        }
        return instance;
    }

    @Override
    public List<Bonus> findAll() {
        List<Bonus> bonuses = new ArrayList<>();
        try (Connection connection = MysqlConnectionPool.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SqlQuery.SELECT_ALL_BONUSES)){
            while (resultSet.next()){
                bonuses.add(initialization(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return bonuses;
    }

    @Override
    public List<Bonus> findAllByTicket(Ticket ticket) {
        List<Bonus> bonuses = new ArrayList<>();
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.SELECT_ALL_BONUSES_BY_TICKET)){
            preparedStatement.setInt(1, ticket.getId());
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    bonuses.add(initialization(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return bonuses;
    }

    @Override
    public List<Bonus> findAllShip(Ship ship) {
        List<Bonus> bonuses = new ArrayList<>();
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.SELECT_ALL_BONUSES_BY_SHIP)){
            preparedStatement.setInt(1, ship.getId());
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    bonuses.add(initialization(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return bonuses;
    }

    @Override
    public Bonus findById(int id) {
        Bonus bonus = null;
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.FIND_BONUS_BY_ID)){
            preparedStatement.setInt(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    bonus = initialization(resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return bonus;
    }

    @Override
    public void add(Bonus bonus) {
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.ADD_BONUS)){
            preparedStatement.setString(1, bonus.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void update(Bonus bonus) {
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.UPDATE_BONUS)){
            preparedStatement.setString(1, bonus.getName());
            preparedStatement.setInt(2, bonus.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void delete(Bonus bonus) {
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.DELETE_BONUS)){
            preparedStatement.setInt(1, bonus.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
