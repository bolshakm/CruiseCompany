package ua.bolshak.model.dao.idao;

import ua.bolshak.model.entity.Role;
import ua.bolshak.model.entity.User;

import java.util.List;

public interface RoleIDao {

    List<Role> findAll();

    List<Role> findAllMutable();

    Role findById(int id);

    Role findByUser(User user);

    void add(Role role);

    void update(Role role);

    void delete(Role role);
}
