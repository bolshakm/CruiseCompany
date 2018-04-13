package ua.bolshak.model.dao.util;

public interface SqlQuery {
    //bonuses table
    String SELECT_ALL_BONUSES = "SELECT * FROM bonuses";
    String SELECT_ALL_BONUSES_BY_TICKET = "SELECT bonuses.* FROM bonuses JOIN tickets_has_bonuses thb ON bonuses.id_bonus = thb.bonuses_id_bonus WHERE thb.tickets_id_ticket = ?";
    String SELECT_ALL_BONUSES_BY_SHIP = "SELECT bonuses.* FROM bonuses JOIN bonuses_has_ships bhs ON bonuses.id_bonus = bhs.bonuses_id_bonus WHERE bhs.ships_id_ship = ?";
    String FIND_BONUS_BY_ID = "SELECT * FROM bonuses WHERE id_bonus = ?";
    String ADD_BONUS = "INSERT INTO bonuses (bonus_name) VALUES (?)";
    String UPDATE_BONUS = "UPDATE bonuses SET bonus_name = ? WHERE id_bonus= ?";
    String DELETE_BONUS = "DELETE FROM bonuses WHERE id_bonus = ?";
    //cruises table
    String FIND_ALL_CRUISES = "SELECT * FROM cruises";
    String FIND_ALL_CRUISES_BY_PORT = "SELECT cruises.* FROM cruises JOIN cruises_has_ports chp ON cruises.id_cruise = chp.cruises_id_cruise WHERE chp.ports_id_port = ?";
    String FIND_ALL_CRUISES_BY_STATUS = "SELECT cruises.* FROM cruises WHERE cruise_statuses_id_cruise_status = ?";
    String FIND_ALL_CRUISES_BY_SHIP = "SELECT * FROM cruises WHERE ships_id_ship = ?";
    String FIND_ALL_CRUISES_BY_USER = "SELECT cruises.* from cruises JOIN tickets t ON cruises.id_cruise = t.cruises_id_cruise WHERE users_id_user = ?";
    String FIND_CRUISE_BY_TICKET = "SELECT cruises.* FROM cruises JOIN tickets t ON cruises.id_cruise = t.cruises_id_cruise WHERE t.id_ticket = ?";
    String FIND_CRUISE_BY_ID = "SELECT cruises.* FROM cruises WHERE id_cruise = ?";
    String FIND_CRUISE_BY_NAME = "SELECT * from cruises where cruise_name = ?";
    String ADD_CRUISE = "INSERT INTO cruises (cruise_name, cruise_from, cruise_to, ships_id_ship, cruise_statuses_id_cruise_status) VALUES (?, ?, ?, ?, ?)";
    String ADD_PORTS_FOR_CRUISE = "INSERT INTO cruises_has_ports (ports_id_port, cruises_id_cruise) VALUES (?, ?)";
    String UPDATE_CRUISE = "UPDATE cruises SET cruise_name = ?, cruise_from = ?, cruise_to = ?, ships_id_ship = ?, cruise_statuses_id_cruise_status = ? WHERE id_cruise = ?";
    String DELETE_CRUISE = "DELETE FROM cruises WHERE id_cruise = ?";
    String DELETE_CRUISE_HAS_PORTS = "DELETE FROM cruises_has_ports WHERE cruises_id_cruise=?";
    //cruisesStatus table
    String FIND_ALL_CRUISE_STATUSES = "SELECT * FROM cruise_statuses";
    String FIND_CRUISE_STATUS_BY_ID = "SELECT * FROM cruise_statuses WHERE id_cruise_status = ?";
    String FIND_CRUISE_STATUS_BY_CRUISE = "SELECT cruise_statuses.* FROM cruise_statuses JOIN cruises c2 ON cruise_statuses.id_cruise_status = c2.cruise_statuses_id_cruise_status WHERE c2.id_cruise = ?";
    String ADD_CRUISE_STATUS = "INSERT INTO cruise_statuses (cruise_status_name) VALUES (?)";
    String UPDATE_CRUISE_STATUS = "UPDATE cruise_statuses SET cruise_status_name = ? WHERE id_cruise_status = ?";
    String DELETE_CRUISE_STATUS = "DELETE FROM cruise_statuses WHERE id_cruise_status = ?";
    //excursions table
    String FIND_ALL_EXCURSIONS = "SELECT * FROM excursions";
    String FIND_ALL_EXCURSIONS_BY_PORT = "SELECT * FROM excursions WHERE ports_id_port = ?";
    String FIND_ALL_EXCURSIONS_BY_TICKET = "SELECT excursions.* FROM excursions JOIN tickets_has_excursions t ON excursions.id_excursion = t.excursions_id_excursion WHERE tickets_id_ticket = ?";
    String FIND_EXCURSION_BY_ID = "SELECT * FROM excursions WHERE  id_excursion = ?";
    String ADD_EXCURSION = "INSERT INTO excursions (excursion_name, excursion_price, ports_id_port) VALUES (?, ?, ?)";
    String UPDATE_EXCURSION = "UPDATE excursions SET excursion_name= ?, excursion_price = ?, ports_id_port = ? WHERE id_excursion = ? ";
    String DELETE_EXCURSION = "DELETE FROM excursions WHERE id_excursion = ?";
    //ports table
    String FIND_ALL_PORTS = "SELECT * FROM ports";
    String FIND_ALL_PORTS_BY_CRUISE = "SELECT ports.* FROM ports JOIN cruises_has_ports chp ON ports.id_port = chp.ports_id_port WHERE chp.cruises_id_cruise  = ?";
    String FIND_PORT_BY_ID = "SELECT * FROM ports WHERE id_port = ?";
    String FIND_PORT_BY_EXCURSION = "SELECT ports.* FROM ports JOIN excursions e ON ports.id_port = e.ports_id_port WHERE id_excursion = ?";
    String ADD_PORT = "INSERT INTO ports (port_name, port_city, port_country) VALUES (?, ?, ?)";
    String UPDATE_PORT = "UPDATE ports SET port_name= ?, port_city = ?, port_country = ? WHERE id_port = ?";
    String DELETE_PORT = "DELETE FROM ports WHERE id_port = ?";
    //roles table
    String FIND_ALL_ROLES = "SELECT * FROM roles";
    String FIND_ROLE_BY_ID = "SELECT * FROM roles WHERE id_role = ?";
    String FIND_ROLE_BY_USER = "SELECT roles.* FROM roles JOIN users u ON roles.id_role = u.roles_id_role WHERE u.id_user = ?";
    String ADD_ROLE = "INSERT INTO roles (role_name) VALUES (?)";
    String UPDATE_ROLE = "UPDATE roles SET role_name = ? WHERE id_role = ?";
    String DELETE_ROLE = "DELETE FROM roles WHERE id_role = ?";
    //ships table
    String FIND_ALL_SHIPS = "SELECT * FROM ships";
    String FIND_ALL_SHIPS_BY_TYPE = "SELECT * FROM ships WHERE ship_types_id_ship_type = ?";
    String FIND_ALL_SHIPS_BY_BONUS = "SELECT ships.* FROM ships JOIN bonuses_has_ships bhs ON ships.id_ship = bhs.ships_id_ship WHERE bonuses_id_bonus = ?";
    String FIND_SHIP_BY_ID = "SELECT * from ships WHERE id_ship = ?";
    String FIND_SHIP_BY_NUMBER = "SELECT * from ships where ship_number = ?";
    String FIND_SHIP_BY_CRUISE = "SELECT ships.* from ships JOIN cruises c2 ON ships.id_ship = c2.ships_id_ship WHERE c2.id_cruise = ?";
    String ADD_SHIP = "INSERT INTO ships (ship_name, ship_number, number_of_seats, price_per_seat, ship_types_id_ship_type) VALUES (?, ?, ?, ?, ?)";
    String UPDATE_SHIP = "UPDATE ships SET ship_name = ?, ship_number = ?, number_of_seats = ?, price_per_seat = ?, ship_types_id_ship_type = ? WHERE id_ship = ?";
    String DELETE_SHIP = "DELETE FROM ships WHERE id_ship = ?";
    String DELETE_ALL_SHIP_HAS_BONUSES = "DELETE FROM bonuses_has_ships WHERE  ships_id_ship = ?";
    String ADD_BONUS_FOR_SHIP ="INSERT INTO bonuses_has_ships (ships_id_ship, bonuses_id_bonus) VALUES (?, ?)";
    //shipTypes table
    String FIND_ALL_SHIP_TYPES = "SELECT * FROM ship_types";
    String FIND_SHIP_TYPE_BY_ID = "SELECT * FROM ship_types WHERE id_ship_type = ?";
    String FIND_SHIP_TYPE_BY_SHIP = "SELECT ship_types.* FROM ship_types JOIN ships s ON ship_types.id_ship_type = s.ship_types_id_ship_type WHERE s.id_ship = ?";
    String ADD_SHIP_TYPE = "INSERT INTO ship_types (ship_type_name) VALUES (?)";
    String UPDATE_SHIP_TYPE = "UPDATE ship_types SET ship_type_name = ? WHERE id_ship_type = ?";
    String DELETE_SHIP_TYPE = "DELETE FROM ship_types WHERE id_ship_type = ?";
    //ticketTypes table
    String FIND_ALL_TICKET_TYPES = "SELECT * FROM ticket_types";
    String FIND_TICKET_TYPE_BY_ID = "SELECT * FROM ticket_types WHERE id_ticket_type = ?";
    String FIND_TICKET_TYPE_BY_TICKET = "SELECT ticket_types.* FROM ticket_types JOIN tickets t ON ticket_types.id_ticket_type = t.ticket_types_id_ticket_type WHERE t.id_ticket = ?";
    String ADD_TICKET_TYPE = "INSERT INTO ticket_types (ticket_type_name, ticket_type_price) VALUES (?, ?)";
    String UPDATE_TICKET_TYPE = "UPDATE ticket_types SET ticket_type_name = ?, ticket_type_price = ? WHERE id_ticket_type = ?";
    String DELETE_TICKET_TYPE = "DELETE FROM ticket_types WHERE id_ticket_type = ?";
    //users table
    String FIND_ALL_USERS = "SELECT * FROM users";
    String FIND_ALL_USERS_BY_ROLE = "SELECT * FROM users WHERE roles_id_role = ?";
    String FIND_ALL_USERS_BY_CRUISE = "SELECT users.* FROM users JOIN tickets t ON users.id_user = t.users_id_user WHERE t.cruises_id_cruise = ?";
    String FIND_ALL_USERS_BY_CRUISE_AND_ROLE = "SELECT users.* FROM users JOIN tickets t ON users.id_user = t.users_id_user WHERE t.cruises_id_cruise = ? AND users.roles_id_role = ?";
    String FIND_ALL_USERS_BY_TICKET = "SELECT users.* FROM users JOIN tickets t ON users.id_user = t.users_id_user WHERE t.ticket_types_id_ticket_type = ?";
    String FIND_ALL_USERS_BY_CRUISE_AND_TICKET_TYPE = "SELECT users.* FROM users JOIN tickets t ON users.id_user = t.users_id_user WHERE t.ticket_types_id_ticket_type = ? AND t.cruises_id_cruise = ?";
    String FIND_USER_BY_ID = "SELECT * FROM users WHERE id_user = ?";
    String FIND_USER_BY_TICKET = "SELECT users.* FROM users JOIN tickets t ON users.id_user = t.users_id_user WHERE t.id_ticket = ?";
    String FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login = ?";
    String FIND_USER_BY_EMAIL = "SELECT * FROM users WHERE email = ?";
    String ADD_USER = "INSERT INTO users (login, password, user_name, user_last_name, email, money, roles_id_role) VALUES (?, ?, ?, ?, ?, ?, ?)";
    String UPDATE_USER = "UPDATE users SET login = ?, password = ?, user_name = ?, user_last_name = ?, email = ?, money = ?, roles_id_role = ? WHERE id_user = ?";
    String DELETE_USER = "DELETE FROM users WHERE id_user = ?";
    //tickets table
    String FIND_ALL_TICKETS = "SELECT * FROM tickets";
    String FIND_ALL_TICKETS_BY_USER = "SELECT * FROM tickets WHERE users_id_user = ?";
    String FIND_ALL_TICKETS_BY_CRUISE = "SELECT * FROM tickets WHERE cruises_id_cruise = ?";
    String FIND_ALL_TICKETS_BY_TICKET_TYPES = "SELECT tickets.* FROM tickets JOIN ticket_types t2 ON tickets.ticket_types_id_ticket_type = t2.id_ticket_type WHERE t2.id_ticket_type = ?";
    String FIND_ALL_TICKETS_BY_BONUS = "SELECT tickets.* FROM tickets JOIN tickets_has_bonuses thb ON tickets.id_ticket = thb.tickets_id_ticket WHERE thb.bonuses_id_bonus = ?";
    String FIND_ALL_TICKETS_BY_EXCURSION = "SELECT tickets.* FROM tickets JOIN tickets_has_excursions t ON tickets.id_ticket = t.tickets_id_ticket WHERE t.excursions_id_excursion = ?";
    String FIND_TICKET_BY_ID = "SELECT * FROM tickets WHERE id_ticket = ?";
    String FIND_TICKET_BY_NAME = "SELECT * FROM tickets WHERE name = ?";
    String ADD_TICKET = "INSERT INTO tickets (users_id_user, name, last_name, ticket_types_id_ticket_type, cruises_id_cruise, price) VALUES (?, ?, ?, ?, ?, ?)";
    String CHECK_USERS_MONEY = "select users.money from users where id_user = ?";
    String CHECK_ADMIN_MONEY = "select users.money from users where id_user = ?";
    String GET_MONEY_PER_TICKET = "update users set money = ? where id_user = ?";
    String SET_MONEY_PER_TICKET = "update users set money = ? where id_user = ?";
    String UPDATE_TICKET = "UPDATE tickets SET users_id_user = ?, name = ?, last_name = ?, ticket_types_id_ticket_type = ?, cruises_id_cruise = ?, price = ? WHERE id_ticket = ?";
    String DELETE_TICKET_HAS_BONUSES = "delete from tickets_has_bonuses where tickets_id_ticket = ?";
    String DELETE_TICKET_HAS_EXCURSIONS = "delete from tickets_has_excursions where tickets_id_ticket = ?";
    String ADD_TICKET_HAS_BONUSES = "INSERT INTO tickets_has_bonuses (tickets_id_ticket, bonuses_id_bonus) VALUES (?, ?);";
    String ADD_TICKET_HAS_EXCURSIONS = "INSERT INTO tickets_has_excursions (excursions_id_excursion, tickets_id_ticket) VALUES (?, ?)";
    String DELETE_TICKET = "DELETE FROM tickets WHERE id_ticket = ?";
}
