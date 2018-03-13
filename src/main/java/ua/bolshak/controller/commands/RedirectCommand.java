package ua.bolshak.controller.commands;

import ua.bolshak.model.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RedirectCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
//        String page = "/jsp/" + request.getParameter("page") + ".jsp";
        String page = "/jsp/administrator.jsp";
        String to = request.getParameter("redirectTo");
        request.setAttribute("Cruises", CruiseService.findAll());
        switch (to){
            case "Ships": request.setAttribute("Ships", ShipService.findAll());
                request.setAttribute("page", "ship");
                break;
            case "Ports": request.setAttribute("Ports", PortService.findAll());
                request.setAttribute("page", "port");
                break;
            case "Users": request.setAttribute("Users", UserService.findAll());
                request.setAttribute("page", "users");
                break;
            case "Tickets": request.setAttribute("Tickets", TicketService.findAll());
                request.setAttribute("page", "tickets");
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
