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
//        commands.put("redirect", new RedirectCommand());
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
