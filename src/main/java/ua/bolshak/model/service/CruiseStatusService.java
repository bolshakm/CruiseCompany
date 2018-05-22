package ua.bolshak.model.service;

import org.apache.log4j.Logger;
import ua.bolshak.model.dao.DaoFactory;
import ua.bolshak.model.entity.Cruise;
import ua.bolshak.model.entity.CruiseStatus;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class CruiseStatusService {
    private static final Logger LOGGER = Logger.getLogger(CruiseStatus.class);

    public static List<CruiseStatus> findAll(){
        return getFull(DaoFactory.getCruiseStatusDao().findAll());
    }

    public static CruiseStatus findById(int id){
        return getFull(DaoFactory.getCruiseStatusDao().findById(id));
    }

    public static CruiseStatus findByCruise(Cruise cruise){
        return getFull(DaoFactory.getCruiseStatusDao().findByCruise(cruise));
    }

    public static CruiseStatus findLazyByCruise(Cruise cruise){
        return DaoFactory.getCruiseStatusDao().findByCruise(cruise);
    }

    public static CruiseStatus getEncodingCruiseStatus(CruiseStatus cruiseStatus){
        try {
            if (cruiseStatus.getName() != null) {
                cruiseStatus.setName(new String(cruiseStatus.getName().getBytes("ISO-8859-1"), "cp1251"));
            }
            if (cruiseStatus.getCruises() != null){
                cruiseStatus.setCruises(CruiseService.getEncodingCruise(cruiseStatus.getCruises()));
            }
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e);
        }
        return cruiseStatus;
    }

    public static void add(CruiseStatus cruiseStatus){
        DaoFactory.getCruiseStatusDao().add(cruiseStatus);
    }

    public static void update(CruiseStatus cruiseStatus){
        DaoFactory.getCruiseStatusDao().update(cruiseStatus);
    }

    public static void delete(CruiseStatus cruiseStatus){
        DaoFactory.getCruiseStatusDao().delete(cruiseStatus);
    }

    public static CruiseStatus getFull(CruiseStatus cruiseStatus){
        if (cruiseStatus != null) {
            cruiseStatus.setCruises(CruiseService.findAllLazyByStatus(cruiseStatus));
        }
        return cruiseStatus;
    }

    public static List<CruiseStatus> getFull(List<CruiseStatus> cruiseStatuses){
        if (cruiseStatuses != null) {
            for (CruiseStatus cruiseStatus : cruiseStatuses) {
                cruiseStatus.setCruises(CruiseService.findAllLazyByStatus(cruiseStatus));
            }
        }
        return cruiseStatuses;
    }
}
