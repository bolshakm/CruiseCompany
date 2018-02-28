package ua.bolshak.model.dao.idao;

import ua.bolshak.model.entity.*;

import java.util.List;

public interface CruiseIDao {

    List<Cruise> findAll();

    List<Cruise> findAllByPort(Port port);

    List<Cruise> findAllByStatus(CruiseStatus cruiseStatus);

    List<Cruise> findAllByShip(Ship ship);

    List<Cruise> findAllByUser(User user);

    Cruise findByTicket(Ticket ticket);

    Cruise findById(int id);

    void add(Cruise cruise);

    void update(Cruise cruise);

    void delete(Cruise cruise);
}
