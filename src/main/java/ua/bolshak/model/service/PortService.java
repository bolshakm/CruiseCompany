package ua.bolshak.model.service;

import org.apache.log4j.Logger;
import ua.bolshak.model.dao.DaoFactory;
import ua.bolshak.model.entity.Cruise;
import ua.bolshak.model.entity.Excursion;
import ua.bolshak.model.entity.Port;
import ua.bolshak.model.entity.Route;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class PortService {
    private static final Logger LOGGER = Logger.getLogger(BonusService.class);

    public static List<Port> findAll(){
        return getFull(DaoFactory.getPortDao().findAll());
    }

    public static List<Port> getListById(String[] idPorts){
        List<Port> ports = new ArrayList<>();
        for (String idPort : idPorts) {
            ports.add(findById(Integer.parseInt(idPort)));
        }
        return ports;
    }

    public static Port findById(int id){
        return getFull(DaoFactory.getPortDao().findById(id));
    }

    public static List<Port> findAllByRoute(Route route){
        return getFull(DaoFactory.getPortDao().findAllByRoute(route));
    }

    public static Port findByExcursion(Excursion excursion){
        return getFull(DaoFactory.getPortDao().findByExcursion(excursion));
    }

    public static Port findLazyByExcursion(Excursion excursion){
        return DaoFactory.getPortDao().findByExcursion(excursion);
    }

    public static Port getEncodingPort(Port port){
        try {
            if (port.getName() != null) {
                port.setName(new String(port.getName().getBytes("ISO-8859-1"), "cp1251"));
            }
            if (port.getCity() != null){
                port.setCity(new String(port.getCity().getBytes("ISO-8859-1"),"cp1251"));
            }
            if (port.getCountry() != null){
                port.setCountry(new String(port.getCountry().getBytes("ISO-8859-1"),"cp1251"));
            }
            if (port.getCruises() != null){
                port.setCruises(CruiseService.getEncodingCruise(port.getCruises()));
            }
            if (port.getRoutes() != null){
                port.setRoutes(RouteService.getEncodingRoute(port.getRoutes()));
            }
            if (port.getExcursions() != null){
                port.setExcursions(ExcursionService.getEncodingExcursion(port.getExcursions()));
            }
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e);
        }
        return port;
    }

    public static List<Port> getEncodingPort(List<Port> ports){
        List<Port> encodingPort = null;
        if (ports != null){
            encodingPort = new ArrayList<>();
            for (Port port : ports) {
                encodingPort.add(getEncodingPort(port));
            }
        }
        return encodingPort;
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
            port.setExcursions(ExcursionService.findAllLazyByPort(port));
            port.setRoutes(RouteService.findAllLazyByPort(port));
        }
        return port;
    }

    public static List<Port> getFull(List<Port> ports){
        if (ports != null) {
            for (Port port : ports) {
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
