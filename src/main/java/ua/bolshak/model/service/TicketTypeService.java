package ua.bolshak.model.service;

import org.apache.log4j.Logger;
import ua.bolshak.model.dao.DaoFactory;
import ua.bolshak.model.entity.Ship;
import ua.bolshak.model.entity.Ticket;
import ua.bolshak.model.entity.TicketType;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class TicketTypeService {
    private static final Logger LOGGER = Logger.getLogger(BonusService.class);

    public static List<TicketType> findAll() {
        return getFull(DaoFactory.getTicketTypeDao().findAll());
    }

    public static List<TicketType> findAllByShip(Ship ship){
        return getFull(DaoFactory.getTicketTypeDao().findAllByShip(ship));
    }

    public static TicketType findById(int id) {
        return getFull(DaoFactory.getTicketTypeDao().findById(id));
    }

    static TicketType findLazyByTicket(Ticket ticket) {
        return DaoFactory.getTicketTypeDao().findByTicket(ticket);
    }

    public static TicketType getEncodingTicketType(TicketType ticketType){
        try {
            if (ticketType.getName() != null) {
                ticketType.setName(new String(ticketType.getName().getBytes("ISO-8859-1"), "cp1251"));
            }
            if (ticketType.getTickets() != null){
                ticketType.setTickets(TicketService.getEncodingTicket(ticketType.getTickets()));
            }
            if (ticketType.getUsers() != null){
                ticketType.setUsers(UserService.getEncodingUser(ticketType.getUsers()));
            }
            if (ticketType.getShips() !=null){
                ticketType.setShips(ShipService.getEncodingShip(ticketType.getShips()));
            }

        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e);
        }
        return ticketType;
    }

    static List<TicketType> getEncodingTicketType(List<TicketType> ticketTypes){
        List<TicketType> encodingTicketType = null;
        if (ticketTypes != null){
            encodingTicketType = new ArrayList<>();
            for (TicketType ticketType : ticketTypes) {
                encodingTicketType.add(getEncodingTicketType(ticketType));
            }
        }
        return encodingTicketType;
    }

    static List<TicketType> findAllLazyByShip(Ship ship){
        return DaoFactory.getTicketTypeDao().findAllByShip(ship);
    }

    public static List<TicketType> getListTicketTypes(String[] idTicketTypes){
        List<TicketType> ticketTypes = new ArrayList<>();
        if (idTicketTypes != null){
            for (String id : idTicketTypes) {
                ticketTypes.add(findById(Integer.parseInt(id)));
            }
        }
        return ticketTypes;
    }

    public static List<TicketType> getListWithStandardTicketTypes(){
        List<TicketType> ticketTypes = new ArrayList<>();
        ticketTypes.add(findById(1));
        return ticketTypes;
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
