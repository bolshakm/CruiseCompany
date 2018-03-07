package ua.bolshak.model;

import ua.bolshak.model.dao.daoImpl.CruiseDao;
import ua.bolshak.model.entity.Cruise;
import ua.bolshak.model.entity.CruiseStatus;
import ua.bolshak.model.entity.User;
import ua.bolshak.model.service.CruiseService;

import java.util.List;

public class Runner {
    public static void main(String[] args) {

        User user = new User();
        user.setId(1);
        List<Cruise> cruises = CruiseService.findAll();
        for (Cruise c :
                cruises) {
            System.out.println(c);
        }
    }
}
