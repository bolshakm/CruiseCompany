package ua.bolshak.model.dao;

import org.apache.log4j.Logger;

public class BonusDao {
    private static BonusDao instance = null;
    private static Logger LOGGER = Logger.getLogger(BonusDao.class);

    private BonusDao() {
    }

    public synchronized static BonusDao getInstance() {
        if (instance == null){
            return new BonusDao();
        }
        return instance;
    }
}
