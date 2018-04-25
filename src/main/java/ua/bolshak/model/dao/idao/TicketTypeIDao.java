package ua.bolshak.model.dao.idao;

import ua.bolshak.model.entity.Bonus;
import ua.bolshak.model.entity.Ship;
import ua.bolshak.model.entity.Ticket;
import ua.bolshak.model.entity.TicketType;

import java.util.List;

public interface TicketTypeIDao {

    List<TicketType> findAll();

    TicketType findById(int id);

    TicketType findByTicket(Ticket ticket);

    void add(TicketType ticketType);

    void update(TicketType ticketType);

    void delete(TicketType ticketType);
}
