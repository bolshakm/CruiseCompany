package ua.bolshak.model.service;

import ua.bolshak.model.dao.DaoFactory;
import ua.bolshak.model.entity.Ticket;
import ua.bolshak.model.entity.TicketType;

import java.util.List;

public class TicketTypeService {

    public static List<TicketType> findAllLazy(){
        return DaoFactory.getTicketTypeDao().findAll();
    }

    public static TicketType findLazyById(int id){
        return DaoFactory.getTicketTypeDao().findById(id);
    }

    public static TicketType findLazyByTicket(Ticket ticket){
        return DaoFactory.getTicketTypeDao().findByTicket(ticket);
    }

    public static void add(TicketType ticketType){
        DaoFactory.getTicketTypeDao().add(ticketType);
    }

    public static void update(TicketType ticketType){
        DaoFactory.getTicketTypeDao().update(ticketType);
    }

    public static void delete(TicketType ticketType){
        DaoFactory.getTicketTypeDao().delete(ticketType);
    }

    public static List<TicketType> getFullTicketType(List<TicketType> ticketTypes){
        for (TicketType ticketType : ticketTypes) {
            ticketType.setTickets(TicketService.findAllLazyByTicketType(ticketType));
        }
        return ticketTypes;
    }

    public static TicketType getFullTicketType(TicketType ticketType){
        ticketType.setTickets(TicketService.findAllLazyByTicketType(ticketType));
        return ticketType;
    }
}
