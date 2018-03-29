package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Excursion;
import ua.bolshak.model.service.ExcursionService;
import ua.bolshak.model.service.PortService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddExcursionCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Excursion excursion = new Excursion();
        excursion.setName(request.getParameter("name"));
        excursion.setPrice(Double.parseDouble(request.getParameter("price")));
        ExcursionService.add(excursion, PortService.findById(Integer.parseInt(request.getParameter("idPort"))));
        return new ToPortsPage().execute(request, response);
    }
}
