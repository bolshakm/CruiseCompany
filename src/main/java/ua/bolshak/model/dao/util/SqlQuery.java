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
    String UPDATE_CRUISE = "UPDATE cruises SET cruise_name = ?, cruise_from = ?, cruise_to = ?, income_of_money = ?, ships_id_ship = ?, cruise_statuses_id_cruise_status = ? WHERE id_cruise = ?";
    String DELETE_CRUISE = "DELETE FROM cruises WHERE id_cruise = ?";

    String FIND_ALL_CRUISE_STATUS = "SELECT * FROM cruise_statuses";
    String FIND_CRUISE_STATUS_BY_ID = "SELECT * FROM cruise_statuses WHERE id_cruise_status = ?";
    String FIND_CRUISE_STATUS_BY_CRUISE = "SELECT cruise_statuses.* FROM cruise_statuses JOIN cruises c2 ON cruise_statuses.id_cruise_status = c2.cruise_statuses_id_cruise_status WHERE c2.id_cruise = ?";
    String ADD_CRUISE_STATUS = "INSERT INTO cruise_statuses (cruise_status_name) VALUES (?)";
    String UPDATE_CRUISE_STATUS = "UPDATE cruise_statuses SET cruise_status_name = ? WHERE id_cruise_status = ?";
    String DELETE_CRUISE_STATUS = "DELETE FROM cruise_statuses WHERE id_cruise_status = ?";

    String FIND_ALL_EXCURSION = "SELECT * FROM excursions";
    String FIND_ALL_EXCURSION_BY_PORT = "SELECT * FROM excursions WHERE ports_id_port = ?";
    String FIND_ALL_EXCURSION_BY_TICKET = "SELECT excursions.* FROM excursions JOIN tickets_has_excursions t ON excursions.id_excursion = t.excursions_id_excursion WHERE tickets_id_ticket = ?";
    String FIND_EXCURSION_BY_ID = "SELECT * FROM excursions WHERE  id_excursion = ?";
    String ADD_EXCURSION = "INSERT INTO excursions (excursion_name, excursion_price, ports_id_port) VALUES (?, ?, ?)";
    String UPDATE_EXCURSION = "UPDATE excursions SET excursion_name= ?, excursion_price = ?, ports_id_port = ? WHERE id_excursion = ? ";
    String DELETE_EXCURSION = "DELETE FROM excursions WHERE id_excursion = ?";

    String FIND_ALL_PORTS = "SELECT * FROM ports";
    String FIND_ALL_PORTS_BY_CRUISE = "SELECT ports.* FROM ports JOIN cruises_has_ports chp ON ports.id_port = chp.ports_id_port WHERE chp.cruises_id_cruise  = ?";
    String FIND_PORT_BY_ID = "SELECT * FROM ports WHERE id_port = ?";
    String FIND_PORT_BY_EXCURSION = "SELECT ports.* FROM ports JOIN excursions e ON ports.id_port = e.ports_id_port WHERE id_excursion = ?";
    String ADD_PORT = "INSERT INTO ports (port_name, port_city, port_country) VALUES (?, ?, ?)";
    String UPDATE_PORT = "UPDATE ports SET port_name= ?, port_city = ?, port_country = ? WHERE id_port = ?";
    String DELETE_PORT = "DELETE FROM ports WHERE id_port = ?";

    String FIND_ALL_ROLES = "SELECT * FROM roles";
    String FIND_ROLE_BY_ID = "SELECT * FROM roles WHERE id_role = ?";
    String FIND_ROLE_BY_USER = "SELECT roles.* FROM roles JOIN users u ON roles.id_role = u.roles_id_role WHERE u.id_user = ?";
    String ADD_ROLE = "INSERT INTO roles (role_name) VALUES (?)";
    String UPDATE_ROLE = "UPDATE roles SET role_name = ? WHERE id_role = ?";
    String DELETE_ROLE = "DELETE FROM roles WHERE id_role = ?";

    String FIND_ALL_SHIPS = "SELECT * FROM ships";
    String FIND_ALL_SHIPS_BY_TYPE = "SELECT * FROM ships WHERE ship_types_id_ship_type = ?";
    String FIND_ALL_SHIPS_BY_BONUS = "SELECT ships.* FROM ships JOIN bonuses_has_ships bhs ON ships.id_ship = bhs.ships_id_ship WHERE bonuses_id_bonus = ?";
    String FIND_SHIP_BY_ID = "SELECT * from ships WHERE id_ship = ?";
    String FIND_SHIP_BY_CRUISE = "SELECT ships.* from ships JOIN cruises c2 ON ships.id_ship = c2.ships_id_ship WHERE c2.id_cruise = ?";
    String ADD_SHIP = "INSERT INTO ships (ship_name, ship_number, number_of_seats, price_per_seat, ship_types_id_ship_type) VALUES (?, ?, ?, ?, ?)";
    String UPDATE_SHIP = "UPDATE ships SET ship_name = ?, ship_number = ?, number_of_seats = ?, price_per_seat = ?, ship_types_id_ship_type = ? WHERE id_ship = ?";
    String DELETE_SHIP = "DELETE FROM ships WHERE id_ship = ?";

    String FIND_ALL_SHIP_TYPE = "SELECT * FROM ship_types";
    String FIND_SHIP_TYPE_BY_ID = "SELECT * FROM ship_types WHERE id_ship_type = ?";
    String FIND_SHIP_TYPE_BY_SHIP = "SELECT ship_types.* FROM ship_types JOIN ships s ON ship_types.id_ship_type = s.ship_types_id_ship_type WHERE s.id_ship = ?";
    String ADD_SHIP_TYPE = "INSERT INTO ship_types (ship_type_name) VALUES (?)";
    String UPDATE_SHIP_TYPE = "UPDATE ship_types SET ship_type_name = ? WHERE id_ship_type = ?";
    String DELETE_SHIP_TYPE = "DELETE FROM ship_types WHERE id_ship_type = ?";
}
