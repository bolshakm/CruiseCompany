package ua.bolshak.model.dao.daoImpl;


import org.apache.log4j.Logger;
import ua.bolshak.model.MysqlConnectionPool;
import ua.bolshak.model.dao.idao.TicketIDao;
import ua.bolshak.model.dao.util.ColumnName;
import ua.bolshak.model.dao.util.SqlQuery;
import ua.bolshak.model.entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDao implements TicketIDao{
    private static TicketDao instance;
    private static Logger LOGGER = Logger.getLogger(TicketDao.class);

    private TicketDao(){}

    private Ticket initialization(ResultSet resultSet) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setId(resultSet.getInt(ColumnName.ID_TICKET));
        ticket.setPrice(resultSet.getDouble(ColumnName.TICKET_PRICE));
        return ticket;
    }

    public synchronized static TicketDao getInstance() {
        if (instance == null){
            instance = new TicketDao();
        }
        return instance;
    }

    @Override
    public List<Ticket> findAll() {
        List<Ticket> tickets = new ArrayList<>();
        try (Connection connection = MysqlConnectionPool.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SqlQuery.FIND_ALL_TICKETS)){
            while (resultSet.next()){
                tickets.add(initialization(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return tickets;
    }

    @Override
    public List<Ticket> findAllByUser(User user) {
        List<Ticket> tickets = new ArrayList<>();
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.FIND_ALL_TICKETS_BY_USER)){
            preparedStatement.setInt(1, user.getId());
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    tickets.add(initialization(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return tickets;
    }

    @Override
    public List<Ticket> findAllByCruise(Cruise cruise) {
        List<Ticket> tickets = new ArrayList<>();
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.FIND_ALL_TICKETS_BY_CRUISE)){
            preparedStatement.setInt(1, cruise.getId());
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    tickets.add(initialization(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return tickets;
    }

    @Override
    public List<Ticket> findAllByTicketType(TicketType ticketType) {
        List<Ticket> tickets = new ArrayList<>();
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.FIND_ALL_TICKETS_BY_TICKET_TYPES)){
            preparedStatement.setInt(1, ticketType.getId());
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    tickets.add(initialization(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return tickets;
    }

    @Override
    public List<Ticket> findAllByBonus(Bonus bonus) {
        List<Ticket> tickets = new ArrayList<>();
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.FIND_ALL_TICKETS_BY_BONUS)){
            preparedStatement.setInt(1, bonus.getId());
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    tickets.add(initialization(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return tickets;
    }

    @Override
    public List<Ticket> findAllByExcursion(Excursion excursion) {
        List<Ticket> tickets = new ArrayList<>();
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.FIND_ALL_TICKETS_BY_EXCURSION)){
            preparedStatement.setInt(1, excursion.getId());
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    tickets.add(initialization(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return tickets;
    }

    @Override
    public Ticket findById(int id) {
        Ticket ticket = null;
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.FIND_TICKET_BY_ID)){
            preparedStatement.setInt(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    ticket = initialization(resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return ticket;
    }

    @Override
    public void add(Ticket ticket) {
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.ADD_TICKET)){
            preparedStatement.setInt(1, ticket.getUser().getId());
            preparedStatement.setInt(2, ticket.getTicketType().getId());
            preparedStatement.setInt(3, ticket.getCruise().getId());
            preparedStatement.setDouble(4, ticket.getPrice());
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void update(Ticket ticket) {
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.UPDATE_TICKET)){
            preparedStatement.setInt(1, ticket.getUser().getId());
            preparedStatement.setInt(2, ticket.getTicketType().getId());
            preparedStatement.setInt(3, ticket.getCruise().getId());
            preparedStatement.setDouble(4, ticket.getPrice());
            preparedStatement.setInt(5, ticket.getId());
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void delete(Ticket ticket) {
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.DELETE_TICKET)){
            preparedStatement.setInt(1, ticket.getId());
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
