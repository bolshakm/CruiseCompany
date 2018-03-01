package ua.bolshak.model.dao.idao;

import ua.bolshak.model.entity.Cruise;
import ua.bolshak.model.entity.CruiseStatus;

import java.util.List;

public interface CruiseStatusIDao {

    List<CruiseStatus> findAll();

    CruiseStatus findById(int id);

    CruiseStatus findByCruise(Cruise cruise);

    void add(CruiseStatus cruiseStatus);

    void update(CruiseStatus cruiseStatus);

    void delete(CruiseStatus cruiseStatus);
}
