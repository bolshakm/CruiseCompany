package ua.bolshak.model.service;

import ua.bolshak.model.dao.DaoFactory;
import ua.bolshak.model.entity.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CruiseService {

    public static List<Cruise> findAll() {
        return getFull(DaoFactory.getCruiseDao().findAll());
    }

    public static List<Cruise> findAllByStatus(CruiseStatus cruiseStatus) {
        return getFull(DaoFactory.getCruiseDao().findAllByStatus(cruiseStatus));
    }

    public static List<Cruise> findAllByShip(Ship ship) {
        return getFull(DaoFactory.getCruiseDao().findAllByShip(ship));
    }

    public static List<Cruise> findAllByShips(List<Ship> ships) {
        List<Cruise> cruises = new ArrayList<>();
        for (Ship ship : ships) {
            cruises.addAll(getFull(findAllByShip(ship)));
        }
        return cruises;
    }

    public static List<Cruise> searchCruise(Cruise cruiseWithSearchingParameter) {
        List<Cruise> cruises = new ArrayList<>();
        for (Cruise cruise : findAll()) {
            boolean resultName = true;
            boolean resultFrom = true;
            boolean resultTo = true;
            boolean resultShip = true;
            boolean resultStatus = true;
            boolean resultRoute = true;
            if (cruiseWithSearchingParameter.getName() != null ) {
                resultName = cruise.getName().toLowerCase().contains(cruiseWithSearchingParameter.getName().toLowerCase());
            }
            if (cruiseWithSearchingParameter.getFrom() != null ) {
                resultFrom = cruiseWithSearchingParameter.getFrom().getTime() <= cruise.getFrom().getTime();
            }
            if (cruiseWithSearchingParameter.getTo() != null ) {
                resultTo = cruiseWithSearchingParameter.getTo().getTime() >= cruise.getTo().getTime();
            }
            if (cruiseWithSearchingParameter.getShip() != null ){
                resultShip = cruiseWithSearchingParameter.getShip().equals(cruise.getShip());
            }
            if (cruiseWithSearchingParameter.getStatus() != null ) {
                resultStatus = cruiseWithSearchingParameter.getStatus().equals(cruise.getStatus());
            }
            if (cruiseWithSearchingParameter.getRoute() != null ){
                resultRoute = cruiseWithSearchingParameter.getRoute().equals(cruise.getRoute());
            }
            if (resultName && resultFrom && resultTo && resultShip && resultStatus && resultRoute){
                cruises.add(cruise);
            }

        }
        return cruises;
    }

    public static boolean checkActive(Cruise cruise) {
        boolean result = false;
        Date now = new Date();
        if (now.getTime() < cruise.getTo().getTime()) {
            result = true;
        }
        return result;
    }

    public static boolean checkActive(List<Cruise> cruises) {
        boolean result = false;
        Date now = new Date();
        for (Cruise cruise : cruises) {
            if (now.getTime() < cruise.getTo().getTime()) {
                result = true;
            }
        }
        return result;
    }

    public static List<Cruise> findAllByRoute(Route route) {
        return getFull(DaoFactory.getCruiseDao().findAllByRoute(route));
    }

    public static List<Cruise> findAllByUser(User user) {
        return getFull(DaoFactory.getCruiseDao().findAllByUser(user));
    }

    public static Cruise findByTicket(Ticket ticket) {
        return getFull(DaoFactory.getCruiseDao().findByTicket(ticket));
    }

    public static Cruise findById(int id) {
        return getFull(DaoFactory.getCruiseDao().findById(id));
    }

    public static List<Cruise> findAllLazyByStatus(CruiseStatus cruiseStatus) {
        return DaoFactory.getCruiseDao().findAllByStatus(cruiseStatus);
    }

    public static List<Cruise> findAllLazyByRoute(Route route) {
        return DaoFactory.getCruiseDao().findAllByRoute(route);
    }

    public static List<Cruise> findAllLazyByShip(Ship ship) {
        return DaoFactory.getCruiseDao().findAllByShip(ship);
    }

    public static List<Cruise> findAllLazyBuUser(User user) {
        return DaoFactory.getCruiseDao().findAllByUser(user);
    }

    public static Cruise findLazyByTicket(Ticket ticket) {
        return DaoFactory.getCruiseDao().findByTicket(ticket);
    }

    public static void add(Cruise cruise) {
        DaoFactory.getCruiseDao().add(cruise);
    }

    public static void update(Cruise cruise) {
        DaoFactory.getCruiseDao().update(cruise);
    }

    public static void delete(Cruise cruise) {
        DaoFactory.getCruiseDao().delete(cruise);
    }

    public static Cruise getFull(Cruise cruise) {
        if (cruise != null) {
            cruise.setShip(ShipService.findLazyByCruise(cruise));
            cruise.setStatus(CruiseStatusService.findLazyByCruise(cruise));
            cruise.setUsers(UserService.findAllLazyByCruise(cruise));
            cruise.setRoute(RouteService.findLazyByCruise(cruise));
            cruise.setTickets(TicketService.findAllLazyByCruise(cruise));

        }
        return cruise;
    }

    public static List<Cruise> getFull(List<Cruise> cruises) {
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
