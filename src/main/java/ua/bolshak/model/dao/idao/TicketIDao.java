package ua.bolshak.model.dao.idao;

import ua.bolshak.model.entity.*;

import java.util.List;

public interface TicketIDao {

    List<Ticket> findAll();

    List<Ticket> findAllByUser(User user);

    List<Ticket> findAllByCruise(Cruise cruise);

    List<Ticket> findAllByTicketType(TicketType ticketType);

    List<Ticket> findAllByExcursion(Excursion excursion);

    Ticket findById(int id);

    Ticket findByName(String name);

    void add(Ticket ticket);

    void update(Ticket ticket);

    void delete(Ticket ticket);
}
