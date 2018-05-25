package ua.bolshak.model.service;

import org.apache.log4j.Logger;
import ua.bolshak.model.dao.DaoFactory;
import ua.bolshak.model.entity.*;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class ExcursionService {
    private static final Logger LOGGER = Logger.getLogger(BonusService.class);

    public static List<Excursion> findAll(){
        return getFull(DaoFactory.getExcursionDao().findAll());
    }

    public static List<Excursion> findAllByCruse(Cruise cruise){
        List<Excursion> excursions = new ArrayList<>();
        for (Port port : PortService.findAllByRoute(cruise.getRoute())) {
            excursions.addAll(( PortService.getFull(port)).getExcursions());
        }
        return excursions;
    }

    public static List<Excursion> getListById(String[] selectedId){
        List<Excursion> excursions = new ArrayList<>();
        if (selectedId != null) {
            for (String id : selectedId) {
                excursions.add(findById(Integer.parseInt(id)));
            }
        }
        return excursions;
    }

    static List<Excursion> findAllLazyByPort(Port port){
        return DaoFactory.getExcursionDao().findAllByPort(port);
    }

    static List<Excursion> findAllLazyByTicket(Ticket ticket){
        return DaoFactory.getExcursionDao().findAllByTicket(ticket);
    }

    public static Excursion getEncodingExcursion(Excursion excursion){
        try {
            if (excursion.getName() != null) {
                excursion.setName(new String(excursion.getName().getBytes("ISO-8859-1"), "cp1251"));
            }
            if (excursion.getTickets() != null){
                excursion.setTickets(TicketService.getEncodingTicket(excursion.getTickets()));
            }
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e);
        }
        return excursion;
    }

    public static List<Excursion> getEncodingExcursion(List<Excursion> excursions){
        List<Excursion> encodingExcursions = null;
        if (excursions != null){
            encodingExcursions = new ArrayList<>();
            for (Excursion excursion : excursions) {
                encodingExcursions.add(getEncodingExcursion(excursion));
            }
        }
        return encodingExcursions;
    }

    public static Excursion findById(int id){
        return getFull(DaoFactory.getExcursionDao().findById(id));
    }

    public static void add(Excursion excursion){
        DaoFactory.getExcursionDao().add(excursion);
    }

    public static void update(Excursion excursion){
        DaoFactory.getExcursionDao().update(excursion);
    }

    public static void delete(Excursion excursion){
        DaoFactory.getExcursionDao().delete(excursion);
    }

    public static Excursion getFull(Excursion excursion){
        if (excursion != null) {
            excursion.setPort(PortService.findLazyByExcursion(excursion));
            excursion.setTickets(TicketService.findAllLazyByExcursion(excursion));
        }
        return excursion;
    }

    public static boolean checkActiveTicker(List<Excursion> excursions){
        boolean result = false;
        for (Excursion excursion : excursions) {
            result = TicketService.checkActiveTicker(getFull(excursion).getTickets());
        }
        return result;
    }

    public static List<Excursion> getFull(List<Excursion> excursions){
        if (excursions != null) {
            for (Excursion excursion : excursions) {
                excursion.setPort(PortService.findLazyByExcursion(excursion));
                excursion.setTickets(TicketService.findAllLazyByExcursion(excursion));
            }
        }
        return excursions;
    }

}
