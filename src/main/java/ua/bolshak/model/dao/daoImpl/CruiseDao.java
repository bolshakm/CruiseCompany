package ua.bolshak.model.dao.daoImpl;

import org.apache.log4j.Logger;
import ua.bolshak.model.MysqlConnectionPool;
import ua.bolshak.model.dao.idao.CruiseIDao;
import ua.bolshak.model.dao.util.ColumnName;
import ua.bolshak.model.dao.util.SqlQuery;
import ua.bolshak.model.entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CruiseDao implements CruiseIDao{

    private static CruiseDao instance = null;
    private static Logger LOGGER = Logger.getLogger(CruiseDao.class);

    private CruiseDao(){

    }

    private Cruise initialization(ResultSet resultSet) throws SQLException {
        Cruise cruise = new Cruise();
        cruise.setId(resultSet.getInt(ColumnName.ID_CRUISE));
        cruise.setName(resultSet.getString(ColumnName.CRUISE_NAME));
        cruise.setFrom(resultSet.getDate(ColumnName.CRUISE_FROM));
        cruise.setTo(resultSet.getDate(ColumnName.CRUISE_TO));
        return cruise;
    }

    public synchronized static CruiseDao getInstance() {
        if (instance == null){
            instance = new CruiseDao();
        }
        return instance;
    }

    @Override
    public List<Cruise> findAll() {
        List<Cruise> cruises = new ArrayList<>();
        try (Connection connection = MysqlConnectionPool.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SqlQuery.FIND_ALL_CRUISES)){
            while (resultSet.next()){
                cruises.add(initialization(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return cruises;
    }

    @Override
    public List<Cruise> findAllByStatus(CruiseStatus cruiseStatus) {
        List<Cruise> cruises = new ArrayList<>();
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.FIND_ALL_CRUISES_BY_STATUS)){
            preparedStatement.setInt(1, cruiseStatus.getId());
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    cruises.add(initialization(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return cruises;
    }

    @Override
    public List<Cruise> findAllByShip(Ship ship) {
        List<Cruise> cruises = new ArrayList<>();
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.FIND_ALL_CRUISES_BY_SHIP)){
            preparedStatement.setInt(1, ship.getId());
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    cruises.add(initialization(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return cruises;
    }

    @Override
    public List<Cruise> findAllByRoute(Route route) {
        List<Cruise> cruises = new ArrayList<>();
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.FIND_ALL_CRUISES_BY_ROUTE)){
            preparedStatement.setInt(1, route.getId());
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    cruises.add(initialization(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return cruises;
    }

    @Override
    public List<Cruise> findAllByUser(User user) {
        List<Cruise> cruises = new ArrayList<>();
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.FIND_ALL_CRUISES_BY_USER)){
            preparedStatement.setInt(1, user.getId());
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    cruises.add(initialization(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return cruises;
    }

    @Override
    public Cruise findByTicket(Ticket ticket) {
        Cruise cruise = null;
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.FIND_CRUISE_BY_TICKET)){
            preparedStatement.setInt(1, ticket.getId());
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    cruise = initialization(resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return cruise;
    }

    @Override
    public Cruise findById(int id) {
        Cruise cruise = null;
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.FIND_CRUISE_BY_ID)){
            preparedStatement.setInt(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    cruise = initialization(resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return cruise;
    }

    @Override
    public Cruise findByName(String name) {
        Cruise cruise = null;
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.FIND_CRUISE_BY_NAME)){
            preparedStatement.setString(1, name);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    cruise = initialization(resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return cruise;
    }

    @Override
    public void add(Cruise cruise) {
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement psForCruiseTable = connection.prepareStatement(SqlQuery.ADD_CRUISE)){
            psForCruiseTable.setString(1, cruise.getName());
            psForCruiseTable.setDate(2,cruise.getFrom());
            psForCruiseTable.setDate(3, cruise.getTo());
            psForCruiseTable.setInt(4, cruise.getShip().getId());
            psForCruiseTable.setInt(5, cruise.getStatus().getId());
            psForCruiseTable.executeUpdate();
//            cruise.setId(findByName(cruise.getName()).getId());
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void update(Cruise cruise) {
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement psForUpdateCruise = connection.prepareStatement(SqlQuery.UPDATE_CRUISE)){
            psForUpdateCruise.setString(1, cruise.getName());
            psForUpdateCruise.setDate(2,cruise.getFrom());
            psForUpdateCruise.setDate(3, cruise.getTo());
            psForUpdateCruise.setInt(4, cruise.getShip().getId());
            psForUpdateCruise.setInt(5, cruise.getStatus().getId());
            psForUpdateCruise.setInt(6, cruise.getRoute().getId());
            psForUpdateCruise.setInt(7, cruise.getId());
            psForUpdateCruise.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void delete(Cruise cruise) {
        PreparedStatement psForDeleteCruiseHasPort = null;
        PreparedStatement psForDeleteCruise = null;
        try(Connection connection = MysqlConnectionPool.getConnection()){
            psForDeleteCruiseHasPort = connection.prepareStatement(SqlQuery.DELETE_CRUISE_HAS_PORTS);
            psForDeleteCruiseHasPort.setInt(1, cruise.getId());
            psForDeleteCruiseHasPort.executeUpdate();
            psForDeleteCruiseHasPort.close();
            psForDeleteCruise = connection.prepareStatement(SqlQuery.DELETE_CRUISE);
            psForDeleteCruise.setInt(1, cruise.getId());
            psForDeleteCruise.executeUpdate();
            psForDeleteCruise.close();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            try {
                if (psForDeleteCruiseHasPort != null) {
                    psForDeleteCruiseHasPort.close();
                }
                if (psForDeleteCruise != null) {
                    psForDeleteCruise.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }
}
