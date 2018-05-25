package ua.bolshak.model.dao.idao;

import ua.bolshak.model.entity.*;

import java.util.List;

public interface CruiseIDao {

    /**
     * @return All cruises from database
     */
    List<Cruise> findAll();

    /**
     * @param cruiseStatus with param for searching
     * @return All cruises by cruise status from database
     */
    List<Cruise> findAllByStatus(CruiseStatus cruiseStatus);

    /**
     * @param ship with param for searching
     * @return All cruises by ship from database
     */
    List<Cruise> findAllByShip(Ship ship);

    /**
     * @param route with param for searching
     * @return All cruises by route from database
     */
    List<Cruise> findAllByRoute(Route route);

    /**
     * @param ticket with param for searching
     * @return Cruise by ticket from database
     */
    Cruise findByTicket(Ticket ticket);

    /**
     * @param id with param for searching
     * @return Cruise by id from database
     */
    Cruise findById(int id);

    /**
     * @param cruise with param for add
     * Add cruise in database
     */
    void add(Cruise cruise);

    /**
     * @param cruise with param for update
     * Update cruise in database
     */
    void update(Cruise cruise);

    /**
     * @param cruise with param for delete
     * Delete cruise in database
     */
    void delete(Cruise cruise);
}
