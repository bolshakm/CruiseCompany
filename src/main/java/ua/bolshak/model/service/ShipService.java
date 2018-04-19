package ua.bolshak.model.service;

import ua.bolshak.model.dao.DaoFactory;
import ua.bolshak.model.entity.*;

import java.util.ArrayList;
import java.util.List;

public class ShipService {

    public static List<Ship> findAll(){
        return  getFull(DaoFactory.getShipDao().findAll());
    }

    public static List<Ship> findAllByShipType(ShipType shipType){
        return getFull(DaoFactory.getShipDao().findAllByShipType(shipType));
    }

    public static List<Ship> findAllByBonus(Bonus bonus){
        return  getFull(DaoFactory.getShipDao().findAllByBonus(bonus));
    }

    public static List<Ship> findAllByTicketType(TicketType ticketType){
        return  getFull(DaoFactory.getShipDao().findAllByTicketTypes(ticketType));
    }

    public static Ship findById(int id){
        return  getFull(DaoFactory.getShipDao().findById(id));
    }

    public static Ship findByNumber(String number){
        return  getFull(DaoFactory.getShipDao().findByNumber(number));
    }

    public static Ship findByCruise(Cruise cruise){
        return  getFull(DaoFactory.getShipDao().findByCruise(cruise));
    }

    public static List<Ship> findAllLazyByShipType(ShipType shipType){
        return DaoFactory.getShipDao().findAllByShipType(shipType);
    }

    public static List<Ship> findAllLazyByBonus(Bonus bonus){
        return DaoFactory.getShipDao().findAllByBonus(bonus);
    }

    public static Ship findLazyByCruise(Cruise cruise){
        return DaoFactory.getShipDao().findByCruise(cruise);
    }

    public static List<Ship> findAllLazyByTicketType(TicketType ticketType){
        return  DaoFactory.getShipDao().findAllByTicketTypes(ticketType);
    }

    public static void add(Ship ship){
        DaoFactory.getShipDao().add(ship);
    }

    public static void update(Ship ship){
        DaoFactory.getShipDao().update(ship);
    }


    public static void delete(Ship ship){
        DaoFactory.getShipDao().delete(ship);
    }


    public static Ship getFull(Ship ship){
        if (ship != null) {
            ship.setBonuses(BonusService.findAllLazyByShip(ship));
            ship.setCruises(CruiseService.findAllLazyByShip(ship));
            ship.setType(ShipTypeService.findLazyByShip(ship));
            ship.setTicketTypes(TicketTypeService.findAllLazyByShip(ship));
        }
        return ship;
    }

    public static List<Ship> getFull(List<Ship> ships){
        if (ships != null) {
            for (Ship ship :
                    ships) {
                ship.setBonuses(BonusService.findAllLazyByShip(ship));
                ship.setCruises(CruiseService.findAllLazyByShip(ship));
                ship.setType(ShipTypeService.findLazyByShip(ship));
                ship.setTicketTypes(TicketTypeService.findAllLazyByShip(ship));
            }
        }
        return ships;
    }
}
