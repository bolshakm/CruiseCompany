package ua.bolshak.model.service;

import ua.bolshak.model.dao.DaoFactory;
import ua.bolshak.model.dao.daoImpl.PortDao;
import ua.bolshak.model.entity.Excursion;
import ua.bolshak.model.entity.Port;
import ua.bolshak.model.entity.Ticket;

import java.util.List;

public class ExcusionService {

    public static List<Excursion> findAllLazy(){
        return DaoFactory.getExcursionDao().findAll();
    }

    public static List<Excursion> findAllLazyByPort(Port port){
        return DaoFactory.getExcursionDao().findAllByPort(port);
    }

    public static List<Excursion> findAllLazyByTicket(Ticket ticket){
        return DaoFactory.getExcursionDao().findAllByTicket(ticket);
    }

    public static Excursion findLazyById(int id){
        return DaoFactory.getExcursionDao().findById(id);
    }

    public static void add(Excursion excursion){
        DaoFactory.getExcursionDao().add(excursion);
    }

    public static void update(Excursion excursion){
        DaoFactory.getExcursionDao().update(excursion);
    }

    public static void delete(Excursion excursion){
        DaoFactory.getExcursionDao().delete(excursion);
    }

    public static List<Excursion> getFullExcursions(List<Excursion> excursions){
        for (Excursion excursion :
                excursions) {
            excursion.setPorts(PortService.findLazyByExcursion(excursion));
        }
        return excursions;
    }

    public static Excursion getFullExcursion(Excursion excursion){
        excursion.setPorts(PortService.findLazyByExcursion(excursion));
        return excursion;
    }
}
