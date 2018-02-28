package ua.bolshak.model.dao.util;

public interface SqlQuery {
    String SELECT_ALL_BONUSES = "SELECT * FROM bonuses";
    String SELECT_ALL_BONUSES_BY_TICKET = "SELECT bonuses.* FROM bonuses JOIN tickets_has_bonuses thb ON bonuses.id_bonus = thb.bonuses_id_bonus WHERE thb.tickets_id_ticket = ?";
    String SELECT_ALL_BONUSES_BY_SHIP = "SELECT bonuses.* FROM bonuses JOIN bonuses_has_ships bhs ON bonuses.id_bonus = bhs.bonuses_id_bonus WHERE bhs.ships_id_ship = ?";
    String FIND_BONUS_BY_ID = "SELECT * FROM bonuses WHERE id_bonus = ?";
    String ADD_BONUS = "INSERT INTO bonuses (bonus_name) VALUES (?)";
    String UPDATE_BONUS = "UPDATE bonuses SET bonus_name = ? WHERE id_bonus= ?";
    String DELETE_BONUS = "DELETE FROM bonuses WHERE id_bonus = ?";

    String FIND_ALL_CRUISES = "SELECT * FROM cruises";
    String FIND_ALL_CRUISES_BY_PORT = "SELECT cruises.* FROM cruises JOIN cruises_has_ports chp ON cruises.id_cruise = chp.cruises_id_cruise WHERE chp.ports_id_port = ?";
    String FIND_ALL_CRUISES_BY_STATUS = "SELECT cruises.* FROM cruises WHERE cruise_statuses_id_cruise_status = ?";
    String FIND_ALL_CRUISES_BY_SHIP = "SELECT * FROM cruises WHERE ships_id_ship = ?";
    String FIND_ALL_CRUISES_BY_USER = "SELECT cruises.* from cruises JOIN tickets t ON cruises.id_cruise = t.cruises_id_cruise WHERE users_id_user = ?";
    String FIND_CRUISE_BY_TICKET = "SELECT cruises.* FROM cruises JOIN tickets t ON cruises.id_cruise = t.cruises_id_cruise WHERE t.id_ticket = ?";
    String FIND_CRUISE_BY_ID = "SELECT cruises.* FROM cruises WHERE id_cruise = ?";
    String ADD_CRUISE = "INSERT INTO cruises (cruise_name, cruise_from, cruise_to, income_of_money, ships_id_ship, cruise_statuses_id_cruise_status) VALUES (?, ?, ?, ?, ?, ?";
    String UPDATE_CRUISE = "UPDATE cruises SET cruise_name = ?, cruise_from = ?, cruise_to = ?, income_of_money = ?, ships_id_ship = ?, cruise_statuses_id_cruise_statuse = ? WHERE id_cruise = ?";
    String DELETE_CRUISE = "DELETE FROM cruises WHERE id_cruise = ?";

}
