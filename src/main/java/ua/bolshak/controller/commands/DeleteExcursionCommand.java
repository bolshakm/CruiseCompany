package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Excursion;
import ua.bolshak.model.service.ExcursionService;
import ua.bolshak.model.service.TicketService;
import ua.bolshak.properties.RequestParams;
import ua.bolshak.properties.TextResources;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteExcursionCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static TextResources text = TextResources.getInstance();
    private static final String ID_EXCURSION = params.getProperty("idExcursion");
    private static final String ERROR_MASSAGE = params.getProperty("ErrorMassage");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String EXCURSION_HAS_ACTIVE_TICKET = text.getProperty("excursion.has.active.ticket");
        Excursion excursion = ExcursionService.findById(Integer.parseInt(request.getParameter(ID_EXCURSION)));
        if (!TicketService.checkActiveTicker(excursion.getTickets())) {
            ExcursionService.delete(excursion);
        } else {
            request.setAttribute(ERROR_MASSAGE, EXCURSION_HAS_ACTIVE_TICKET);
        }
        return new ToPortsPage().execute(request, response);
    }
}
