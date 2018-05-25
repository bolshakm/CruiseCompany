package ua.bolshak.model.dao.idao;

import ua.bolshak.model.entity.Cruise;
import ua.bolshak.model.entity.CruiseStatus;

import java.util.List;

public interface CruiseStatusIDao {

    /**
     * @return All Cruise statuses from databases
     */
    List<CruiseStatus> findAll();

    /**
     * @param id param for searching
     * @return All Cruise statuses by id from databases
     */
    CruiseStatus findById(int id);

    /**
     * @param cruise with param for searching
     * @return All Cruise statuses by cruise from databases
     */
    CruiseStatus findByCruise(Cruise cruise);

    /**
     * @param cruiseStatus with param for add
     * Add cruise status in database
     */
    void add(CruiseStatus cruiseStatus);

    /**
     * @param cruiseStatus with param for update
     * Update cruise status in database
     */
    void update(CruiseStatus cruiseStatus);

    /**
     * @param cruiseStatus with param for delete
     * Delete cruise status in database
     */
    void delete(CruiseStatus cruiseStatus);
}
