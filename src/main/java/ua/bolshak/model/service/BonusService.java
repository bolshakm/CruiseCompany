package ua.bolshak.model.service;


import ua.bolshak.model.dao.DaoFactory;
import ua.bolshak.model.entity.Bonus;
import ua.bolshak.model.entity.Ship;
import ua.bolshak.model.entity.Ticket;
import ua.bolshak.model.entity.TicketType;

import java.util.ArrayList;
import java.util.List;

public class BonusService {

    public static List<Bonus> findAll(){
        return getFull(DaoFactory.getBonusDao().findAll());
    }

    public static List<Bonus> findAllByTicket(Ticket ticket){
        return getFull(DaoFactory.getBonusDao().findAllByTicket(ticket));
    }

    public static List<Bonus> getListById(String[] selectedId){
        List<Bonus> bonuses = new ArrayList<>();
        for (String id : selectedId) {
            bonuses.add(findById(Integer.parseInt(id)));
        }
        return bonuses;
    }

    public static List<Bonus> findAllByShip(Ship ship){
        return getFull(DaoFactory.getBonusDao().findAllByShip(ship));
    }

    public static Bonus findById(int id){
        return getFull(DaoFactory.getBonusDao().findById(id));
    }

    public static List<Bonus> findAllLazyByTicket(Ticket ticket){
        return DaoFactory.getBonusDao().findAllByTicket(ticket);
    }

    public static List<Bonus> findAllLazyByShip(Ship ship){
        return DaoFactory.getBonusDao().findAllByShip(ship);
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

    public static Bonus getFull(Bonus bonus){
        if (bonus !=null) {
            bonus.setShips(ShipService.findAllLazyByBonus(bonus));
            bonus.setTickets(TicketService.findAllLazyByBonus(bonus));
        }
        return bonus;
    }

    public static List<Bonus> getFull(List<Bonus> bonuses){
        if (bonuses != null) {
            for (Bonus bonus : bonuses) {
                bonus.setShips(ShipService.findAllLazyByBonus(bonus));
                bonus.setTickets(TicketService.findAllLazyByBonus(bonus));
            }
        }
        return bonuses;
    }

    public static List<Bonus> getListBonuses(String[] idBonus){
        List<Bonus> bonuses = new ArrayList<>();
        if (idBonus != null) {
            for (String id : idBonus) {
                bonuses.add(findById(Integer.parseInt(id)));
            }
        }
        return bonuses;
    }
}
