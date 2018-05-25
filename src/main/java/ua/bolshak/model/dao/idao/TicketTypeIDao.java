package ua.bolshak.model.dao.idao;

import ua.bolshak.model.entity.Ship;
import ua.bolshak.model.entity.Ticket;
import ua.bolshak.model.entity.TicketType;

import java.util.List;

public interface TicketTypeIDao {

    /**
     * @return All ticket type from database
     */
    List<TicketType> findAll();

    /**
     * @param ship with param for searching
     * @return All ticket type by ship from database
     */
    List<TicketType> findAllByShip(Ship ship);

    /**
     * @param id param for searching
     * @return Ticket type by id from database
     */
    TicketType findById(int id);

    /**
     * @param ticket with param for searching
     * @return Ticket type by ticket from database
     */
    TicketType findByTicket(Ticket ticket);

    /**
     * @param ticketType with param for add
     * Add ticket type in database
     */
    void add(TicketType ticketType);

    /**
     * @param ticketType with param for update
     * Update ticket type in database
     */
    void update(TicketType ticketType);

    /**
     * @param ticketType with param for delete
     * Delete ticket type in database
     */
    void delete(TicketType ticketType);
}
