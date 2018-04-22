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

    private RouteDaoImpl() {
    }

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
             ResultSet resultSet = statement.executeQuery(SqlQuery.FIND_ALL_ROUTES)) {
            while (resultSet.next()) {
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
        try (Connection connection = MysqlConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.FIND_ALL_ROUTES_BY_PORT)) {
            preparedStatement.setInt(1, port.getId());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
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
        try (Connection connection = MysqlConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.FIND_ROUTES_BY_ID)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
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
    public Route findByName(String name) {
        Route route = null;
        try (Connection connection = MysqlConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.FIND_ROUTES_BY_NAME)) {
            preparedStatement.setString(1, name);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
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
        try (Connection connection = MysqlConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.FIND_ROUTES_BY_CRUISE)) {
            preparedStatement.setInt(1, cruise.getId());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
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
        PreparedStatement psForAddRoute = null;
        PreparedStatement psForAddRouteHasPorts = null;
        try (Connection connection = MysqlConnectionPool.getConnection()) {
            psForAddRoute = connection.prepareStatement(SqlQuery.ADD_ROUTES);
            psForAddRoute.setString(1, route.getName());
            psForAddRoute.executeUpdate();
            route.setId(findByName(route.getName()).getId());
            if (route.getPorts() != null) {
//                addPorts(route);
                psForAddRouteHasPorts = connection.prepareStatement(SqlQuery.ADD_ROUTES_HAS_PORTS);
                for (Port port : route.getPorts()) {
                    psForAddRouteHasPorts.setInt(1, port.getId());
                    psForAddRouteHasPorts.setInt(2, route.getId());
                    psForAddRouteHasPorts.addBatch();
                }
                psForAddRouteHasPorts.executeBatch();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            try {
                if (psForAddRoute != null) {
                    psForAddRoute.close();
                }
                if (psForAddRouteHasPorts != null) {
                    psForAddRouteHasPorts.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }

    @Override
    public void update(Route route) {
        try (Connection connection = MysqlConnectionPool.getConnection();
             PreparedStatement psForUpdateRoutes = connection.prepareStatement(SqlQuery.UPDATE_ROUTES)){
            psForUpdateRoutes.setString(1, route.getName());
            psForUpdateRoutes.setInt(2, route.getId());
            psForUpdateRoutes.executeUpdate();
            if (route.getPorts() != null){
                deletePorts(route);
                addPorts(route);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void delete(Route route) {
        if (route.getPorts() != null){
            deletePorts(route);
        }
        try (Connection connection = MysqlConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.DELETE_ROUTES)) {
            preparedStatement.setInt(1, route.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void addPorts(Route route) {
        try (Connection connection = MysqlConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.ADD_ROUTES_HAS_PORTS)) {
            for (Port port : route.getPorts()) {
                preparedStatement.setInt(1, port.getId());
                preparedStatement.setInt(2, route.getId());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void deletePorts(Route route) {
        try (Connection connection = MysqlConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.DELETE_ROUTES_HAS_PORTS)) {
            preparedStatement.setInt(1, route.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public static synchronized RouteDaoImpl getInstance() {
        if (instance == null) {
            instance = new RouteDaoImpl();
        }
        return instance;
    }
}
