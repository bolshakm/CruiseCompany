package ua.bolshak.controller.commands;

import ua.bolshak.model.service.CruiseService;
import ua.bolshak.model.service.ShipService;

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
            case "Ship": request.setAttribute("Ships", ShipService.findAll());
                request.setAttribute("page", "ship");
                break;
//            case "Cruises": request.setAttribute("page", "cruise");
//                break;
            default: request.setAttribute("Cruises", CruiseService.findAll());
                request.setAttribute("page", "cruise");
        }

        return page;
    }
}
