package ua.bolshak.model.dao.idao;

import sun.nio.cs.ext.SJIS;
import ua.bolshak.model.entity.Bonus;
import ua.bolshak.model.entity.Ship;
import ua.bolshak.model.entity.Ticket;
import ua.bolshak.model.entity.TicketType;

import java.util.List;

public interface BonusIDao {

    List<Bonus> findAll();

    List<Bonus> findAllByTicket(Ticket ticket);

    List<Bonus> findAllByShip(Ship ship);

    List<Bonus> findAllByShipAndTicketType(Ship ship, TicketType ticketType);

    Bonus findById(int id);

    void addBonusesForShipByTicketType(List<Bonus> bonuses, Ship ship, TicketType ticketType);

    void deleteBonusesForShipByTicketType(Ship ship, TicketType ticketType);

    void add(Bonus bonus);

    void update(Bonus bonus);

    void delete(Bonus bonus);
}
