package ua.bolshak.model.dao.idao;

import ua.bolshak.model.entity.Cruise;
import ua.bolshak.model.entity.Port;
import ua.bolshak.model.entity.Route;

import java.util.List;

public interface IRouteDao {

    /**
     * @return All route from database
     */
    List<Route> findAll();

    /**
     * @param port with param for searching
     * @return All route by port from database
     */
    List<Route> findAllByPort(Port port);

    /**
     * @param id param for searching
     * @return Route by id from database
     */
    Route findById(int id);

    /**
     * @param name with param for searching
     * @return Route by name from database
     */
    Route findByName(String name);

    /**
     * @param cruise with param for searching
     * @return Route by cruise from database
     */
    Route findByCruise(Cruise cruise);

    /**
     * @param route with param for add
     * Add route in database
     */
    void add(Route route);

    /**
     * @param route with param for update
     * Update route in database
     */
    void update(Route route);

    /**
     * @param route with param for delete
     * Delete route in database
     */
    void delete(Route route);
}
