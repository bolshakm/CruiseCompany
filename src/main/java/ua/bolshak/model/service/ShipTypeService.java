package ua.bolshak.model.service;

import ua.bolshak.model.dao.DaoFactory;
import ua.bolshak.model.entity.Ship;
import ua.bolshak.model.entity.ShipType;

import java.util.List;

public class ShipTypeService {

    public static List<ShipType> findAll(){
        return DaoFactory.getShipTypeDao().findAll();
    }

    public static ShipType findById(int id){
        return DaoFactory.getShipTypeDao().findById(id);
    }

    public static ShipType findByShip(Ship ship){
        return DaoFactory.getShipTypeDao().findByShip(ship);
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

    public static List<ShipType> getFullShipTypes(List<ShipType> shipTypes){
        for (ShipType shipType : shipTypes) {
            shipType.setShips(ShipService.findAllByShipType(shipType));
        }
        return shipTypes;
    }

    public static ShipType getFullShipType(ShipType shipType){
        shipType.setShips(ShipService.findAllByShipType(shipType));
        return shipType;
    }
}
