package ua.bolshak.controller;

import ua.bolshak.controller.commands.*;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

public class ControllerHelper {
    private static ControllerHelper instance;
    private Map<String, ICommand> commands = new LinkedHashMap<>();

    private ControllerHelper() {
        commands.put("login", new LoginCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("updateRole", new UpdateRoleCommand());
        commands.put("registration", new RegistrationCommand());
        commands.put("toCruisesPage", new ToCruisesPage());
        commands.put("toShipsPage", new ToShipsPageCommand());
        commands.put("toPortsPage", new ToPortsPageCommand());
        commands.put("toUsersPage", new ToUserPageCommand());
        commands.put("toTicketsPage", new ToTicketsPageCommand());
        commands.put("addCruiseStatus", new AddCruiseStatusCommand());
        commands.put("deleteCruiseStatus", new DeleteCruiseStatusCommand());
        commands.put("updateCruiseStatus", new UpdateCruiseStatusCommand());
        commands.put("addShipType", new AddShipTypeCommand());
        commands.put("deleteShipType", new DeleteShipTypeCommand());
        commands.put("updateShipType", new UpdateShipTypeCommand());
        commands.put("addBonus", new AddBonusCommand());
        commands.put("deleteBonus", new DeleteBonusCommand());
        commands.put("updateBonus", new UpdateBonusCommand());
        commands.put("addRole", new AddRoleCommand());
        commands.put("deleteRole", new DeleteRoleCommand());
        commands.put("addTicketType", new AddTicketTypeCommand());
        commands.put("deleteTicketType", new DeleteTicketTypeCommand());
        commands.put("toUpdateTicketType", new ToUpdateTicketTypeCommand());
        commands.put("addPort", new AddPortCommand());
        commands.put("deletePort", new DeletePortCommand());
        commands.put("updatePort", new UpdatePortCommand());
        commands.put("addExcursion", new AddExcursionCommand());
        commands.put("deleteExcursion", new DeleteExcursionCommand());
        commands.put("updateExcursion", new UpdateExcursionCommand());
        commands.put("toShipCard", new ToShipCardCommand());
        commands.put("addShip", new AddShipCommand());
        commands.put("deleteShip", new DeleteShipCommand());
        commands.put("toUpdateShipCard", new ToUpdateShipCard());
        commands.put("updateShip", new UpdateShipCommand());
        commands.put("toCruiseCard", new ToCruiseCard());
        commands.put("addCruise", new AddCruiseCommand());
        commands.put("updateCruise", new UpdateCruiseCommand());
        commands.put("deleteCruise", new DeleteCruiseCommand());
        commands.put("toUpdateCruise", new ToUpdateCruiseCommand());
        commands.put("toUpdateTicket", new ToUpdateTicketCommand());
        commands.put("updateTicket", new UpdateTicketCommand());
        commands.put("deleteTicket", new DeleteTicketCommand());
        commands.put("toUpdateUserCard", new ToUpdateUserCardCommand());
        commands.put("updateUser", new UpdateUserCommand());
        commands.put("deleteUser", new DeleteUserCommand());
        commands.put("buyTicket", new ToAddTicketCardCommand());
        commands.put("addTicket", new AddTicketCommand());
        commands.put("getPrice", new ToGetPriceForTicketCommand());
        commands.put("toMainPage", new ToMainPage());
        commands.put("toStaffPage", new ToStaffPageCommand());
        commands.put("toRouteCard", new ToRouteCardCommand());
        commands.put("addRoute", new AddRouteCommand());
        commands.put("deleteRoute", new DeleteRouteCommand());
        commands.put("toUpdateRoute", new ToUpdateRouteCommand());
        commands.put("updateRoute", new UpdateRouteCommand());
        commands.put("toRoutePage", new ToRoutePageCommand());
        commands.put("updateTicketType", new UpdateTicketTypeCommand());
        commands.put("toShipAdministratorPage", new ToShipAdministratorPageCommand());
        commands.put("toSetBonusesForShipByTicketType", new ToAddBonusesToTicketTypeByShipCommand());
        commands.put("editBonuses", new AddBonusesToTicketTypeByShipCommand());
        commands.put("addUser", new AddUserCommand());
        commands.put("toRegistrationPage", new ToRegistrationPage());
        commands.put("searchCruise", new SearchCruiseCommand());
        commands.put("toAdministratorPage", new ToAdministratorPage());
        commands.put("toCruisePage", new ToCruisesPage());
        commands.put("toUserCard", new ToUserCardCommand());
        commands.put("toTicketCard", new ToTicketCardCommand());
        commands.put("actionsForCruise", new ActionsForCruiseCommand());
        commands.put("actionsForCruiseStatus", new ActionsForCruiseStatusCommand());
        commands.put("actionsForPort", new ActionsForPortCommand());
        commands.put("actionsForExcursion", new ActionsForExcursionsCommand());
        commands.put("actionsForRoute", new ActionsForRouteCommand());
        commands.put("actionsForShip", new ActionsForShipCommand());
        commands.put("actionsForShipType", new ActionsForShipTypeCommand());
        commands.put("actionsForBonuses", new ActionsForBonusesCommand());
        commands.put("actionsForTicket", new ActionsForTicketCommand());
        commands.put("actionsForTicketType", new ActionsForTicketTypeCommand());
        commands.put("actionsForUser", new ActionsForUserCommand());
        commands.put("actionsForRole", new ActionsForRoleCommand());
        commands.put("changeLanguage", new ChangeLanguageCommand());
        commands.put("toStatisticPage", new ToStatisticPageCommand());
    }

    public static ControllerHelper getInstance() {
        if (instance == null) {
            instance = new ControllerHelper();
        }
        return instance;
    }

    public ICommand getCommand(HttpServletRequest request) {
        String command = request.getParameter("command");
        if (command == null) {
            return new EmptyCommand();
        }
        return commands.get(command);
    }
}
