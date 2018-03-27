package ua.bolshak.controller.commands;

import ua.bolshak.model.service.PortService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToPortsPage implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("Ports", PortService.findAll());
        return "/jsp/port.jsp";
    }
}
