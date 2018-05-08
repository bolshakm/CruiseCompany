package ua.bolshak.model.dao.daoImpl;

import org.apache.log4j.Logger;
import ua.bolshak.model.MysqlConnectionPool;
import ua.bolshak.model.dao.idao.ExcursionIDao;
import ua.bolshak.model.dao.util.ColumnName;
import ua.bolshak.model.dao.util.SqlQuery;
import ua.bolshak.model.entity.Excursion;
import ua.bolshak.model.entity.Port;
import ua.bolshak.model.entity.Ticket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExcursionDao implements ExcursionIDao{
    private static ExcursionDao instance;
    private static Logger LOGGER = Logger.getLogger(ExcursionDao.class);

    private ExcursionDao(){}

    public synchronized static ExcursionDao getInstance() {
        if (instance == null){
            instance = new ExcursionDao();
        }
        return instance;
    }

    private Excursion initialization(ResultSet resultSet) throws SQLException {
       Excursion excursion = new Excursion();
       excursion.setId(resultSet.getInt(ColumnName.ID_EXCURSION));
       excursion.setName(resultSet.getString(ColumnName.EXCURSION_NAME));
       excursion.setPrice(resultSet.getDouble(ColumnName.EXCURSION_PRICE));
       return excursion;
    }

    @Override
    public List<Excursion> findAll() {
        List<Excursion> excursions = new ArrayList<>();
        try (Connection connection = MysqlConnectionPool.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SqlQuery.FIND_ALL_EXCURSIONS)){
            while (resultSet.next()){
                excursions.add(initialization(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return excursions;
    }

    @Override
    public List<Excursion> findAllByPort(Port port) {
        List<Excursion> excursions = new ArrayList<>();
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.FIND_ALL_EXCURSIONS_BY_PORT)){
            preparedStatement.setInt(1, port.getId());
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    excursions.add(initialization(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return excursions;
    }

    @Override
    public List<Excursion> findAllByTicket(Ticket ticket) {
        List<Excursion> excursions = new ArrayList<>();
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.FIND_ALL_EXCURSIONS_BY_TICKET)){
            preparedStatement.setInt(1, ticket.getId());
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    excursions.add(initialization(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return excursions;
    }

    @Override
    public Excursion findById(int id) {
        Excursion excursion = null;
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.FIND_EXCURSION_BY_ID)){
            preparedStatement.setInt(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    excursion = initialization(resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return excursion;
    }

    @Override
    public void add(Excursion excursion) {
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.ADD_EXCURSION)){
            preparedStatement.setString(1, excursion.getName());
            preparedStatement.setDouble(2, excursion.getPrice());
            preparedStatement.setInt(3, excursion.getPort().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void update(Excursion excursion) {
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.UPDATE_EXCURSION)){
            preparedStatement.setString(1, excursion.getName());
            preparedStatement.setDouble(2, excursion.getPrice());
            preparedStatement.setInt(3, excursion.getPort().getId());
            preparedStatement.setInt(4, excursion.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void delete(Excursion excursion) {
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.DELETE_EXCURSION);
            PreparedStatement psForDeleteTicketsHasExcursion = connection.prepareStatement(SqlQuery.DELETE_EXCURSIONS_TICKETS)){
            if (!excursion.getTickets().isEmpty()) {
                psForDeleteTicketsHasExcursion.setInt(1, excursion.getId());
                psForDeleteTicketsHasExcursion.executeUpdate();
            }
            preparedStatement.setInt(1, excursion.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
