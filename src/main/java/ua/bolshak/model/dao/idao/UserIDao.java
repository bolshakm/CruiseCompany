package ua.bolshak.model.dao.idao;

import ua.bolshak.model.entity.*;

import java.util.List;

public interface UserIDao {

    List<User> findAll();

    List<User> findAllByRole(Role role);

    List<User> findAllByCruise(Cruise cruise);

    List<User> findAllByCruiseAndRole(Cruise cruise, Role role);

    List<User> findAllByTicketType(TicketType ticketType);

    List<User> findAllByCruiseAndTicketType(Cruise cruise,TicketType ticketType);

    User findById(int id);

    User findByTicket(Ticket ticket);

    User findByLogin(String login);

    User findByEmail(String email);

    void add(User user);

    void update(User user);

    void delete(User user);
}
