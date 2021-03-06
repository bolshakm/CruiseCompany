package ua.bolshak.model.service;


import org.apache.log4j.Logger;
import ua.bolshak.model.dao.DaoFactory;
import ua.bolshak.model.entity.*;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class BonusService {
    private static final Logger LOGGER = Logger.getLogger(BonusService.class);

    public static List<Bonus> findAll() {
        return getFull(DaoFactory.getBonusDao().findAll());
    }

    public static List<Bonus> getListWithEmptyBonus() {
        List<Bonus> bonuses = new ArrayList<>();
        bonuses.add(findById(1));
        return bonuses;
    }

    public static List<Bonus> findAllByShip(Ship ship) {
        return getFull(DaoFactory.getBonusDao().findAllByShip(ship));
    }


    public static List<Bonus> findAllByShipAndTicketType(Ship ship, TicketType ticketType) {
        return getFull(DaoFactory.getBonusDao().findAllByShipAndTicketType(ship, ticketType));
    }

    public static Bonus findById(int id) {
        return getFull(DaoFactory.getBonusDao().findById(id));
    }

    public static Bonus getEncodingBonus(Bonus bonus) {
        try {
            if (bonus.getName() != null) {
                bonus.setName(new String(bonus.getName().getBytes("ISO-8859-1"), "cp1251"));
            }
            if (bonus.getShips() != null){
                for (Ship ship : bonus.getShips()) {
                    ShipService.getEncodingShip(ship);
                }
            }
            if (bonus.getTickets() != null){
                bonus.setTickets(TicketService.getEncodingTicket(bonus.getTickets()));
            }
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e);
        }
        return bonus;
    }

    public static List<Bonus> getEncodingBonus(List<Bonus> bonuses){
        List<Bonus> encodingBonuses = null;
        if (bonuses != null) {
            encodingBonuses = new ArrayList<>();
            for (Bonus bonus :
                    bonuses) {
                encodingBonuses.add(getEncodingBonus(bonus));
            }
        }
        return encodingBonuses;
    }

    static List<Bonus> findAllLazyByTicket(Ticket ticket) {
        return DaoFactory.getBonusDao().findAllByTicket(ticket);
    }

    static List<Bonus> findAllLazyByShip(Ship ship) {
        return DaoFactory.getBonusDao().findAllByShip(ship);
    }

    public static void editBonusesForShipByTicketType(List<Bonus> bonuses, TicketType ticketType, Ship ship) {
        DaoFactory.getBonusDao().editBonusesForShipByTicketType(bonuses, ship, ticketType);
    }

    public static void editBonusesForTicketByShipAndTicketType(List<Bonus> bonuses, TicketType ticketType, Ship ship) {
        for (Cruise cruise : CruiseService.getFull(ship.getCruises())) {
            for (Ticket ticket : TicketService.getFull(cruise.getTickets())) {
                if (ticketType.equals(ticket.getTicketType())) {
                    ticket.setBonuses(bonuses);
                    TicketService.updateTicketHasBonuses(ticket);
                }
            }
        }
    }

    public static void add(Bonus bonus) {
        DaoFactory.getBonusDao().add(bonus);
    }

    public static void update(Bonus bonus) {
        DaoFactory.getBonusDao().update(bonus);
    }

    public static void delete(Bonus bonus) {
        DaoFactory.getBonusDao().delete(bonus);
    }

    public static Bonus getFull(Bonus bonus) {
        if (bonus != null) {
            bonus.setShips(ShipService.findAllLazyByBonus(bonus));
            bonus.setTickets(TicketService.findAllLazyByBonus(bonus));
        }
        return bonus;
    }

    public static List<Bonus> getFull(List<Bonus> bonuses) {
        if (bonuses != null) {
            for (Bonus bonus : bonuses) {
                bonus.setShips(ShipService.findAllLazyByBonus(bonus));
                bonus.setTickets(TicketService.findAllLazyByBonus(bonus));
            }
        }
        return bonuses;
    }

    public static List<Bonus> getListBonuses(String[] idBonus) {
        List<Bonus> bonuses = new ArrayList<>();
        if (idBonus != null) {
            for (String id : idBonus) {
                bonuses.add(findById(Integer.parseInt(id)));
            }
        }
        return bonuses;
    }
}
