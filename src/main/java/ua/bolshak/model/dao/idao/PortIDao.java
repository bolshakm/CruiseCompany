package ua.bolshak.model.dao.idao;

import ua.bolshak.model.entity.Excursion;
import ua.bolshak.model.entity.Port;
import ua.bolshak.model.entity.Route;

import java.util.List;

public interface PortIDao {

    /**
     * @return All ports from database
     */
    List<Port> findAll();

    /**
     * @param route with param for searching
     * @return All ports by route from database
     */
    List<Port> findAllByRoute(Route route);

    /**
     * @param id param for searching
     * @return Ports by id from database
     */
    Port findById(int id);

    /**
     * @param excursion with param for searching
     * @return Ports by excursion from database
     */
    Port findByExcursion(Excursion excursion);

    /**
     * @param port with param for add
     * Add port in database
     */
    void add(Port port);

    /**
     * @param port with param for update
     * Update port in database
     */
    void update(Port port);

    /**
     * @param port with param for delete
     * Delete port in database
     */
    void delete(Port port);
}
