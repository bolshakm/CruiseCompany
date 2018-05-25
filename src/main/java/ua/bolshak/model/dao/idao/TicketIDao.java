package ua.bolshak.model.dao.idao;

import ua.bolshak.model.entity.*;

import java.util.List;

public interface TicketIDao {

    /**
     * @return All tickets from database
     */
    List<Ticket> findAll();

    /**
     * @param user with param for searching
     * @return All tickets by user from database
     */
    List<Ticket> findAllByUser(User user);

    /**
     * @param cruise with param for searching
     * @return All tickets by cruise from database
     */
    List<Ticket> findAllByCruise(Cruise cruise);

    /**
     * @param bonus with param for searching
     * @return All tickets by bonus from database
     */
    List<Ticket> findAllByBonus(Bonus bonus);

    /**
     * @param ticketType with param for searching
     * @return All tickets by ticket type from database
     */
    List<Ticket> findAllByTicketType(TicketType ticketType);

    /**
     * @param excursion with param for searching
     * @return All tickets by excursion from database
     */
    List<Ticket> findAllByExcursion(Excursion excursion);

    /**
     * @param id param for searching
     * @return Tickets by id from database
     */
    Ticket findById(int id);

    /**
     * @param name param for searching
     * @return Tickets by name from database
     */
    Ticket findByName(String name);

    /**
     * @param ticket with param for add
     * Add ticket in database
     */
    void add(Ticket ticket);

    /**
     * @param ticket with param for update
     * Update ticket in database
     */
    void update(Ticket ticket);

    /**
     * @param ticket with param for delete
     * Delete ticket in database
     */
    void delete(Ticket ticket);
}
