package ua.bolshak.model.dao.util;

public interface ColumnName {
    String ID_BONUS = "id_bonus";
    String BONUS_NAME = "bonus_name";

    String ID_CRUISE = "id_cruise";
    String CRUISE_NAME = "cruise_name";
    String CRUISE_FROM = "cruise_from";
    String CRUISE_TO = "cruise_to";

    String ID_CRUISE_STATUS = "id_cruise_status";
    String CRUISE_STATUS_NAME = "cruise_status_name";

    String ID_EXCURSION = "id_excursion";
    String EXCURSION_NAME = "excursion_name";
    String EXCURSION_PRICE = "excursion_price";

    String ID_PORT = "id_port";
    String PORT_NAME = "port_name";
    String PORT_CITY = "port_city";
    String PORT_COUNTRY = "port_country";

    String ID_ROLE = "id_role";
    String ROLE_NAME = "role_name";

    String ID_SHIP = "id_ship";
    String SHIP_NAME = "ship_name";
    String SHIP_NUMBER = "ship_number";
    String NUMBER_OF_SEATS = "number_of_seats";
    String PRICE_PER_SEAT = "price_per_seat";

    String ID_SHIP_TYPE = "id_ship_type";
    String SHIP_TYPE_NAME = "ship_type_name";

    String ID_TICKET_TYPE = "id_ticket_type";
    String TICKET_TYPE_NAME = "ticket_type_name";
    String TICKET_TYPE_PRICE = "ticket_type_price";

    String ID_USER = "id_user";
    String LOGIN = "login";
    String PASSWORD = "password";
    String USER_NAME = "user_name";
    String USER_LAST_NAME = "user_last_name";
    String EMAIL = "email";
    String MONEY = "money";

    String ID_TICKET = "id_ticket";
    String NAME = "name";
    String LAST_NAME = "last_name";
    String TICKET_PRICE = "price";

    String ID_ROUTE = "id_route";
    String ROUTE_NAME = "route_name";

    String SHIPS_HAS_ID_TICKET_TYPE = "ticket_types_id_ticket_type";
    String SHIPS_BONUSES_BY_TICKET_TYPE = "bonuses_id_bonus";
}
