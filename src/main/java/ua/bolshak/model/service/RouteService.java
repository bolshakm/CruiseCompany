package ua.bolshak.model.service;

import ua.bolshak.model.dao.DaoFactory;
import ua.bolshak.model.entity.Cruise;
import ua.bolshak.model.entity.Port;
import ua.bolshak.model.entity.Route;

import java.util.List;

public class RouteService {

    public static List<Route> findAll(){
        return getFull(DaoFactory.getRouteDaoImpl().findAll());
    }

    public static List<Route> findAllByPort(Port port){
        return getFull(DaoFactory.getRouteDaoImpl().findAllByPort(port));
    }

    public static Route findById (int id){
        return getFull(DaoFactory.getRouteDaoImpl().findById(id));
    }

    public static Route findByCruise(Cruise cruise){
        return getFull(DaoFactory.getRouteDaoImpl().findByCruise(cruise));
    }

    public static List<Route> findAllLazyByPort(Port port){
        return DaoFactory.getRouteDaoImpl().findAllByPort(port);
    }


    public static Route findLazyByCruise(Cruise cruise){
        return DaoFactory.getRouteDaoImpl().findByCruise(cruise);
    }

    public static void add(Route route){
        DaoFactory.getRouteDaoImpl().add(route);
    }

    public static void update(Route route){
        DaoFactory.getRouteDaoImpl().update(route);
    }

    public static void delete(Route route){
        DaoFactory.getRouteDaoImpl().delete(route);
    }

    public static List<Route> getFull(List<Route> routes){
        if (routes != null){
            for (Route route : routes) {
                route.setCruises(CruiseService.findAllLazyByRoute(route));
                route.setPorts(PortService.findAllLazyByRoute(route));
            }
        }
        return routes;
    }

    public static Route getFull(Route route){
        if (route != null){
            route.setCruises(CruiseService.findAllLazyByRoute(route));
            route.setPorts(PortService.findAllLazyByRoute(route));

        }
        return route;
    }
}
