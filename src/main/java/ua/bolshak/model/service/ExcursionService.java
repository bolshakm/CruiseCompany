package ua.bolshak.model.service;

import ua.bolshak.model.dao.DaoFactory;
import ua.bolshak.model.entity.Cruise;
import ua.bolshak.model.entity.Excursion;
import ua.bolshak.model.entity.Port;
import ua.bolshak.model.entity.Ticket;

import java.util.ArrayList;
import java.util.List;

public class ExcursionService {

    public static List<Excursion> findAll(){
        return getFull(DaoFactory.getExcursionDao().findAll());
    }

    public static List<Excursion> findAllByPort(Port port){
        return getFull(DaoFactory.getExcursionDao().findAllByPort(port));
    }

    public static List<Excursion> findAllByLazyCruse(Cruise cruise){
        List<Excursion> excursions = new ArrayList<>();
        cruise = CruiseService.getFull(cruise);
        for (Port port : cruise.getPorts()) {
            port = PortService.getFull(port);
            for (Excursion excursion : port.getExcursions()) {
                excursions.add(excursion);
            }
        }
        return excursions;
    }

    public static List<Excursion> getListById(String[] selectedId){
        List<Excursion> excursions = new ArrayList<>();
        for (String id : selectedId ) {
            excursions.add(findById(Integer.parseInt(id)));
        }
        return excursions;
    }

    public static List<Excursion> findAllByTicket(Ticket ticket){
        return getFull(DaoFactory.getExcursionDao().findAllByTicket(ticket));
    }

    public static List<Excursion> findAllLazyByPort(Port port){
        return DaoFactory.getExcursionDao().findAllByPort(port);
    }

    public static List<Excursion> findAllLazyByTicket(Ticket ticket){
        return DaoFactory.getExcursionDao().findAllByTicket(ticket);
    }

    public static Excursion findById(int id){
        return getFull(DaoFactory.getExcursionDao().findById(id));
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

    public static Excursion getFull(Excursion excursion){
        excursion.setPort(PortService.findLazyByExcursion(excursion));
        excursion.setTickets(TicketService.findAllLazyByExcursion(excursion));
        return excursion;
    }

    public static List<Excursion> getFull(List<Excursion> excursions){
        for (Excursion excursion : excursions) {
            excursion.setPort(PortService.findLazyByExcursion(excursion));
            excursion.setTickets(TicketService.findAllLazyByExcursion(excursion));
        }
        return excursions;
    }

}
