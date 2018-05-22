package ua.bolshak.model.service;

import org.apache.log4j.Logger;
import ua.bolshak.model.dao.DaoFactory;
import ua.bolshak.model.entity.*;

import java.io.UnsupportedEncodingException;
import java.util.*;

public class TicketService {
    private static final Logger LOGGER = Logger.getLogger(BonusService.class);

    public static List<Ticket> findAll() {
        return getFull(DaoFactory.getTicketDao().findAll());
    }

    public static List<Ticket> findAllWithFullCruise() {
        List<Ticket> tickets = new ArrayList<>();
        for (Ticket ticket : getFull(DaoFactory.getTicketDao().findAll())) {
            ticket.setCruise(CruiseService.getFull(ticket.getCruise()));
            tickets.add(ticket);
        }
        return tickets;
    }

    public static boolean checkActiveTicketByUser(User user) {
        boolean result = false;
        Date now = new Date();
        List<Ticket> tickets = findAllByUser(user);
        for (Ticket ticket : tickets) {
            java.sql.Date sqlDate = ticket.getCruise().getTo();
            if (now.getTime() < sqlDate.getTime()) {
                result = true;
            }
        }
        return result;
    }

    public static boolean checkActiveTicker(Ticket ticket) {
        boolean result = false;
        Date now = new Date();
        java.sql.Date sqlDate = ticket.getCruise().getFrom();
        if (now.getTime() < sqlDate.getTime()) {
            result = true;
        }
        return result;
    }

    public static boolean checkActiveTicker(List<Ticket> tickets) {
        boolean result = false;
        Date now = new Date();
        for (Ticket ticket : tickets) {
            java.sql.Date sqlDate = getFull(ticket).getCruise().getTo();
            if (now.getTime() < sqlDate.getTime()) {
                result = true;
            }
        }
        return result;
    }

    public static List<Ticket> findAllByUser(User user) {
        return getFull(DaoFactory.getTicketDao().findAllByUser(user));
    }

    public static List<Ticket> findAllByShips(Ship ship) {
        List<Ticket> tickets = new ArrayList<>();
        for (Cruise cruise : ShipService.getFull(ship).getCruises()) {
            tickets.addAll(findAllByCruise(cruise));
        }
        return tickets;
    }

    public static List<Ticket> findAllByBonus(Bonus bonus) {
        return getFull(DaoFactory.getTicketDao().findAllByBonus(bonus));
    }

    public static List<Ticket> findAllByCruise(Cruise cruise) {
        return getFull(DaoFactory.getTicketDao().findAllByCruise(cruise));
    }

    public static List<Ticket> findAllByTicketTypeAndCruise(TicketType ticketType, Cruise cruise) {
        return getFull(DaoFactory.getTicketDao().findAllByTicketTypeAndCruise(ticketType, cruise));
    }

    public static List<Ticket> findAllByTicketType(TicketType ticketType) {
        return getFull(DaoFactory.getTicketDao().findAllByTicketType(ticketType));
    }

    public static List<Ticket> findAllByExcursion(Excursion excursion) {
        return getFull(DaoFactory.getTicketDao().findAllByExcursion(excursion));
    }

    public static Ticket checkPrice(Ticket ticket) {
        if (ticket != null) {
            double price = ticket.getCruise().getShip().getPricePerSeat() + ticket.getTicketType().getPrice();
            if (ticket.getExcursions() != null) {
                for (Excursion excursion :
                        ticket.getExcursions()) {
                    price += excursion.getPrice();
                }
            }
            ticket.setPrice(price);
        }
        return ticket;
    }

    public static Ticket findById(int id) {
        return getFull(DaoFactory.getTicketDao().findById(id));
    }

