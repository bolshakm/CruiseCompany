package ua.bolshak.model.service;

import ua.bolshak.model.dao.DaoFactory;
import ua.bolshak.model.entity.Bonus;
import ua.bolshak.model.entity.Cruise;
import ua.bolshak.model.entity.Ship;
import ua.bolshak.model.entity.ShipType;

import java.util.List;

public class ShipService {

    public static List<Ship> findAllLazy(){
        return DaoFactory.getShipDao().findAll();
    }

    public static List<Ship> findAllLazyByShipType(ShipType shipType){
        return DaoFactory.getShipDao().findAllByShipType(shipType);
    }

    public static List<Ship> findAllLazyByBonus(Bonus bonus){
        return DaoFactory.getShipDao().findAllByBonus(bonus);
    }

    public static Ship findLazyById(int id){
        return DaoFactory.getShipDao().findById(id);
    }

    public static Ship findLazyByCruise(Cruise cruise){
        return DaoFactory.getShipDao().findByCruise(cruise);
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

    public static List<Ship> getFullShips(List<Ship> ships){
        for (Ship ship : ships) {
            ship.setType(ShipTypeService.findLazyByShip(ship));
            ship.setBonuses(BonusService.findAllLazyByShip(ship));
            ship.setCruises(CruiseService.findAllLazyByShip(ship));
        }
        return ships;
    }

    public static Ship getFullShip(Ship ship){
        ship.setType(ShipTypeService.findLazyByShip(ship));
        ship.setBonuses(BonusService.findAllLazyByShip(ship));
        ship.setCruises(CruiseService.findAllLazyByShip(ship));
        return ship;
    }
}
