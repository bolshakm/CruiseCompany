package ua.bolshak.controller;

import ua.bolshak.controller.commands.EmptyCommand;
import ua.bolshak.controller.commands.ICommand;
import ua.bolshak.controller.commands.LoginCommand;
import ua.bolshak.controller.commands.RedirectCommand;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

public class ControllerHelper {
    private static ControllerHelper instance;
    private Map<String, ICommand> commands = new LinkedHashMap<>();

    private ControllerHelper() {
        commands.put("login", new LoginCommand());
        commands.put("redirect", new RedirectCommand());
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
