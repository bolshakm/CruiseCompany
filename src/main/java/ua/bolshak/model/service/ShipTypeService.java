package ua.bolshak.model.service;

import org.apache.log4j.Logger;
import ua.bolshak.model.dao.DaoFactory;
import ua.bolshak.model.entity.Ship;
import ua.bolshak.model.entity.ShipType;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class ShipTypeService {
    private static final Logger LOGGER = Logger.getLogger(BonusService.class);

    public static List<ShipType> findAll(){
        return getFull(DaoFactory.getShipTypeDao().findAll());
    }

    public static ShipType findById(int id){
        return getFull(DaoFactory.getShipTypeDao().findById(id));
    }

    static ShipType findLazyByShip(Ship ship){
        return DaoFactory.getShipTypeDao().findByShip(ship);
    }

    public static ShipType getEncodingShipType(ShipType shipType){
        try {
            if (shipType.getName() != null) {
                shipType.setName(new String(shipType.getName().getBytes("ISO-8859-1"), "cp1251"));
            }
            if (shipType.getShips() != null){
                shipType.setShips(ShipService.getEncodingShip(shipType.getShips()));
            }
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e);
        }
        return shipType;
    }


    public static void add(ShipType shipType){
        DaoFactory.getShipTypeDao().add(shipType);
    }

    public static void update(ShipType shipType){
        DaoFactory.getShipTypeDao().update(shipType);
    }

    public static void delete(ShipType shipType){
        DaoFactory.getShipTypeDao().delete(shipType);
    }

    public static ShipType getFull(ShipType shipType){
        if (shipType != null) {
            shipType.setShips(ShipService.findAllLazyByShipType(shipType));
        }
        return shipType;
    }

    public static List<ShipType> getFull(List<ShipType> shipTypes){
        if (shipTypes != null) {
            for (ShipType shipType : shipTypes) {
                shipType.setShips(ShipService.findAllLazyByShipType(shipType));
            }
        }
        return shipTypes;
    }
}
