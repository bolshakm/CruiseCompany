package ua.bolshak.model.dao.daoImpl;

import org.apache.log4j.Logger;
import ua.bolshak.model.MysqlConnectionPool;
import ua.bolshak.model.dao.idao.TicketTypeIDao;
import ua.bolshak.model.dao.util.ColumnName;
import ua.bolshak.model.dao.util.SqlQuery;
import ua.bolshak.model.entity.Ticket;
import ua.bolshak.model.entity.TicketType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketTypeDao implements TicketTypeIDao{
    private static TicketTypeDao instance;
    private static Logger LOGGER = Logger.getLogger(TicketTypeDao.class);

    private TicketTypeDao(){}

    public synchronized static TicketTypeDao getInstance() {
        if (instance == null){
            instance = new TicketTypeDao();
        }
        return instance;
    }

    private TicketType initialization (ResultSet resultSet) throws SQLException {
        TicketType ticketType = new TicketType();
        ticketType.setId(resultSet.getInt(ColumnName.ID_TICKET_TYPE));
        ticketType.setName(resultSet.getString(ColumnName.TICKET_TYPE_NAME));
        ticketType.setPrice(resultSet.getDouble(ColumnName.TICKET_TYPE_PRICE));
        return ticketType;
    }


    @Override
    public List<TicketType> findAll() {
        List<TicketType> ticketTypes = new ArrayList<>();
        try (Connection connection = MysqlConnectionPool.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SqlQuery.FIND_ALL_TICKET_TYPES)){
            while (resultSet.next()){
                ticketTypes.add(initialization(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return ticketTypes;
    }

    @Override
    public TicketType findById(int id) {
        TicketType ticketType = null;
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.FIND_TICKET_TYPE_BY_ID)){
            preparedStatement.setInt(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    ticketType = initialization(resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return ticketType;
    }

    @Override
    public TicketType findByTicket(Ticket ticket) {
        TicketType ticketType = null;
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.FIND_TICKET_TYPE_BY_TICKET)){
            preparedStatement.setInt(1, ticket.getId());
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    ticketType = initialization(resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return ticketType;
    }

    @Override
    public void add(TicketType ticketType) {
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.ADD_TICKET_TYPE)){
            preparedStatement.setString(1, ticketType.getName());
            preparedStatement.setDouble(2, ticketType.getPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void update(TicketType ticketType) {
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.UPDATE_TICKET_TYPE)){
            preparedStatement.setString(1, ticketType.getName());
            preparedStatement.setDouble(2, ticketType.getPrice());
            preparedStatement.setInt(3, ticketType.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void delete(TicketType ticketType) {
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.DELETE_TICKET_TYPE)){
            preparedStatement.setInt(1, ticketType.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
