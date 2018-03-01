package ua.bolshak.model.dao.idao;

import ua.bolshak.model.entity.Excursion;
import ua.bolshak.model.entity.Port;
import ua.bolshak.model.entity.Ticket;

import java.util.List;

public interface ExcursionIDao {

    List<Excursion> findAll();

    List<Excursion> findAllByPort(Port port);

    List<Excursion> findAllByTicket(Ticket ticket);

    Excursion findById(int id);

    void add(Excursion excursion);

    void update(Excursion excursion);

    void delete(Excursion excursion);
}
