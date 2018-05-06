package ua.bolshak.model.service;

import ua.bolshak.model.dao.DaoFactory;
import ua.bolshak.model.entity.*;

import java.util.ArrayList;
import java.util.List;

public class CruiseService {

    public static List<Cruise> findAll(){
        return getFull(DaoFactory.getCruiseDao().findAll());
    }

    public static List<Cruise> findAllByStatus(CruiseStatus cruiseStatus){
        return getFull(DaoFactory.getCruiseDao().findAllByStatus(cruiseStatus));
    }

    public static List<Cruise> findAllByShip(Ship ship){
        return getFull(DaoFactory.getCruiseDao().findAllByShip(ship));
    }

    public static List<Cruise> findAllByShips(List<Ship> ships){
        List<Cruise> cruises = new ArrayList<>();
        for (Ship ship : ships) {
            cruises.addAll(getFull(findAllByShip(ship)));
        }
        return cruises;
    }

    public static List<Cruise> findAllByRoute(Route route){
        return getFull(DaoFactory.getCruiseDao().findAllByRoute(route));
    }

    public static List<Cruise> findAllByUser(User user){
        return getFull(DaoFactory.getCruiseDao().findAllByUser(user));
    }

    public static Cruise findByTicket(Ticket ticket){
        return getFull(DaoFactory.getCruiseDao().findByTicket(ticket));
    }

    public static Cruise findById(int id){
        return getFull(DaoFactory.getCruiseDao().findById(id));
    }

    public static List<Cruise> findAllLazyByStatus(CruiseStatus cruiseStatus){
        return DaoFactory.getCruiseDao().findAllByStatus(cruiseStatus);
    }

    public static List<Cruise> findAllLazyByRoute(Route route){
        return DaoFactory.getCruiseDao().findAllByRoute(route);
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

    public static void add (Cruise cruise){
        DaoFactory.getCruiseDao().add(cruise);
    }

    public static void update (Cruise cruise){
        DaoFactory.getCruiseDao().update(cruise);
    }

    public static void delete(Cruise cruise){
        DaoFactory.getCruiseDao().delete(cruise);
    }

    public static Cruise getFull(Cruise cruise){
        if (cruise != null) {
            cruise.setShip(ShipService.findLazyByCruise(cruise));
            cruise.setStatus(CruiseStatusService.findLazyByCruise(cruise));
            cruise.setUsers(UserService.findAllLazyByCruise(cruise));
            cruise.setRoute(RouteService.findLazyByCruise(cruise));
            cruise.setTickets(TicketService.findAllLazyByCruise(cruise));

        }
        return cruise;
    }

    public static List<Cruise> getFull(List<Cruise> cruises){
        if (cruises != null) {
            for (Cruise cruise : cruises) {
                cruise.setShip(ShipService.findLazyByCruise(cruise));
                cruise.setRoute(RouteService.findLazyByCruise(cruise));
                cruise.setStatus(CruiseStatusService.findLazyByCruise(cruise));
                cruise.setTickets(TicketService.findAllLazyByCruise(cruise));
                cruise.setUsers(UserService.findAllLazyByCruise(cruise));
            }
        }
        return cruises;
    }


}
