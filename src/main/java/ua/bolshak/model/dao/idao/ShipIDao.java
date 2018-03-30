package ua.bolshak.model.dao.idao;

import ua.bolshak.model.entity.Bonus;
import ua.bolshak.model.entity.Cruise;
import ua.bolshak.model.entity.Ship;
import ua.bolshak.model.entity.ShipType;

import java.util.List;

public interface ShipIDao {

    List<Ship> findAll();

    List<Ship> findAllByShipType(ShipType shipType);

    List<Ship> findAllByBonus(Bonus bonus);

    Ship findById(int id);

    Ship findByNumber(String number);

    Ship findByCruise(Cruise cruise);

    void add(Ship ship, ShipType shipType);

    void addBonusesForShip(Ship ship, List<Bonus> bonuses);

    void update(Ship ship, ShipType shipType);

    void updateBonuses(Ship ship, List<Bonus> bonuses);

    void delete(Ship ship);

    void deleteBonuses(Ship ship);
}
