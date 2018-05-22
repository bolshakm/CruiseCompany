package ua.bolshak.filter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PagesMapping {
    public static Map<Integer, List<String>> PAGES_MAP;
    static {
        PAGES_MAP = new LinkedHashMap<>();
        List<String> admin = new ArrayList<>();
        List<String> user = new ArrayList<>();
        List<String> shipAdministrator = new ArrayList<>();
        List<String> stuff = new ArrayList<>();
        List<String> unregister = new ArrayList<>();

        admin.add("login");
        admin.add("toMainPage");
        admin.add("toShipsPage");
        admin.add("toPortsPage");
        admin.add("toRoutePage");
        admin.add("toUsersPage");
        admin.add("toTicketsPage");
        admin.add("toCruiseCard");
        admin.add("addCruise");
        admin.add("toUpdateCruise");
        admin.add("updateCruise");
        admin.add("deleteCruise");
        admin.add("addCruiseStatus");
        admin.add("updateCruiseStatus");
        admin.add("deleteCruiseStatus");
        admin.add("toShipCard");
        admin.add("addShip");
        admin.add("toUpdateShipCard");
        admin.add("updateShip");
        admin.add("deleteShip");
        admin.add("addShipType");
        admin.add("updateShipType");
        admin.add("deleteShipType");
        admin.add("addBonus");
        admin.add("updateBonus");
        admin.add("deleteBonus");
        admin.add("addExcursion");
        admin.add("updateExcursion");
        admin.add("deleteExcursion");
        admin.add("addPort");
        admin.add("updatePort");
        admin.add("deletePort");
        admin.add("toRouteCard");
        admin.add("addRoute");
        admin.add("toUpdateRoute");
        admin.add("updateRoute");
        admin.add("deleteRoute");
        admin.add("toUserCard");
        admin.add("addUser");
        admin.add("toUpdateUserCard");
        admin.add("deleteUser");
        admin.add("addRole");
        admin.add("updateRole");
        admin.add("deleteRole");
        admin.add("toUpdateTicketPage");
        admin.add("updateTicket");
        admin.add("toUpdateTicket");
        admin.add("deleteTicket");
        admin.add("addTicketType");
        admin.add("toUpdateTicketType");
        admin.add("updateTicketType");
        admin.add("deleteTicketType");
        admin.add("updateUser");
        admin.add("logout");
        admin.add("searchCruise");
        admin.add("toCruisePage");
        admin.add("actionsForCruise");
        admin.add("actionsForCruiseStatus");
        admin.add("actionsForPort");
        admin.add("actionsForExcursion");
        admin.add("actionsForRoute");
        admin.add("actionsForShip");
        admin.add("actionsForShipType");
        admin.add("actionsForBonuses");
        admin.add("actionsForTicket");
        admin.add("actionsForTicketType");
        admin.add("actionsForUser");
        admin.add("actionsForRole");
        admin.add("changeLanguage");

        user.add("login");
        user.add("buyTicket");
        user.add("getPrice");
        user.add("addTicket");
        user.add("toShipsPage");
        user.add("toRoutePage");
        user.add("toPortsPage");
        user.add("toTicketsPage");
        user.add("toMainPage");
        user.add("logout");
        user.add("deleteTicket");
        user.add("toUpdateUserCard");
        user.add("updateUser");
        user.add("toCruisePage");
        user.add("changeLanguage");
        user.add("toUpdateUserCard");

        shipAdministrator.add("login");
        shipAdministrator.add("toMainPage");
        shipAdministrator.add("toShipsPage");
        shipAdministrator.add("toUsersPage");
        shipAdministrator.add("toTicketsPage");
        shipAdministrator.add("toSetBonusesForShipByTicketType");
        shipAdministrator.add("editBonuses");
        shipAdministrator.add("logout");
        shipAdministrator.add("toUpdateUser");
        shipAdministrator.add("updateUser");
        shipAdministrator.add("toCruisePage");
        shipAdministrator.add("changeLanguage");
        shipAdministrator.add("toUpdateUserCard");

        stuff.add("login");
        stuff.add("logout");
        stuff.add("toUpdateUser");
        stuff.add("changeLanguage");
        stuff.add("updateUser");
        stuff.add("toUpdateUserCard");

        unregister.add("login");
        unregister.add("changeLanguage");
        unregister.add("registration");

        PAGES_MAP.put(1, admin);
        PAGES_MAP.put(2, user);
        PAGES_MAP.put(3, shipAdministrator);
        PAGES_MAP.put(4, stuff);
        PAGES_MAP.put(5, unregister);
    }
}
