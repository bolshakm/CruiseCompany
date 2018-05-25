package ua.bolshak.model.service;

import org.apache.log4j.Logger;
import ua.bolshak.model.dao.DaoFactory;
import ua.bolshak.model.entity.*;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private static final Logger LOGGER = Logger.getLogger(UserService.class);

    public static List<User> findAll(){
        return getFull(DaoFactory.getUserDao().findAll());
    }

    public static List<User> findAllByRole(Role role){
        return getFull(DaoFactory.getUserDao().findAllByRole(role));
    }


    public static List<User> findAllByShip(Ship ship){
        return getFull(DaoFactory.getUserDao().findAllByShip(ship));
    }


    static List<User> getEncodingUser(List<User> users){
        List<User> encodingUsers = null;
        if (users != null) {
            encodingUsers = new ArrayList<>();
            for (User user : users) {
                encodingUsers.add(getUserWithEncoding(user));
            }
        }
        return encodingUsers;
    }

    public static User findById(int id){
        return getFull(DaoFactory.getUserDao().findById(id));
    }

    public static User findByLogin(String login){
        return getFull(DaoFactory.getUserDao().findByLogin(login));
    }

    public static User getUserWithEncoding(User user ){
        try {
            if (user.getLogin() != null) {
                user.setLogin(new String(user.getLogin().getBytes("ISO-8859-1"), "cp1251"));
            }
            if (user.getName() != null){
                user.setName(new String(user.getName().getBytes("ISO-8859-1"), "cp1251"));
            }
            if (user.getLastName() != null){
                user.setLastName(new String(user.getLastName().getBytes("ISO-8859-1"), "cp1251"));
            }
            if (user.getShip()!=null){
                user.setShip(ShipService.getEncodingShip(user.getShip()));
            }
            if (user.getRole() != null){
                user.setRole(RoleService.getEncodingRole(user.getRole()));
            }
            if (user.getTickets() != null){
                user.setTickets(TicketService.getEncodingTicket(user.getTickets()));
            }
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e);
        }
        return user;
    }

    public static User findByEmail(String email){
        return getFull(DaoFactory.getUserDao().findByEmail(email));
    }

    static List<User> findAllLazyByRole(Role role){
        return DaoFactory.getUserDao().findAllByRole(role);
    }

    static List<User> findAllLazyByCruise(Cruise cruise){
        return DaoFactory.getUserDao().findAllByCruise(cruise);
    }

    static List<User> findAllLazyByShip(Ship ship){
        return DaoFactory.getUserDao().findAllByShip(ship);
    }


    static List<User> findAllLazyTicketType(TicketType ticketType){
        return DaoFactory.getUserDao().findAllByTicketType(ticketType);
    }

    static User findLazyByTicket(Ticket ticket){
        return DaoFactory.getUserDao().findByTicket(ticket);
    }


    public static void transferMoneyFromAdministrator(User user, double money){
        DaoFactory.getUserDao().transferMoneyFromUser(user, money);
    }

    public static void add(User user){
        DaoFactory.getUserDao().add(user);
    }

    public static void update(User user){
        DaoFactory.getUserDao().update(user);
    }

    public static void delete(User user){
        DaoFactory.getUserDao().delete(user);
    }

    public static User getFull(User user){
        if (user != null) {
            user.setRole(RoleService.findLazyByUser(user));
            user.setTickets(TicketService.findAllLazyByUser(user));
            user.setShip(ShipService.findLazyByUser(user));
        }
        return user;
    }

    public static List<User> getFull(List<User> users){
        if (users != null) {
            for (User user : users) {
                user.setRole(RoleService.findLazyByUser(user));
                user.setTickets(TicketService.findAllLazyByUser(user));
                user.setShip(ShipService.findLazyByUser(user));
            }
        }
        return users;
    }
}
