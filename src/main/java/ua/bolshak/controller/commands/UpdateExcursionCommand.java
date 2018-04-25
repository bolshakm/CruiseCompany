package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Excursion;
import ua.bolshak.model.service.ExcursionService;
import ua.bolshak.model.service.PortService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateExcursionCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Excursion excursion = ExcursionService.findById(Integer.parseInt(request.getParameter("idExcursion")));
        String name = request.getParameter("name");
        if (name == null){
            request.setAttribute("idExcursion",excursion.getId());
            request.setAttribute("ExcursionName", excursion.getName());
            request.setAttribute("PortId", excursion.getPort().getId());
            request.setAttribute("price", excursion.getPrice());
        } else {
            String price = request.getParameter("price");
            String idPort = request.getParameter("idPort");
            excursion.setName(name);
            excursion.setPrice(Double.parseDouble(price));
            excursion.setPort(PortService.findById(Integer.parseInt(idPort)));
            ExcursionService.update(excursion);
        }
        return new ToPortsPage().execute(request, response);
    }
}
