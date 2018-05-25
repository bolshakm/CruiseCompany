package ua.bolshak.model.service;

import org.apache.log4j.Logger;
import ua.bolshak.model.dao.DaoFactory;
import ua.bolshak.model.entity.*;

import java.io.UnsupportedEncodingException;
import java.util.*;

public class CruiseService {
    private static final Logger LOGGER = Logger.getLogger(CruiseService.class);

    public static List<Cruise> findAll() {
        return getFull(DaoFactory.getCruiseDao().findAll());
    }

    public static List<Cruise> findAllActual() {
        List<Cruise> cruises = new ArrayList<>();
        Date now = new Date();
        for (Cruise cruise : findAll()) {
            List<Ticket> tickets = cruise.getTickets();
            int countOfSeats = cruise.getShip().getNumberOfSeats();
            if (cruise.getTo().getTime() >= now.getTime() && tickets.size() < countOfSeats) {
                cruises.add(cruise);
            }
        }
        return cruises;
    }

    public static List<Cruise> findAllByShip(Ship ship) {
        return getFull(DaoFactory.getCruiseDao().findAllByShip(ship));
    }

    public static List<Cruise> findAllActualByShip(Ship ship) {
        List<Cruise> cruises = new ArrayList<>();
        Date now = new Date();
        for (Cruise cruise : getFull(DaoFactory.getCruiseDao().findAllByShip(ship))) {
            if (cruise.getTo().getTime() >= now.getTime()) {
                cruises.add(cruise);
            }
        }
        return cruises;
    }

    public static HashMap<String, Double> getMapAllCruiseComesMoney() {
        List<Cruise> cruises = CruiseService.findAll();
        HashMap<String, Double> cruisesMoney = new HashMap<>();
        for (Cruise cruise : cruises) {
            cruisesMoney.put(cruise.getName(), CruiseService.countOfMoneyByCruise(cruise));
        }
        return cruisesMoney;
    }

    public static double countOfMoneyByCruise(Cruise cruise) {
        double count = 0;
        for (Ticket ticket : cruise.getTickets()) {
            count += ticket.getPrice();
        }
        return count;
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
            if (cruiseWithSearchingParameter.getName() != null) {
                resultName = cruise.getName().toLowerCase().contains(cruiseWithSearchingParameter.getName().toLowerCase());
            }
            if (cruiseWithSearchingParameter.getFrom() != null) {
                resultFrom = cruiseWithSearchingParameter.getFrom().getTime() <= cruise.getFrom().getTime();
            }
            if (cruiseWithSearchingParameter.getTo() != null) {
                resultTo = cruiseWithSearchingParameter.getTo().getTime() >= cruise.getTo().getTime();
            }
            if (cruiseWithSearchingParameter.getShip() != null) {
                resultShip = cruise.getShip().getNumber().contains(cruiseWithSearchingParameter.getShip().getNumber());
            }
            if (cruiseWithSearchingParameter.getStatus() != null) {
                resultStatus = cruiseWithSearchingParameter.getStatus().equals(cruise.getStatus());
            }
            if (cruiseWithSearchingParameter.getRoute() != null) {
                resultRoute = cruiseWithSearchingParameter.getRoute().equals(cruise.getRoute());
            }
            if (resultName && resultFrom && resultTo && resultShip && resultStatus && resultRoute) {
                cruises.add(cruise);
            }

        }
        return cruises;
    }

    public static Cruise getEncodingCruise(Cruise cruise) {
        try {
            if (cruise.getName() != null) {
                cruise.setName(new String(cruise.getName().getBytes("ISO-8859-1"), "cp1251"));
            }
            if (cruise.getShip() != null) {
                cruise.setShip(ShipService.getEncodingShip(cruise.getShip()));
            }
            if (cruise.getRoute() != null) {
                cruise.setRoute(RouteService.getEncodingRoute(cruise.getRoute()));
            }
            if (cruise.getStatus() != null) {
                cruise.setStatus(CruiseStatusService.getEncodingCruiseStatus(cruise.getStatus()));
            }
            if (cruise.getTickets() != null) {
                cruise.setTickets(TicketService.getEncodingTicket(cruise.getTickets()));
            }
            if (cruise.getUsers() != null) {
                cruise.setUsers(UserService.getEncodingUser(cruise.getUsers()));
            }
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e);
        }
        return cruise;
    }

    public static List<Cruise> getEncodingCruise(List<Cruise> cruises) {
        List<Cruise> encodingCruises = null;
        if (cruises != null) {
            encodingCruises = new ArrayList<>();
            for (Cruise cruise : cruises) {
                encodingCruises.add(getEncodingCruise(cruise));
            }
        }
        return encodingCruises;
    }

    public static boolean checkActive(Cruise cruise) {
        boolean result = false;
        Date now = new Date();
        if (now.getTime() < cruise.getTo().getTime()) {
            result = !cruise.getTickets().isEmpty();
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

    public static Cruise findById(int id) {
        return getFull(DaoFactory.getCruiseDao().findById(id));
    }

    static List<Cruise> findAllLazyByStatus(CruiseStatus cruiseStatus) {
        return DaoFactory.getCruiseDao().findAllByStatus(cruiseStatus);
    }

    static List<Cruise> findAllLazyByRoute(Route route) {
        return DaoFactory.getCruiseDao().findAllByRoute(route);
    }

    static List<Cruise> findAllLazyByShip(Ship ship) {
        return DaoFactory.getCruiseDao().findAllByShip(ship);
    }

    static Cruise findLazyByTicket(Ticket ticket) {
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
