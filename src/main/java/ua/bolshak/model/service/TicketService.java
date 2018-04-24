package ua.bolshak.model.service;

import ua.bolshak.model.dao.DaoFactory;
import ua.bolshak.model.entity.*;

import java.util.ArrayList;
import java.util.List;

public class TicketService {

    public static List<Ticket> findAll(){
        return getFull(DaoFactory.getTicketDao().findAll());
    }

    public static List<Ticket> findAllWithFullCruise(){
        List <Ticket> tickets = new ArrayList<>();
        for (Ticket ticket : getFull(DaoFactory.getTicketDao().findAll())) {
            ticket.setCruise(CruiseService.getFull(ticket.getCruise()));
            tickets.add(ticket);
        }
        return tickets;
    }

    public static List<Ticket> findAllByUser(User user){
        return getFull(DaoFactory.getTicketDao().findAllByUser(user));
    }

    public static List<Ticket> findAllByBonus(Bonus bonus) {
        return getFull(DaoFactory.getTicketDao().findAllByBonus(bonus));
    }

    public static List<Ticket> findAllByCruise(Cruise cruise){
        return getFull(DaoFactory.getTicketDao().findAllByCruise(cruise));
    }

    public static List<Ticket> findAllByTicketType(TicketType ticketType){
        return getFull(DaoFactory.getTicketDao().findAllByTicketType(ticketType));
    }

    public static List<Ticket> findAllByExcursion(Excursion excursion){
        return getFull(DaoFactory.getTicketDao().findAllByExcursion(excursion));
    }

    public static Ticket checkPrice(Ticket ticket){
        if (ticket != null){
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

    public static Ticket findById(int id){
        return getFull(DaoFactory.getTicketDao().findById(id));
    }

    public static List<Ticket> findAllLazyByUser(User user){
        return DaoFactory.getTicketDao().findAllByUser(user);
    }

    public static List<Ticket> findAllLazyByCruise(Cruise cruise){
        return DaoFactory.getTicketDao().findAllByCruise(cruise);
    }

    public static List<Ticket> findAllLazyByBonus(Bonus bonus) {
        return DaoFactory.getTicketDao().findAllByBonus(bonus);
           }

    public static List<Ticket> findAllLazyByTicketType(TicketType ticketType){
        return DaoFactory.getTicketDao().findAllByTicketType(ticketType);
    }

    public static List<Ticket> findAllLazyByExcursion(Excursion excursion){
        return DaoFactory.getTicketDao().findAllByExcursion(excursion);
    }

    public static void add(Ticket ticket){
        DaoFactory.getTicketDao().add(checkPrice(ticket));
    }

    public static void buy(Ticket ticket){
        DaoFactory.getTicketDao().buy(checkPrice(ticket));
    }

    public static void update(Ticket ticket){
        DaoFactory.getTicketDao().update(checkPrice(ticket));
    }

    public static void delete(Ticket ticket){
        DaoFactory.getTicketDao().delete(ticket);
    }

    public static Ticket getFull(Ticket ticket){
        if (ticket != null) {
            ticket.setUser(UserService.findLazyByTicket(ticket));
            ticket.setTicketType(TicketTypeService.findLazyByTicket(ticket));
            ticket.setCruise(CruiseService.findLazyByTicket(ticket));
            ticket.setBonuses(BonusService.findAllLazyByTicket(ticket));
            ticket.setExcursions(ExcursionService.findAllLazyByTicket(ticket));
        }
        return ticket;
    }

    public static List<Ticket> getFull(List<Ticket> tickets){
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
}
