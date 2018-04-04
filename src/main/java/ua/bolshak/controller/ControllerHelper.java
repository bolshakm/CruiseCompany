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
        commands.put("toShipsPage", new ToShipsPage());
        commands.put("toPortsPage", new ToPortsPage());
        commands.put("toUsersPage", new ToUserPage());
        commands.put("toTicketsPage", new ToTicketsPage());
        commands.put("addCruiseStatus", new AddCruiseStatus());
        commands.put("deleteCruiseStatus", new DeleteCruiseStatus());
        commands.put("updateCruiseStatus", new UpdateCruiseStatus());
        commands.put("addShipType", new AddShipTypeCommand());
        commands.put("deleteShipType", new DeleteShipTypeCommand());
        commands.put("updateShipType", new UpdateShipType());
        commands.put("addBonus", new AddBonusCommand());
        commands.put("deleteBonus", new DeleteBonusCommand());
        commands.put("updateBonus", new UpdateBonusCommand());
        commands.put("addRole", new AddRoleCommand());
        commands.put("deleteRole", new DeleteRoleCommand());
        commands.put("addTicketType", new AddTicketTypeCommand());
        commands.put("deleteTicketType", new DeleteTicketTypeCommand());
        commands.put("updateTicketType", new UpdateTicketTypeCommand());
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
        commands.put("toUpdateUserCard", new ToUserCardCommand());
        commands.put("updateUser", new UpdateUserCommand());
        commands.put("deleteUser", new DeleteUserCommand());
    }

    public static ControllerHelper getInstance() {
        if (instance == null){
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
