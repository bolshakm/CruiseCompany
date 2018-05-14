package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Excursion;
import ua.bolshak.model.service.ExcursionService;
import ua.bolshak.model.service.TicketService;
import ua.bolshak.properties.RequestParams;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteExcursionCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static final String ERROR_MASSAGE = params.getProperty("ErrorMassage");
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Excursion excursion = ExcursionService.findById(Integer.parseInt(request.getParameter("idExcursion")));
        if (!TicketService.checkActiveTicker(excursion.getTickets())) {
            ExcursionService.delete(excursion);
        } else {
            request.setAttribute(ERROR_MASSAGE, "The excursion has active ticket!");
        }
        return new ToPortsPage().execute(request, response);
    }
}
