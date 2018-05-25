package ua.bolshak.model.dao.idao;

import ua.bolshak.model.entity.Bonus;
import ua.bolshak.model.entity.Ship;
import ua.bolshak.model.entity.Ticket;
import ua.bolshak.model.entity.TicketType;

import java.util.List;

public interface BonusIDao {

    /**
     * @return All bonuses from database
     */
    List<Bonus> findAll();

    /**
     * @param ticket with param for searching
     * @return All bonuses by ticket from database
     */
    List<Bonus> findAllByTicket(Ticket ticket);

    /**
     * @param ship with param for searching
     * @return All bonuses by ship from database
     */
    List<Bonus> findAllByShip(Ship ship);

    /**
     * @param ship with param for searching
     * @param ticketType with param for searching
     * @return All bonuses by ship and ticket type from database
     */
    List<Bonus> findAllByShipAndTicketType(Ship ship, TicketType ticketType);

    /**
     * @param id param for searching
     * @return Bonus by ticket id from database
     */
    Bonus findById(int id);

    /**
     * @param bonuses with param for add
     * @param ship with param for add
     * @param ticketType
     * Add bonuses for ship by ticket type. Save in database
     */
    void addBonusesForShipByTicketType(List<Bonus> bonuses, Ship ship, TicketType ticketType);

    /**
     * @param ship with param for delete
     * @param ticketType with param for delete
     * Delete bonuses for ship by ticket type. Save in database
     */
    void deleteBonusesForShipByTicketType(Ship ship, TicketType ticketType);

    /**
     * @param bonus with param for add
     * Add bonuses in database
     */
    void add(Bonus bonus);

    /**
     * @param bonus with param for update
     * Update bonus in database
     */
    void update(Bonus bonus);

    /**
     * @param bonus with param for delete
     * Delete bonus in database
     */
    void delete(Bonus bonus);
}
