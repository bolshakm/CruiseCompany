package ua.bolshak.model.dao.idao;

import ua.bolshak.model.entity.Role;
import ua.bolshak.model.entity.User;

import java.util.List;

public interface RoleIDao {

    /**
     * @return All roles from database
     */
    List<Role> findAll();

    /**
     * @return All roles mutable from database
     */
    List<Role> findAllMutable();

    /**
     * @param id  param for searching
     * @return Role by id from database
     */
    Role findById(int id);

    /**
     * @param user with param for searching
     * @return Role by name from database
     */
    Role findByUser(User user);

    /**
     * @param role with param for add
     * Add role in database
     */
    void add(Role role);

    /**
     * @param role with param for update
     * Update role in database
     */
    void update(Role role);

    /**
     * @param role with param for delete
     * Delete role in database
     */
    void delete(Role role);
}
