package ua.bolshak.model.dao.idao;

import ua.bolshak.model.entity.Cruise;
import ua.bolshak.model.entity.Excursion;
import ua.bolshak.model.entity.Port;

import java.util.List;

public interface PordIDao {

    List<Port> findAll();

    List<Port> findAllByCruise(Cruise cruise);

    Port findById(int id);

    Port findByExcursion(Excursion excursion);

    void add(Port port);

    void update(Port port);

    void delete(Port port);
}
