package ua.bolshak.model.dao.idao;

import ua.bolshak.model.entity.*;

import java.util.List;

public interface ShipIDao {

    /**
     * @return All ships from database
     */
    List<Ship> findAll();

    /**
     * @param shipType with param for searching
     * @return All ships by ship type from database
     */
    List<Ship> findAllByShipType(ShipType shipType);

    /**
     * @param bonus with param for searching
     * @return All ships by bonus from database
     */
    List<Ship> findAllByBonus(Bonus bonus);

    /**
     * @param ticketType with param for searching
     * @return All ships by ticket type from database
     */
    List<Ship> findAllByTicketType(TicketType ticketType);

    /**
     * @param user with param for searching
     * @return Ship by user from database
     */
    Ship findByUser(User user);

    /**
     * @param id param for searching
     * @return Ship by id from database
     */
    Ship findById(int id);

    /**
     * @param number with param for searching
     * @return Ship by number from database
     */
    Ship findByNumber(String number);

    /**
     * @param cruise with param for searching
     * @return Ship by cruise from database
     */
    Ship findByCruise(Cruise cruise);

    /**
     * @param ship with param for add
     * Add ship in database
     */
    void add(Ship ship);

    /**
     * @param ship with param for update
     * Update ship in database
     */
    void update(Ship ship);

    /**
     * @param ship with param for delete
     * Delete ship in database
     */
    void delete(Ship ship);

}
