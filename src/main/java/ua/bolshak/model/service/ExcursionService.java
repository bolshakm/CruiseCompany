package ua.bolshak.model.service;

import ua.bolshak.model.dao.DaoFactory;
import ua.bolshak.model.entity.Excursion;
import ua.bolshak.model.entity.Port;
import ua.bolshak.model.entity.Ticket;

import java.util.List;

public class ExcursionService {

    public static List<Excursion> findAll(){
        return DaoFactory.getExcursionDao().findAll();
    }

    public static List<Excursion> findAllByPort(Port port){
        return DaoFactory.getExcursionDao().findAllByPort(port);
    }

    public static List<Excursion> findAllByTicket(Ticket ticket){
        return DaoFactory.getExcursionDao().findAllByTicket(ticket);
    }

    public static Excursion findById(int id){
        return DaoFactory.getExcursionDao().findById(id);
    }

    public static void add(Excursion excursion, Port port){
        DaoFactory.getExcursionDao().add(excursion, port);
    }

    public static void update(Excursion excursion, Port port){
        DaoFactory.getExcursionDao().update(excursion, port);
    }

    public static void delete(Excursion excursion){
        DaoFactory.getExcursionDao().delete(excursion);
    }

}