    public static Ticket getEncodingTicket(Ticket ticket) {
        try {
            if (ticket.getName() != null) {
                ticket.setName(new String(ticket.getName().getBytes("ISO-8859-1"), "cp1251"));
            }
            if (ticket.getLastName() != null) {
                ticket.setLastName(new String(ticket.getLastName().getBytes("ISO-8859-1"), "cp1251"));
            }
            if (ticket.getUser() != null) {
                ticket.setUser(UserService.getUserWithEncoding(ticket.getUser()));
            }
            if (ticket.getCruise() != null){
                ticket.setCruise(CruiseService.getEncodingCruise(ticket.getCruise()));
            }
            if (ticket.getTicketType() != null){
                ticket.setTicketType(TicketTypeService.getEncodingTicketType(ticket.getTicketType()));
            }
            if (ticket.getExcursions() != null){
                ticket.setExcursions(ExcursionService.getEncodingExcursion(ticket.getExcursions()));
            }
            if (ticket.getBonuses() != null){
                ticket.setBonuses(BonusService.getEncodingBonus(ticket.getBonuses()));
            }
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e);
        }
        return ticket;
    }

    public static List<Ticket> getEncodingTicket(List<Ticket> tickets) {
        List<Ticket> encodingTicket = null;
        if (tickets != null) {
            encodingTicket = new ArrayList<>();
            for (Ticket ticket : tickets) {
                encodingTicket.add(getEncodingTicket(ticket));
            }
        }
        return encodingTicket;
    }

    public static List<Ticket> findAllLazyByUser(User user) {
        return DaoFactory.getTicketDao().findAllByUser(user);
    }

    public static List<Ticket> findAllLazyByCruise(Cruise cruise) {
        return DaoFactory.getTicketDao().findAllByCruise(cruise);
    }

    public static List<Ticket> findAllLazyByBonus(Bonus bonus) {
        return DaoFactory.getTicketDao().findAllByBonus(bonus);
    }

    public static List<Ticket> findAllLazyByTicketType(TicketType ticketType) {
        return DaoFactory.getTicketDao().findAllByTicketType(ticketType);
    }

    public static List<Ticket> findAllLazyByExcursion(Excursion excursion) {
        return DaoFactory.getTicketDao().findAllByExcursion(excursion);
    }

    public static void add(Ticket ticket) {
        DaoFactory.getTicketDao().add(checkPrice(ticket));
    }

    public static void buy(Ticket ticket) {
        DaoFactory.getTicketDao().buy(checkPrice(ticket));
    }

    public static void update(Ticket ticket) {
        DaoFactory.getTicketDao().update(ticket);
    }


    public static void delete(Ticket ticket) {
        DaoFactory.getTicketDao().delete(ticket);
    }

    public static Ticket getFull(Ticket ticket) {
        if (ticket != null) {
            ticket.setUser(UserService.findLazyByTicket(ticket));
            ticket.setTicketType(TicketTypeService.findLazyByTicket(ticket));
            ticket.setCruise(CruiseService.findLazyByTicket(ticket));
            ticket.setBonuses(BonusService.findAllLazyByTicket(ticket));
            ticket.setExcursions(ExcursionService.findAllLazyByTicket(ticket));
        }
        return ticket;
    }

    public static List<Ticket> getFull(List<Ticket> tickets) {
        if (tickets != null) {
            for (Ticket ticket : tickets) {
                ticket.setUser(UserService.findLazyByTicket(ticket));
                ticket.setCruise(CruiseService.findLazyByTicket(ticket));
                ticket.setBonuses(BonusService.findAllLazyByTicket(ticket));
                ticket.setTicketType(TicketTypeService.findLazyByTicket(ticket));
                ticket.setExcursions(ExcursionService.findAllLazyByTicket(ticket));
            }
        }
        return tickets;
    }

    public static void AddTicketHasBonuses(Ticket ticket) {
        DaoFactory.getTicketDao().addBonuses(ticket);
    }

    public static void updateTicketHasBonuses(Ticket ticket) {
        DaoFactory.getTicketDao().updateBonuses(ticket);
    }

    public static void updateTicketHasBonuses(List<Ticket> tickets) {
        for (Ticket ticket : tickets) {
            DaoFactory.getTicketDao().updateBonuses(ticket);
        }
    }

    public static void deleteTicketHasBonuses(Ticket ticket) {
        DaoFactory.getTicketDao().deleteBonuses(ticket);
    }

}
