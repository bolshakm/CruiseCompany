package ua.bolshak.model.service;

import ua.bolshak.model.dao.DaoFactory;
import ua.bolshak.model.entity.*;

import java.util.List;

public class CruiseService {

    public static List<Cruise> findAll(){
        return DaoFactory.getCruiseDao().findAll();
    }

    public static List<Cruise> findAllBYPort(Port port){
        return DaoFactory.getCruiseDao().findAllByPort(port);
    }

    public static List<Cruise> findAllByStatus(CruiseStatus cruiseStatus){
        return DaoFactory.getCruiseDao().findAllByStatus(cruiseStatus);
    }

    public static List<Cruise> findAllByShip(Ship ship){
        return DaoFactory.getCruiseDao().findAllByShip(ship);
    }

    public static List<Cruise> findAllBuUser(User user){
        return DaoFactory.getCruiseDao().findAllByUser(user);
    }

    public static Cruise findByTicket(Ticket ticket){
        return DaoFactory.getCruiseDao().findByTicket(ticket);
    }

    public static Cruise findById(int id){
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

    private static List<Cruise> getFullCruises(List<Cruise> cruises){
        for (Cruise cruise :
                cruises) {
            cruise.setPorts(PortService.findAllByCruise(cruise));
            cruise.setShip(ShipService.findByCruise(cruise));
            cruise.setStatus(CruiseStatusService.findByCruise(cruise));
            cruise.setTickets(TicketService.findAllByCruise(cruise));
        }
        return cruises;
    }

    private static Cruise getFullCruise(Cruise cruise){
        cruise.setPorts(PortService.findAllByCruise(cruise));
        cruise.setShip(ShipService.findByCruise(cruise));
        cruise.setStatus(CruiseStatusService.findByCruise(cruise));
        cruise.setTickets(TicketService.findAllByCruise(cruise));
        return cruise;
    }
}
