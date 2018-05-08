package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Cruise;
import ua.bolshak.model.service.CruiseService;
import ua.bolshak.model.service.TicketService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteCruiseCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Cruise cruise = CruiseService.findById(Integer.parseInt(request.getParameter("idCruise")));
        if (!CruiseService.checkActive(cruise)) {
            CruiseService.delete(cruise);
        } else {
            request.setAttribute("ErrorMassage", "The cruise is active and can not be removed!");
        }
        return new ToCruisesPage().execute(request, response);
    }
}
