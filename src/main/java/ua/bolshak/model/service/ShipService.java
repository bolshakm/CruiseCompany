package ua.bolshak.model.service;

import org.apache.log4j.Logger;
import ua.bolshak.model.dao.DaoFactory;
import ua.bolshak.model.entity.*;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class ShipService {
    private static final Logger LOGGER = Logger.getLogger(BonusService.class);

    public static List<Ship> findAll(){
        return  getFull(DaoFactory.getShipDao().findAll());
    }

    public static Ship findByUser(User user){
        return  getFull(DaoFactory.getShipDao().findByUser(user));
    }

    public static Ship getEmptyShip(){
        return  getFull(DaoFactory.getShipDao().findById(1));
    }

    public static Ship findById(int id){
        return  getFull(DaoFactory.getShipDao().findById(id));
    }

    public static Ship getEncodingShip(Ship ship){
        try {
            if (ship.getName() != null) {
                ship.setName(new String(ship.getName().getBytes("ISO-8859-1"), "cp1251"));
            }
            if (ship.getType() != null){
                ship.setType(ShipTypeService.getEncodingShipType(ship.getType()));
            }
            if (ship.getCruises() != null){
                ship.setCruises(CruiseService.getEncodingCruise(ship.getCruises()));
            }
            if (ship.getTicketTypes() != null){
                ship.setTicketTypes(TicketTypeService.getEncodingTicketType(ship.getTicketTypes()));
            }
            if (ship.getBonuses() != null){
                ship.setBonuses(BonusService.getEncodingBonus(ship.getBonuses()));
            }
            if (ship.getUsers() != null){
                ship.setUsers(UserService.getEncodingUser(ship.getUsers()));
            }
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e);
        }
        return ship;
    }

    static List<Ship> getEncodingShip(List<Ship> ships){
        List<Ship> encodingShip = null;
        if (ships != null) {
            encodingShip = new ArrayList<>();
            for (Ship ship :
                    ships) {
                encodingShip.add(getEncodingShip(ship));
            }
        }
        return encodingShip;
    }

    static List<Ship> findAllLazyByShipType(ShipType shipType){
        return DaoFactory.getShipDao().findAllByShipType(shipType);
    }

    static Ship findLazyByUser(User user){
        return  DaoFactory.getShipDao().findByUser(user);
    }

    static List<Ship> findAllLazyByTicketType(TicketType ticketType){
        return DaoFactory.getShipDao().findAllByTicketType(ticketType);
    }

    static List<Ship> findAllLazyByBonus(Bonus bonus){
        return DaoFactory.getShipDao().findAllByBonus(bonus);
    }

    static Ship findLazyByCruise(Cruise cruise){
        return DaoFactory.getShipDao().findByCruise(cruise);
    }

    public static void add(Ship ship){
        DaoFactory.getShipDao().add(ship);
    }

    public static void update(Ship ship){
        DaoFactory.getShipDao().update(ship);
    }


    public static void delete(Ship ship){
        for (Cruise cruise : ship.getCruises()) {
            CruiseService.delete(cruise);
        }
        DaoFactory.getShipDao().delete(ship);
    }


    public static Ship getFull(Ship ship){
        if (ship != null) {
            ship.setBonuses(BonusService.findAllLazyByShip(ship));
            ship.setCruises(CruiseService.findAllLazyByShip(ship));
            ship.setType(ShipTypeService.findLazyByShip(ship));
            ship.setTicketTypes(TicketTypeService.findAllLazyByShip(ship));
            ship.setUsers(UserService.findAllLazyByShip(ship));
        }
        return ship;
    }

    public static List<Ship> getFull(List<Ship> ships){
        if (ships != null) {
            for (Ship ship :
                    ships) {
                ship.setBonuses(BonusService.findAllLazyByShip(ship));
                ship.setCruises(CruiseService.findAllLazyByShip(ship));
                ship.setUsers(UserService.findAllLazyByShip(ship));
                ship.setType(ShipTypeService.findLazyByShip(ship));
                ship.setTicketTypes(TicketTypeService.findAllLazyByShip(ship));
            }
        }
        return ships;
    }
}
