package ua.bolshak.model.service;

import ua.bolshak.model.dao.DaoFactory;
import ua.bolshak.model.entity.Ship;
import ua.bolshak.model.entity.ShipType;

import java.util.List;

public class ShipTypeDao {

    public static List<ShipType> findAllLazy(){
        return DaoFactory.getShipTypeDao().findAll();
    }

    public static ShipType findLazyById(int id){
        return DaoFactory.getShipTypeDao().findById(id);
    }

    public static ShipType findLazyByShip(Ship ship){
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
}
