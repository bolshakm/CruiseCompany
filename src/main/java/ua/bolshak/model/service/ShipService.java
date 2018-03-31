package ua.bolshak.model.service;

import ua.bolshak.model.dao.DaoFactory;
import ua.bolshak.model.entity.Bonus;
import ua.bolshak.model.entity.Cruise;
import ua.bolshak.model.entity.Ship;
import ua.bolshak.model.entity.ShipType;

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

    public static void addBonusesForShip(Ship ship, String [] bonusId){
        List<Bonus> bonuses = new ArrayList<>();
        for (String id : bonusId){
            bonuses.add(BonusService.findById(Integer.parseInt(id)));
        }
        DaoFactory.getShipDao().addBonusesForShip(ship, bonuses);
    }

    public static void add(Ship ship, ShipType shipType){
        DaoFactory.getShipDao().add(ship, shipType);
    }

    public static void update(Ship ship, ShipType shipType){
        DaoFactory.getShipDao().update(ship, shipType);
    }

    public static void updateWithBonuses(Ship ship, ShipType shipType, String [] bonusId){
        List<Bonus> bonuses = new ArrayList<>();
        for (String id : bonusId){
            bonuses.add(BonusService.findById(Integer.parseInt(id)));
        }
        DaoFactory.getShipDao().update(ship, shipType);
        DaoFactory.getShipDao().updateBonuses(ship, bonuses);
    }

    public static void delete(Ship ship){
        DaoFactory.getShipDao().delete(ship);
    }


    public static Ship getFull(Ship ship){
        ship.setBonuses(BonusService.findAllLazyByShip(ship));
        ship.setCruises(CruiseService.findAllLazyByShip(ship));
        ship.setType(ShipTypeService.findLazyByShip(ship));
        return ship;
    }

    public static List<Ship> getFull(List<Ship> ships){
        for (Ship ship :
                ships) {
            ship.setBonuses(BonusService.findAllLazyByShip(ship));
            ship.setCruises(CruiseService.findAllLazyByShip(ship));
            ship.setType(ShipTypeService.findLazyByShip(ship));
        }
        return ships;
    }
}
