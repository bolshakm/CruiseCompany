package ua.bolshak.model.service;

import ua.bolshak.model.dao.DaoFactory;
import ua.bolshak.model.entity.Bonus;
import ua.bolshak.model.entity.Ship;
import ua.bolshak.model.entity.Ticket;
import ua.bolshak.model.entity.TicketType;

import java.util.List;

public class TicketTypeService {

    public static List<TicketType> findAll() {
        return getFull(DaoFactory.getTicketTypeDao().findAll());
    }

    public static List<TicketType> findAllByShip(Ship ship){
        return getFull(DaoFactory.getTicketTypeDao().findAllByShip(ship));
    }

    public static TicketType findById(int id) {
        return getFull(DaoFactory.getTicketTypeDao().findById(id));
    }

    public static TicketType findByTicket(Ticket ticket) {
        return getFull(DaoFactory.getTicketTypeDao().findByTicket(ticket));
    }

    public static TicketType findLazyByTicket(Ticket ticket) {
        return DaoFactory.getTicketTypeDao().findByTicket(ticket);
    }

    public static List<TicketType> findAllLazyByShip(Ship ship){
        return DaoFactory.getTicketTypeDao().findAllByShip(ship);
    }

    public static void add(TicketType ticketType) {
        DaoFactory.getTicketTypeDao().add(ticketType);
    }

    public static void update(TicketType ticketType) {
        DaoFactory.getTicketTypeDao().update(ticketType);
    }

    public static void delete(TicketType ticketType) {
        DaoFactory.getTicketTypeDao().delete(ticketType);
    }

    public static TicketType getFull(TicketType ticketType){
        if (ticketType != null) {
            ticketType.setTickets(TicketService.findAllLazyByTicketType(ticketType));
            ticketType.setUsers(UserService.findAllLazyTicketType(ticketType));
            ticketType.setShips(ShipService.findAllLazyByTicketType(ticketType));
        }
        return ticketType;
    }

    public static List<TicketType> getFull(List<TicketType> ticketTypes) {
        if (ticketTypes != null) {
            for (TicketType ticketType : ticketTypes) {
                ticketType.setTickets(TicketService.findAllLazyByTicketType(ticketType));
                ticketType.setUsers(UserService.findAllLazyTicketType(ticketType));
                ticketType.setShips(ShipService.findAllLazyByTicketType(ticketType));
            }
        }
        return ticketTypes;
    }
}
