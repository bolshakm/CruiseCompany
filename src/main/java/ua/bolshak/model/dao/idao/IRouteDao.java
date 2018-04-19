package ua.bolshak.model.dao.idao;

import ua.bolshak.model.entity.Cruise;
import ua.bolshak.model.entity.Port;
import ua.bolshak.model.entity.Route;

import java.util.List;

public interface IRouteDao {

    List<Route> findAll();

    List<Route> findAllByPort(Port port);

    Route findById(int id);

    Route findByCruise(Cruise cruise);

    void add(Route route);

    void update(Route route);

    void delete(Route route);
}
