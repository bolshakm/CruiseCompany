package ua.bolshak.model.service;


import ua.bolshak.model.dao.DaoFactory;
import ua.bolshak.model.entity.Bonus;
import ua.bolshak.model.entity.Ship;
import ua.bolshak.model.entity.Ticket;

import java.util.List;

public class BonusService {

    public static List<Bonus> findAllLazy(){
        return DaoFactory.getBonusDao().findAll();
    }

    public static List<Bonus> findAllLazyByTicket(Ticket ticket){
        return DaoFactory.getBonusDao().findAllByTicket(ticket);
    }

    public static List<Bonus> findAllLazyByShip(Ship ship){
        return DaoFactory.getBonusDao().findAllShip(ship);
    }

    public static Bonus findLazyById(int id){
        return DaoFactory.getBonusDao().findById(id);
    }

    public static void add(Bonus bonus){
        DaoFactory.getBonusDao().add(bonus);
    }

    public static void update(Bonus bonus){
        DaoFactory.getBonusDao().update(bonus);
    }

    public static void delete(Bonus bonus){
        DaoFactory.getBonusDao().delete(bonus);
    }

    public static List<Bonus> getFullBonuses(List<Bonus> bonuses){
        for (Bonus bonus :
                bonuses) {
            bonus.setTickets(TicketService.findAllLazyByBonus(bonus));
            bonus.setShips(ShipService.findAllLazyByBonus(bonus));
        }
        return bonuses;
    }

    public static Bonus getFullBonus(Bonus bonus){
        bonus.setTickets(TicketService.findAllLazyByBonus(bonus));
        bonus.setShips(ShipService.findAllLazyByBonus(bonus));
        return bonus;
    }
}
