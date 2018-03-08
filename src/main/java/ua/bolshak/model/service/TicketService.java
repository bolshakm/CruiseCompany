package ua.bolshak.model.service;

import ua.bolshak.model.dao.DaoFactory;
import ua.bolshak.model.entity.*;

import java.util.List;

public class TicketService {

    public static List<Ticket> findAllLazy(){
        return DaoFactory.getTicketDao().findAll();
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

    public static Ticket findLazyById(int id){
        return DaoFactory.getTicketDao().findById(id);
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

    public static List<Ticket> getFullTickets(List<Ticket> tickets){
        for (Ticket ticket : tickets) {
            ticket.setTicketType(TicketTypeService.findLazyByTicket(ticket));
            ticket.setBonuses(BonusService.findAllLazyByTicket(ticket));
        }
        return tickets;
    }

    public static Ticket getFullTicket(Ticket ticket){
        ticket.setTicketType(TicketTypeService.findLazyByTicket(ticket));
        ticket.setBonuses(BonusService.findAllLazyByTicket(ticket));
        return ticket;
    }
}
