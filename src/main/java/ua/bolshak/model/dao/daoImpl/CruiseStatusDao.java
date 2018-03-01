package ua.bolshak.model.dao.daoImpl;

import org.apache.log4j.Logger;
import ua.bolshak.model.MysqlConnectionPool;
import ua.bolshak.model.dao.idao.CruiseStatusIDao;
import ua.bolshak.model.dao.util.ColumnName;
import ua.bolshak.model.dao.util.SqlQuery;
import ua.bolshak.model.entity.Cruise;
import ua.bolshak.model.entity.CruiseStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CruiseStatusDao implements CruiseStatusIDao{
    private static CruiseStatusDao instance;
    private static Logger LOGGER = Logger.getLogger(CruiseStatusDao.class);

    private CruiseStatusDao(){}

    public synchronized static CruiseStatusDao getInstance() {
        if (instance == null){
            instance = new CruiseStatusDao();
        }
        return instance;
    }

    private CruiseStatus initialization(ResultSet resultSet) throws SQLException {
        CruiseStatus cruiseStatus = new CruiseStatus();
        cruiseStatus.setId(resultSet.getInt(ColumnName.ID_CRUISE_STATUS));
        cruiseStatus.setName(resultSet.getString(ColumnName.CRUISE_STATUS_NAME));
        return cruiseStatus;
    }

    @Override
    public List<CruiseStatus> findAll() {
        List<CruiseStatus> cruiseStatuses = new ArrayList<>();
        try (Connection connection = MysqlConnectionPool.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SqlQuery.FIND_ALL_CRUISE_STATUS)){
            while (resultSet.next()){
                cruiseStatuses.add(initialization(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return cruiseStatuses;
    }

    @Override
    public CruiseStatus findById(int id) {
        CruiseStatus cruiseStatus = new CruiseStatus();
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.FIND_CRUISE_STATUS_BY_ID)){
            preparedStatement.setInt(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    cruiseStatus = initialization(resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return cruiseStatus;
    }

    @Override
    public CruiseStatus findByCruise(Cruise cruise) {
        CruiseStatus cruiseStatus = new CruiseStatus();
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.FIND_CRUISE_STATUS_BY_CRUISE)){
            preparedStatement.setInt(1, cruise.getId());
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    cruiseStatus = initialization(resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return cruiseStatus;
    }

    @Override
    public void add(CruiseStatus cruiseStatus) {
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.ADD_CRUISE_STATUS)){
            preparedStatement.setString(1, cruiseStatus.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void update(CruiseStatus cruiseStatus) {
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.UPDATE_CRUISE_STATUS)){
            preparedStatement.setString(1, cruiseStatus.getName());
            preparedStatement.setInt(2, cruiseStatus.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void delete(CruiseStatus cruiseStatus) {
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.DELETE_CRUISE_STATUS)){
            preparedStatement.setInt(1, cruiseStatus.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
