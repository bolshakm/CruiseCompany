package ua.bolshak.model;

import ua.bolshak.model.dao.daoImpl.CruiseDao;
import ua.bolshak.model.entity.Cruise;
import ua.bolshak.model.entity.User;

import java.util.List;

public class Runner {
    public static void main(String[] args) {

        User user = new User();
        user.setId(1);
        List<Cruise> cruises = CruiseDao.getInstance().findAllByUser(user);
        for (Cruise c :
                cruises) {
            System.out.println(c);
        }
    }
}
