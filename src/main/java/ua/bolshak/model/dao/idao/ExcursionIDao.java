package ua.bolshak.model.dao.idao;

import ua.bolshak.model.entity.Excursion;
import ua.bolshak.model.entity.Port;
import ua.bolshak.model.entity.Ticket;

import java.util.List;

public interface ExcursionIDao {

    /**
     * @return All excursion from database
     */
    List<Excursion> findAll();

    /**
     * @param port with param for searching
     * @return All excursion by port from database
     */
    List<Excursion> findAllByPort(Port port);

    /**
     * @param ticket with param for searching
     * @return All excursion by ticket from database
     */
    List<Excursion> findAllByTicket(Ticket ticket);

    /**
     * @param id param for searching
     * @return Excursion ny id from database
     */
    Excursion findById(int id);

    /**
     * @param excursion with param for add
     * Add excursion in database
     */
    void add(Excursion excursion);

    /**
     * @param excursion with param for update
     * Update excursion in database
     */
    void update(Excursion excursion);

    /**
     * @param excursion with param for delete
     * Delete excursion in database
     */
    void delete(Excursion excursion);
}
