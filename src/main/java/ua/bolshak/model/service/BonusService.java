package ua.bolshak.model.service;


import ua.bolshak.model.dao.DaoFactory;
import ua.bolshak.model.entity.Bonus;
import ua.bolshak.model.entity.Ship;
import ua.bolshak.model.entity.Ticket;

import java.util.List;

public class BonusService {

    public static List<Bonus> findAll(){
        return DaoFactory.getBonusDao().findAll();
    }

    public static List<Bonus> findAllByTicket(Ticket ticket){
        return DaoFactory.getBonusDao().findAllByTicket(ticket);
    }

    public static List<Bonus> findAllByShip(Ship ship){
        return DaoFactory.getBonusDao().findAllShip(ship);
    }

    public static Bonus findById(int id){
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


}
