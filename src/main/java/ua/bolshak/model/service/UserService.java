package ua.bolshak.model.service;

import ua.bolshak.model.dao.DaoFactory;
import ua.bolshak.model.entity.*;

import java.util.List;

public class UserService {

    public static List<User> findAll(){
        return getFull(DaoFactory.getUserDao().findAll());
    }

    public static List<User> findAllByRole(Role role){
        return getFull(DaoFactory.getUserDao().findAllByRole(role));
    }

    public static List<User> findAllByCruise(Cruise cruise){
        return getFull(DaoFactory.getUserDao().findAllByCruise(cruise));
    }

    public static List<User> findAllByCruiseAndRole(Cruise cruise, Role role){
        return getFull(DaoFactory.getUserDao().findAllByCruiseAndRole(cruise, role));
    }

    public static List<User> findAllTicketType(TicketType ticketType){
        return getFull(DaoFactory.getUserDao().findAllByTicketType(ticketType));
    }

    public static List<User> findAllByCruiseAndTicketType(Cruise cruise, TicketType ticketType){
        return getFull(DaoFactory.getUserDao().findAllByCruiseAndTicketType(cruise, ticketType));
    }

    public static User findById(int id){
        return getFull(DaoFactory.getUserDao().findById(id));
    }

    public static User findByTicket(Ticket ticket){
        return getFull(DaoFactory.getUserDao().findByTicket(ticket));
    }

    public static User findByLogin(String login){
        return getFull(DaoFactory.getUserDao().findByLogin(login));
    }


    public static List<User> findAllLazyByRole(Role role){
        return DaoFactory.getUserDao().findAllByRole(role);
    }

    public static List<User> findAllLazyByCruise(Cruise cruise){
        return DaoFactory.getUserDao().findAllByCruise(cruise);
    }

    public static List<User> findAllLazyByCruiseAndRole(Cruise cruise, Role role){
        return DaoFactory.getUserDao().findAllByCruiseAndRole(cruise, role);
    }

    public static List<User> findAllLazyTicketType(TicketType ticketType){
        return DaoFactory.getUserDao().findAllByTicketType(ticketType);
    }

    public static List<User> findAllLazyByCruiseAndTicketType(Cruise cruise, TicketType ticketType){
        return DaoFactory.getUserDao().findAllByCruiseAndTicketType(cruise, ticketType);
    }

    public static User findLazyByTicket(Ticket ticket){
        return DaoFactory.getUserDao().findByTicket(ticket);
    }

    public static User findLazyByLogin(String login){
        return DaoFactory.getUserDao().findByLogin(login);
    }

    public static void add(User user, Role role){
        DaoFactory.getUserDao().add(user, role);
    }

    public static void update(User user, Role role){
        DaoFactory.getUserDao().update(user, role);
    }

    public static void delete(User user){
        DaoFactory.getUserDao().delete(user);
    }

    public static User getFull(User user){
        user.setRole(RoleService.findLazyByUser(user));
        user.setTickets(TicketService.findAllLazyByUser(user));
        return user;
    }

    public static List<User> getFull(List<User> users){
        for (User user : users) {
            user.setRole(RoleService.findLazyByUser(user));
            user.setTickets(TicketService.findAllLazyByUser(user));
        }
        return users;
    }

}
