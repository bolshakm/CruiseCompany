package ua.bolshak.model.dao;

import ua.bolshak.model.dao.daoImpl.*;

public abstract class DaoFactory {

    public static BonusDao getBonusDao(){
        return BonusDao.getInstance();
    }

    public static CruiseDao getCruiseDao(){
        return CruiseDao.getInstance();
    }

    public static CruiseStatusDao getCruiseStatusDao(){
        return CruiseStatusDao.getInstance();
    }

    public static ExcursionDao getExcursionDao(){
        return ExcursionDao.getInstance();
    }

    public static PortDao getPortDao(){
        return PortDao.getInstance();
    }

    public static RoleDao getRoleDao(){
        return RoleDao.getInstance();
    }

    public static ShipDao getShipDao(){
        return ShipDao.getInstance();
    }

    public static ShipTypeDao getShipTypeDao(){
        return ShipTypeDao.getInstance();
    }

    public static TicketDao getTicketDao(){
        return TicketDao.getInstance();
    }

    public static TicketTypeDao getTicketTypeDao(){
        return TicketTypeDao.getInstance();
    }

    public static UserDao getUserDao(){
        return UserDao.getInstance();
    }
}
