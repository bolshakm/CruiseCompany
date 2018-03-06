package ua.bolshak.model.service;

import ua.bolshak.model.dao.DaoFactory;
import ua.bolshak.model.entity.Role;
import ua.bolshak.model.entity.User;

import java.util.List;

public class RoleService {

    public static List<Role> findAllLazy(){
        return DaoFactory.getRoleDao().findAll();
    }

    public static Role findLazyById(int id){
        return DaoFactory.getRoleDao().findById(id);
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
}
