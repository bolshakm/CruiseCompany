package ua.bolshak.model.service;

import ua.bolshak.model.dao.DaoFactory;
import ua.bolshak.model.entity.*;

import java.util.List;

public class TicketService {

    public static List<Ticket> findAll(){
        return getFull(DaoFactory.getTicketDao().findAll());
    }

    public static List<Ticket> findAllByUser(User user){
        return getFull(DaoFactory.getTicketDao().findAllByUser(user));
    }

    public static List<Ticket> findAllByCruise(Cruise cruise){
        return getFull(DaoFactory.getTicketDao().findAllByCruise(cruise));
    }

    public static List<Ticket> findAllByTicketType(TicketType ticketType){
        return getFull(DaoFactory.getTicketDao().findAllByTicketType(ticketType));
    }

    public static List<Ticket> findAllByBonus(Bonus bonus){
        return getFull(DaoFactory.getTicketDao().findAllByBonus(bonus));
    }

    public static List<Ticket> findAllByExcursion(Excursion excursion){
        return getFull(DaoFactory.getTicketDao().findAllByExcursion(excursion));
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

    public static List<Ticket> findAllLazyByTicketType(TicketType ticketType){
        return DaoFactory.getTicketDao().findAllByTicketType(ticketType);
    }

    public static List<Ticket> findAllLazyByBonus(Bonus bonus){
        return DaoFactory.getTicketDao().findAllByBonus(bonus);
    }

    public static List<Ticket> findAllLazyByExcursion(Excursion excursion){
        return DaoFactory.getTicketDao().findAllByExcursion(excursion);
    }

    public static void add(Ticket ticket){
        DaoFactory.getTicketDao().add(ticket);
    }

    public static void update(Ticket ticket){
        DaoFactory.getTicketDao().update(ticket);
    }

    public static void delete(Ticket ticket){
        DaoFactory.getTicketDao().delete(ticket);
    }

    public static Ticket getFull(Ticket ticket){
        ticket.setUser(UserService.findLazyByTicket(ticket));
        ticket.setBonuses(BonusService.findAllLazyByTicket(ticket));
        ticket.setCruise(CruiseService.findLazyByTicket(ticket));
        ticket.setTicketType(TicketTypeService.findLazyByTicket(ticket));
        ticket.setExcursions(ExcursionService.findAllLazyByTicket(ticket));
        return ticket;
    }

    public static List<Ticket> getFull(List<Ticket> tickets){
        for (Ticket ticket : tickets) {
            ticket.setUser(UserService.findLazyByTicket(ticket));
            ticket.setBonuses(BonusService.findAllLazyByTicket(ticket));
            ticket.setCruise(CruiseService.findLazyByTicket(ticket));
            ticket.setTicketType(TicketTypeService.findLazyByTicket(ticket));
            ticket.setExcursions(ExcursionService.findAllLazyByTicket(ticket));
        }
        return tickets;
    }
}
