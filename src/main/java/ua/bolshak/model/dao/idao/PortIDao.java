package ua.bolshak.model.dao.idao;

import ua.bolshak.model.entity.Cruise;
import ua.bolshak.model.entity.Excursion;
import ua.bolshak.model.entity.Port;
import ua.bolshak.model.entity.Route;

import java.util.List;

public interface PortIDao {

    List<Port> findAll();

    List<Port> findAllByRoute(Route route);

    Port findById(int id);

    Port findByExcursion(Excursion excursion);

    void add(Port port);

    void update(Port port);

    void delete(Port port);
}
