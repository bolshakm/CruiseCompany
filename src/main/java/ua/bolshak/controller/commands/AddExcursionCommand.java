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
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String idPort = request.getParameter("idPort");
        excursion.setName(name);
        excursion.setPrice(Double.parseDouble(price));
        excursion.setPort(PortService.findById(Integer.parseInt(idPort)));
        ExcursionService.add(excursion);
        return new ToPortsPage().execute(request, response);
    }
}
