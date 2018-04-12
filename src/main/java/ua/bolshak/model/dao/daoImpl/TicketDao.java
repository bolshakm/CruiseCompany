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
        ticket.setName(resultSet.getString(ColumnName.NAME));
        ticket.setLastName(resultSet.getString(ColumnName.LAST_NAME));
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

    public Ticket findByName(String name) {
        Ticket ticket = null;
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.FIND_TICKET_BY_NAME)){
            preparedStatement.setString(1, name);
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
            preparedStatement.setString(2, ticket.getName());
            preparedStatement.setString(3, ticket.getLastName());
            preparedStatement.setInt(4, ticket.getTicketType().getId());
            preparedStatement.setInt(5, ticket.getCruise().getId());
            preparedStatement.setDouble(6, ticket.getPrice());
            preparedStatement.executeUpdate();
            ticket.setId(findByName(ticket.getName()).getId());
            if (ticket.getExcursions() != null){
                addExcursions(ticket);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public void buy(Ticket ticket) {
        PreparedStatement checkUserMoney = null;
        PreparedStatement checkAdminMoney = null;
        PreparedStatement rsFrom = null;
        PreparedStatement rsTo = null;
        try(Connection connection = MysqlConnectionPool.getConnection()){
            connection.setAutoCommit(false);
            double usersMoney = 0;
            double adminsMoney = 0;
            checkUserMoney = connection.prepareStatement("select users.money from users where id_user = ?");
            checkUserMoney.setInt(1, ticket.getUser().getId());
            try(ResultSet resultSet = checkUserMoney.executeQuery()){
                while (resultSet.next()) {
                    usersMoney = resultSet.getDouble(ColumnName.MONEY);
                }
            }
            checkAdminMoney = connection.prepareStatement("select users.money from users where id_user = ?");
            checkAdminMoney.setInt(1, 1);
            try(ResultSet resultSet = checkAdminMoney.executeQuery()){
                while (resultSet.next()) {
                    adminsMoney = resultSet.getDouble(ColumnName.MONEY);
                }
            }
            if (usersMoney < ticket.getPrice()){
                // add own exception
                throw new SQLException("Not enough money");
            } else {
                usersMoney -= ticket.getPrice();
                adminsMoney += ticket.getPrice();
            }
            rsFrom = connection.prepareStatement("update users set money = ? where id_user = ?");
            rsFrom.setDouble(1, usersMoney);
            rsFrom.setInt(2, ticket.getUser().getId());
            rsFrom.executeUpdate();

            rsTo = connection.prepareStatement("update users set money = ? where id_user = ?");
            rsTo.setDouble(1, adminsMoney);
            rsTo.setInt(2, 1);
            rsTo.executeUpdate();
            connection.commit();
            add(ticket);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            try {
                checkUserMoney.close();
                checkAdminMoney.close();
                rsFrom.close();
                rsTo.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    private void addExcursions(Ticket ticket) {
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.ADD_TICKET_HAS_EXCURSIONS)){
            for (Excursion excursion : ticket.getExcursions()) {
                preparedStatement.setInt(1, excursion.getId());
                preparedStatement.setInt(2, ticket.getId());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void addBonuses(Ticket ticket) {
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.ADD_TICKET_HAS_BONUSES)){
            for (Bonus bonus : ticket.getBonuses()) {
                preparedStatement.setInt(1, ticket.getId());
                preparedStatement.setInt(2, bonus.getId());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void update(Ticket ticket) {
        try (Connection connection = MysqlConnectionPool.getConnection()) {
            PreparedStatement psForUpdateTicket = connection.prepareStatement(SqlQuery.UPDATE_TICKET);
            psForUpdateTicket.setInt(1, ticket.getUser().getId());
            psForUpdateTicket.setString(2, ticket.getName());
            psForUpdateTicket.setString(3, ticket.getLastName());
            psForUpdateTicket.setInt(4, ticket.getTicketType().getId());
            psForUpdateTicket.setInt(5, ticket.getCruise().getId());
            psForUpdateTicket.setDouble(6, ticket.getPrice());
            psForUpdateTicket.setInt(7, ticket.getId());
            psForUpdateTicket.executeUpdate();
            psForUpdateTicket.close();
            PreparedStatement psForDeleteBonuses = connection.prepareStatement(SqlQuery.DELETE_TICKET_HAS_BONUSES);
            psForDeleteBonuses.setInt(1, ticket.getId());
            psForDeleteBonuses.executeUpdate();
            psForDeleteBonuses.close();
            PreparedStatement psForUpdateBonuses = connection.prepareStatement(SqlQuery.ADD_TICKET_HAS_BONUSES);
            for (Bonus bonus :
                    ticket.getBonuses()) {
                psForUpdateBonuses.setInt(1, ticket.getId());
                psForUpdateBonuses.setInt(2, bonus.getId());
                psForUpdateBonuses.addBatch();
            }
            psForUpdateBonuses.executeBatch();
            psForUpdateBonuses.close();
            PreparedStatement psForDeleteExcursions = connection.prepareStatement(SqlQuery.DELETE_TICKET_HAS_EXCURSIONS);
            psForDeleteExcursions.setInt(1, ticket.getId());
            psForDeleteExcursions.executeUpdate();
            psForDeleteExcursions.close();
            PreparedStatement psForUpdateExcursions = connection.prepareStatement(SqlQuery.ADD_TICKET_HAS_EXCURSIONS);
            for (Excursion excursion :
                    ticket.getExcursions()) {
                psForUpdateExcursions.setInt(1, excursion.getId());
                psForUpdateExcursions.setInt(2, ticket.getId());
                psForUpdateExcursions.addBatch();
            }
            psForUpdateExcursions.executeBatch();
            psForUpdateExcursions.close();

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void delete(Ticket ticket) {
        try(Connection connection = MysqlConnectionPool.getConnection()){
            PreparedStatement psForDeleteBonuses = connection.prepareStatement(SqlQuery.DELETE_TICKET_HAS_BONUSES);
            psForDeleteBonuses.setInt(1, ticket.getId());
            psForDeleteBonuses.executeUpdate();
            psForDeleteBonuses.close();
            PreparedStatement psForDeleteExcursions = connection.prepareStatement(SqlQuery.DELETE_TICKET_HAS_EXCURSIONS);
            psForDeleteExcursions.setInt(1, ticket.getId());
            psForDeleteExcursions.executeUpdate();
            psForDeleteExcursions.close();
            PreparedStatement psForDeleteTicket = connection.prepareStatement(SqlQuery.DELETE_TICKET);
            psForDeleteTicket.setInt(1, ticket.getId());
            psForDeleteTicket.executeUpdate();
            psForDeleteTicket.close();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
