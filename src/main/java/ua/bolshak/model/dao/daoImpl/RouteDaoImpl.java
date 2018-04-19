package ua.bolshak.model.dao.daoImpl;

import org.apache.log4j.Logger;
import ua.bolshak.model.MysqlConnectionPool;
import ua.bolshak.model.dao.idao.IRouteDao;
import ua.bolshak.model.dao.util.ColumnName;
import ua.bolshak.model.dao.util.SqlQuery;
import ua.bolshak.model.entity.Cruise;
import ua.bolshak.model.entity.Port;
import ua.bolshak.model.entity.Route;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl implements IRouteDao {
    private static RouteDaoImpl instance;
    private static Logger LOGGER = Logger.getLogger(Route.class);

    private RouteDaoImpl(){}

    private static Route initialization(ResultSet resultSet) throws SQLException {
        Route route = new Route();
        route.setId(resultSet.getInt(ColumnName.ID_ROUTE));
        route.setName(resultSet.getString(ColumnName.ROUTE_NAME));
        return route;
    }

    @Override
    public List<Route> findAll() {
        List<Route> routes = new ArrayList<>();
        try (Connection connection = MysqlConnectionPool.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SqlQuery.FIND_ALL_ROUTES)){
            while (resultSet.next()){
                routes.add(initialization(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return routes;
    }

    @Override
    public List<Route> findAllByPort(Port port) {
        List<Route> routes = new ArrayList<>();
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.FIND_ALL_ROUTES_BY_PORT)){
            preparedStatement.setInt(1, port.getId());
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    routes.add(initialization(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return routes;
    }

    @Override
    public Route findById(int id) {
        Route route = null;
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.FIND_ROUTES_BY_ID)){
            preparedStatement.setInt(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    route = initialization(resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return route;
    }

    @Override
    public Route findByCruise(Cruise cruise) {
        Route route = null;
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.FIND_ROUTES_BY_CRUISE)){
            preparedStatement.setInt(1, cruise.getId());
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    route = initialization(resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return route;
    }

    @Override
    public void add(Route route) {
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.ADD_ROUTES)){
            preparedStatement.setString(1, route.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void update(Route route) {
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.UPDATE_ROUTES)){
            preparedStatement.setString(1, route.getName());
            preparedStatement.setInt(2, route.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void delete(Route route) {
        try(Connection connection = MysqlConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.DELETE_ROUTES)){
            preparedStatement.setInt(1, route.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        }

        public static synchronized RouteDaoImpl getInstance() {
        if (instance == null){
            instance = new RouteDaoImpl();
        }
        return instance;
    }
}
