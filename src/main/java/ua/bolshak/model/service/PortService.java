package ua.bolshak.model.service;

import ua.bolshak.model.dao.DaoFactory;
import ua.bolshak.model.entity.Cruise;
import ua.bolshak.model.entity.Excursion;
import ua.bolshak.model.entity.Port;

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

    public static Port findByExcursion(Excursion excursion){
        return getFull(DaoFactory.getPortDao().findByExcursion(excursion));
    }

    public static List<Port> findAllLazyByCruise(Cruise cruise){
        return DaoFactory.getPortDao().findAllByCruise(cruise);
    }

    public static Port findLazyByExcursion(Excursion excursion){
        return DaoFactory.getPortDao().findByExcursion(excursion);
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
        port.setCruises(CruiseService.findAllLazyBYPort(port));
        port.setExcursions(ExcursionService.findAllLazyByPort(port));
        return port;
    }

    public static List<Port> getFull(List<Port> ports){
        for (Port port : ports) {
            port.setCruises(CruiseService.findAllLazyBYPort(port));
            port.setExcursions(ExcursionService.findAllLazyByPort(port));
        }
        return ports;
    }
}
