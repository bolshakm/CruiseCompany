package ua.bolshak.model.dao.idao;

import ua.bolshak.model.entity.Ship;
import ua.bolshak.model.entity.ShipType;

import java.util.List;

public interface ShipTypeIDao {

    List<ShipType> findAll();

    ShipType findById(int id);

    ShipType findByShip(Ship ship);

    void add(ShipType shipType);

    void update(ShipType shipType);

    void delete(ShipType shipType);
}
