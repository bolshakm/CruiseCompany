package ua.bolshak.model.dao.idao;

import ua.bolshak.model.entity.*;

import java.util.List;

public interface ShipIDao {

    List<Ship> findAll();

    List<Ship> findAllByShipType(ShipType shipType);

    List<Ship> findAllByBonus(Bonus bonus);

    List<Ship> findAllByTicketType(TicketType ticketType);

    Ship findById(int id);

    Ship findByNumber(String number);

    Ship findByCruise(Cruise cruise);

    void add(Ship ship);

    void update(Ship ship);

    void delete(Ship ship);

}
