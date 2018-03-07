package ua.bolshak.model.service;

import ua.bolshak.model.dao.DaoFactory;
import ua.bolshak.model.entity.*;

import java.util.List;

public class CruiseService {

    public static List<Cruise> findAllLazy(){
        return DaoFactory.getCruiseDao().findAll();
    }

    public static List<Cruise> findAllLazyBYPort(Port port){
        return DaoFactory.getCruiseDao().findAllByPort(port);
    }

    public static List<Cruise> findAllLazyByStatus(CruiseStatus cruiseStatus){
        return DaoFactory.getCruiseDao().findAllByStatus(cruiseStatus);
    }

    public static List<Cruise> findAllLazyByShip(Ship ship){
        return DaoFactory.getCruiseDao().findAllByShip(ship);
    }

    public static List<Cruise> findAllLazyBuUser(User user){
        return DaoFactory.getCruiseDao().findAllByUser(user);
    }

    public static Cruise findLazyByTicket(Ticket ticket){
        return DaoFactory.getCruiseDao().findByTicket(ticket);
    }

    public static Cruise findLazyById(int id){
        return DaoFactory.getCruiseDao().findById(id);
    }

        public static void add (Cruise cruise){
        DaoFactory.getCruiseDao().add(cruise);
    }

    public static void update (Cruise cruise){
        DaoFactory.getCruiseDao().update(cruise);
    }

    public static void delete(Cruise cruise){
        DaoFactory.getCruiseDao().delete(cruise);
    }

    public static List<Cruise> getFullCruises(List<Cruise> cruises){
        for (Cruise cruise :
                cruises) {
            cruise.setPorts(PortService.findAllLazyByCruise(cruise));
            cruise.setShip(ShipService.findLazyByCruise(cruise));
            cruise.setStatus(CruiseStatusService.findLazyByCruise(cruise));
            cruise.setTickets(TicketService.findAllLazyByCruise(cruise));
        }
        return cruises;
    }

    public static Cruise getFullCruise(Cruise cruise){
        cruise.setPorts(PortService.findAllLazyByCruise(cruise));
        cruise.setShip(ShipService.findLazyByCruise(cruise));
        cruise.setStatus(CruiseStatusService.findLazyByCruise(cruise));
        cruise.setTickets(TicketService.findAllLazyByCruise(cruise));
        return cruise;
    }
}
