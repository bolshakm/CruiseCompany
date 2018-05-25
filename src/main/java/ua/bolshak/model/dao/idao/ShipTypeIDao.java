package ua.bolshak.model.dao.idao;

import ua.bolshak.model.entity.Ship;
import ua.bolshak.model.entity.ShipType;

import java.util.List;

public interface ShipTypeIDao {

    /**
     * @return All ship type from database
     */
    List<ShipType> findAll();

    /**
     * @param id param for searching
     * @return Ship type by id from database
     */
    ShipType findById(int id);

    /**
     * @param ship with param for searching
     * @return Ship type by ship from database
     */
    ShipType findByShip(Ship ship);

    /**
     * @param shipType with param for add
     * Add shit type in database
     */
    void add(ShipType shipType);

    /**
     * @param shipType with param for update
     * Update shit type in database
     */
    void update(ShipType shipType);

    /**
     * @param shipType with param for delete
     * Delete shit type in database
     */
    void delete(ShipType shipType);
}
