package ua.bolshak.model.service;

import ua.bolshak.model.dao.DaoFactory;
import ua.bolshak.model.entity.Cruise;
import ua.bolshak.model.entity.Excursion;
import ua.bolshak.model.entity.Port;
import ua.bolshak.model.entity.Route;

import java.util.ArrayList;
import java.util.List;

public class PortService {

    public static List<Port> findAll(){
        return getFull(DaoFactory.getPortDao().findAll());
    }

    public static Port findById(int id){
        return getFull(DaoFactory.getPortDao().findById(id));
    }

    public static List<Port> findAllByCruise(Cruise cruise){
        return getFull(DaoFactory.getPortDao().findAllByCruise(cruise));
    }

    public static List<Port> findAllByRoute(Route route){
        return getFull(DaoFactory.getPortDao().findAllByRoute(route));
    }

    public static Port findByExcursion(Excursion excursion){
        return getFull(DaoFactory.getPortDao().findByExcursion(excursion));
    }

    public static List<Port> findAllLazyByCruise(Cruise cruise){
        return DaoFactory.getPortDao().findAllByCruise(cruise);
    }

    public static Port findLazyByExcursion(Excursion excursion){
        return DaoFactory.getPortDao().findByExcursion(excursion);
    }

    public static List<Port> findAllLazyByRoute(Route route){
        return DaoFactory.getPortDao().findAllByRoute(route);
    }

    public static void add (Port port){
        DaoFactory.getPortDao().add(port);
    }

    public static void update(Port port){
        DaoFactory.getPortDao().update(port);
    }

    public static void delete(Port port){
        DaoFactory.getPortDao().delete(port);
    }

    public static Port getFull(Port port){
        if (port != null) {
            port.setCruises(CruiseService.findAllLazyBYPort(port));
            port.setExcursions(ExcursionService.findAllLazyByPort(port));
            port.setRoutes(RouteService.findAllLazyByPort(port));
        }
        return port;
    }

    public static List<Port> getFull(List<Port> ports){
        if (ports != null) {
            for (Port port : ports) {
                port.setCruises(CruiseService.findAllLazyBYPort(port));
                port.setExcursions(ExcursionService.findAllLazyByPort(port));
                port.setRoutes(RouteService.findAllLazyByPort(port));
            }
        }
        return ports;
    }

    public static List<Port> getListPort(String[] idPorts){
        List<Port> ports = new ArrayList<>();
        for (String id : idPorts) {
            ports.add(findById(Integer.parseInt(id)));
        }
        return ports;
    }

}
