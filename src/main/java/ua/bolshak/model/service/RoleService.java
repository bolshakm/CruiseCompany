package ua.bolshak.model.service;

import ua.bolshak.model.dao.DaoFactory;
import ua.bolshak.model.entity.Role;
import ua.bolshak.model.entity.User;

import java.util.List;

public class RoleService {

    public static List<Role> findAll(){
        return getFull(DaoFactory.getRoleDao().findAll());
    }

    public static Role findById(int id){
        return getFull(DaoFactory.getRoleDao().findById(id));
    }

    public static Role findByUser(User user){
        return getFull(DaoFactory.getRoleDao().findByUser(user));
    }

    public static Role findLazyByUser(User user){
        return DaoFactory.getRoleDao().findByUser(user);
    }

    public static void add(Role role){
        DaoFactory.getRoleDao().add(role);
    }

    public static void update(Role role){
        DaoFactory.getRoleDao().update(role);
    }

    public static void delete(Role role){
        DaoFactory.getRoleDao().delete(role);
    }

    public static Role getFull(Role role){
        if (role != null) {
            role.setUsers(UserService.findAllLazyByRole(role));
        }
        return role;
    }

    public static List<Role> getFull(List<Role> roles){
        if (roles != null) {
            for (Role role : roles) {
                role.setUsers(UserService.findAllLazyByRole(role));
            }
        }
        return roles;
    }
}
