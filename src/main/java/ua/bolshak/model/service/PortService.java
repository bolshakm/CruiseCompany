package ua.bolshak.model.service;

import ua.bolshak.model.dao.DaoFactory;
import ua.bolshak.model.entity.Cruise;
import ua.bolshak.model.entity.Excursion;
import ua.bolshak.model.entity.Port;

import java.util.List;

public class PortService {

    public static List<Port> findAll(){
        return DaoFactory.getPortDao().findAll();
    }

    public static List<Port> findAllByCruise(Cruise cruise){
        return DaoFactory.getPortDao().findAllByCruise(cruise);
    }


    public static Port findById(int id){
        return DaoFactory.getPortDao().findById(id);
    }

    public static Port findByExcursion(Excursion excursion){
        return DaoFactory.getPortDao().findByExcursion(excursion);
    }

    private static void add (Port port){
        DaoFactory.getPortDao().add(port);
    }

    private static void update(Port port){
        DaoFactory.getPortDao().update(port);
    }

    private static void delete(Port port){
        DaoFactory.getPortDao().delete(port);
    }
}
