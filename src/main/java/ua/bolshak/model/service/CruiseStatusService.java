package ua.bolshak.model.service;

import ua.bolshak.model.dao.DaoFactory;
import ua.bolshak.model.entity.Cruise;
import ua.bolshak.model.entity.CruiseStatus;

import java.util.List;

public class CruiseStatusService {

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
        cruiseStatus.setCruises(CruiseService.findAllLazyByStatus(cruiseStatus));
        return cruiseStatus;
    }

    public static List<CruiseStatus> getFull(List<CruiseStatus> cruiseStatuses){
        for (CruiseStatus cruiseStatus : cruiseStatuses) {
            cruiseStatus.setCruises(CruiseService.findAllLazyByStatus(cruiseStatus));
        }
        return cruiseStatuses;
    }
}
