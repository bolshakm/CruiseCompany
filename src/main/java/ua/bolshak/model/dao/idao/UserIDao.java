package ua.bolshak.model.dao.idao;

import ua.bolshak.model.entity.*;

import java.util.List;

public interface UserIDao {

    /**
     * @return All users from database
     */
    List<User> findAll();

    /**
     * @param role with param for searching
     * @return All users by role from database
     */
    List<User> findAllByRole(Role role);

    /**
     * @param cruise with param for searching
     * @return All users by cruise from database
     */
    List<User> findAllByCruise(Cruise cruise);

    /**
     * @param ship with param for searching
     * @return All users by ship from database
     */
    List<User> findAllByShip(Ship ship);

    /**
     * @param ticketType with param for searching
     * @return All users by ticket type from database
     */
    List<User> findAllByTicketType(TicketType ticketType);

    /**
     * @param id  param for searching
     * @return User by id from database
     */
    User findById(int id);

    /**
     * @param ticket with param for searching
     * @return User by ticket from database
     */
    User findByTicket(Ticket ticket);

    /**
     * @param login param for searching
     * @return User by email from database
     */
    User findByLogin(String login);

    /**
     * @param email param for searching
     * @return User by email from database
     */
    User findByEmail(String email);

    /**
     * @param user with param for add
     * Add user in database
     */
    void add(User user);

    /**
     * @param user with param for update
     *  Update user in database
     */
    void update(User user);

    /**
     * @param user with param for delete
     * Delete user in database
     */
    void delete(User user);
}
