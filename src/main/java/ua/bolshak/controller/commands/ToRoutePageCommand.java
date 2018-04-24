package ua.bolshak.controller.commands;

import ua.bolshak.model.service.RouteService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToRoutePageCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("Routes", RouteService.findAll());
        return "/jsp/route.jsp";
    }
}
