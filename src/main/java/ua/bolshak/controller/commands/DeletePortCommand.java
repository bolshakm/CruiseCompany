package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Port;
import ua.bolshak.model.service.ExcursionService;
import ua.bolshak.model.service.PortService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeletePortCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Port port = PortService.findById(Integer.parseInt(request.getParameter("idPort")));
        if (!ExcursionService.checkActiveTicker(port.getExcursions())) {
            PortService.delete(port);
        } else {
            request.setAttribute("ErrorMassage", "The port has excursion");
        }
        return new ToPortsPage().execute(request, response);
    }
}
