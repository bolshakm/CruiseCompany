package ua.bolshak.model.dao.daoImpl;

import org.apache.log4j.Logger;
import ua.bolshak.exception.NotEnoughMoneyException;
import ua.bolshak.model.MysqlConnectionPool;
import ua.bolshak.model.dao.idao.UserIDao;
import ua.bolshak.model.dao.util.ColumnName;
import ua.bolshak.model.dao.util.SqlQuery;
import ua.bolshak.model.entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements UserIDao{
    private static UserDao instance;
    private static Logger LOGGER = Logger.getLogger(UserDao.class);

    private UserDao(){
    }

    public synchronized static UserDao getInstance() {
        if (instance == null){
            instance = new UserDao();
        }
        return instance;
    }

    private User initialization(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt(ColumnName.ID_USER));
        user.setLogin(resultSet.getString(ColumnName.LOGIN));
        user.setPassword(resultSet.getString(ColumnName.PASSWORD));
        user.setName(resultSet.getString(ColumnName.USER_NAME));
        user.setLastName(resultSet.getString(ColumnName.USER_LAST_NAME));
        user.setEmail(resultSet.getString(ColumnName.EMAIL));
        user.setMoney(resultSet.getDouble(ColumnName.MONEY));
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = MysqlConnectionPool.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SqlQuery.FIND_ALL_USERS)){
            while (resultSet.next()){
                users.add(initialization(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return users;
    }

    @Override
    public List<User> findAllByRole(Role role) {
        List<User> users = new ArrayList<>();
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.FIND_ALL_USERS_BY_ROLE)){
            preparedStatement.setInt(1, role.getId());
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    users.add(initialization(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return users;
    }

    @Override
    public List<User> findAllByCruise(Cruise cruise) {
        List<User> users = new ArrayList<>();
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.FIND_ALL_USERS_BY_CRUISE)){
            preparedStatement.setInt(1, cruise.getId());
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    users.add(initialization(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return users;
    }

    @Override
    public List<User> findAllByShip(Ship ship) {
        List<User> users = new ArrayList<>();
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.FIND_ALL_USER_BY_SHIP)){
            preparedStatement.setInt(1, ship.getId());
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    users.add(initialization(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return users;
    }

    @Override
    public List<User> findAllByTicketType(TicketType ticketType) {
        List<User> users = new ArrayList<>();
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.FIND_ALL_USERS_BY_TICKET)){
            preparedStatement.setInt(1, ticketType.getId());
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    users.add(initialization(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return users;
    }

    @Override
    public User findById(int id) {
        User user = null;
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.FIND_USER_BY_ID)){
            preparedStatement.setInt(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    user = initialization(resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return user;
    }

    @Override
    public User findByTicket(Ticket ticket) {
        User user = null;
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.FIND_USER_BY_TICKET)){
            preparedStatement.setInt(1, ticket.getId());
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    user = initialization(resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return user;
    }

    @Override
    public User findByLogin(String login) {
        User user = null;
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.FIND_USER_BY_LOGIN)){
            preparedStatement.setString(1, login);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    user = initialization(resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return user;
    }

    @Override
    public User findByEmail(String email) {
        User user = null;
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.FIND_USER_BY_EMAIL)){
            preparedStatement.setString(1, email);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    user = initialization(resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return user;
    }

    public void transferMoneyFromUser(User user, double money){
        Connection connection = null;
        PreparedStatement checkUserMoney = null;
        PreparedStatement checkAdminMoney = null;
        PreparedStatement rsFrom = null;
        PreparedStatement rsTo = null;
        try {
            connection = MysqlConnectionPool.getConnection();
            connection.setAutoCommit(false);
            double usersMoney = 0;
            double adminsMoney = 0;
            checkUserMoney = connection.prepareStatement(SqlQuery.CHECK_USERS_MONEY);
            checkUserMoney.setInt(1, user.getId());
            try(ResultSet resultSet = checkUserMoney.executeQuery()){
                while (resultSet.next()) {
                    usersMoney = resultSet.getDouble(ColumnName.MONEY);
                }
            }
            checkAdminMoney = connection.prepareStatement(SqlQuery.CHECK_ADMIN_MONEY);
            checkAdminMoney.setInt(1, 1);
            try(ResultSet resultSet = checkAdminMoney.executeQuery()){
                while (resultSet.next()) {
                    adminsMoney = resultSet.getDouble(ColumnName.MONEY);
                }
            }
            if (adminsMoney < money){
                throw new NotEnoughMoneyException("Administrator doesn't have enough money!");
            } else {
                adminsMoney -= money;
                usersMoney += money;
            }
            rsFrom = connection.prepareStatement(SqlQuery.GET_MONEY);
            rsFrom.setDouble(1, adminsMoney);
            rsFrom.setInt(2, 1);
            rsFrom.executeUpdate();
            rsTo = connection.prepareStatement(SqlQuery.SET_MONEY);
            rsTo.setDouble(1, usersMoney);
            rsTo.setInt(2, user.getId());
            rsTo.executeUpdate();
            connection.commit();
        } catch (NotEnoughMoneyException e) {
            try {
                connection.rollback();
            } catch (SQLException rollBackException) {
                LOGGER.error(rollBackException.getMessage());
            }
            LOGGER.error(e.getMessage());
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException rollBackException) {
                LOGGER.error(rollBackException.getMessage());
            }
            LOGGER.error(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (checkUserMoney != null) {
                    checkUserMoney.close();
                }
                if (checkAdminMoney != null) {
                    checkAdminMoney.close();
                }
                if (rsTo != null) {
                    rsTo.close();
                }
                if (rsFrom != null) {
                    rsFrom.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }


    @Override
    public void add(User user) {
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.ADD_USER)){
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setDouble(6, user.getMoney());
            preparedStatement.setInt(7, user.getRole().getId());
            preparedStatement.setInt(8, user.getShip().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void update(User user) {
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.UPDATE_USER)){
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setDouble(6, user.getMoney());
            preparedStatement.setInt(7, user.getRole().getId());
            preparedStatement.setInt(8, user.getShip().getId());
            preparedStatement.setInt(9, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void delete(User user) {
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.DELETE_USER);
            PreparedStatement psForDeleteTicketByUser = connection.prepareStatement(SqlQuery.DELETE_TICKET_BY_USER)){
            if (!user.getTickets().isEmpty()) {
                psForDeleteTicketByUser.setInt(1, user.getId());
                psForDeleteTicketByUser.executeUpdate();
            }
            preparedStatement.setInt(1, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
