package ua.bolshak.controller.commands;

import ua.bolshak.model.service.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RedirectCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        String page = "/jsp/" + request.getParameter("page") + ".jsp";
        String page = "/jsp/administrator.jsp";
        String to = request.getParameter("redirectTo");
        request.setAttribute("Cruises", CruiseService.findAll());
        switch (to){
            case "Ships": request.setAttribute("ShipTypes", ShipTypeService.findAll());
                request.setAttribute("Bonuses", BonusService.findAll());
                request.setAttribute("Ships", ShipService.findAll());
                request.setAttribute("page", "ship");
                break;
            case "Ports": request.setAttribute("Ports", PortService.findAll());
                request.setAttribute("page", "port");
                break;
            case "Users": request.setAttribute("Users", UserService.findAll());
                request.setAttribute("Roles", RoleService.findAll());
                request.setAttribute("page", "users");
                break;
            case "Tickets": request.setAttribute("Tickets", TicketService.findAll());
                request.setAttribute("TicketTypes", TicketTypeService.findAll());
                request.setAttribute("page", "tickets");
                break;
            case "Logout": page = new LogoutCommand().execute(request, response);
                break;
//            case "Cruises": request.setAttribute("page", "cruise");
//                break;
            default: request.setAttribute("Cruises", CruiseService.findAll());
            request.setAttribute("CruiseStatuses", CruiseStatusService.findAll());
                request.setAttribute("page", "cruise");
        }

        return page;
    }
}
