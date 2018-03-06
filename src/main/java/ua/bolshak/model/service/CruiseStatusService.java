package ua.bolshak.model.service;

import ua.bolshak.model.dao.DaoFactory;
import ua.bolshak.model.entity.Cruise;
import ua.bolshak.model.entity.CruiseStatus;

import java.util.List;

public class CruiseStatusService {

    public static List<CruiseStatus> findAllLazy(){
        return DaoFactory.getCruiseStatusDao().findAll();
    }

    public static CruiseStatus findLazyById(int id){
        return DaoFactory.getCruiseStatusDao().findById(id);
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
}
