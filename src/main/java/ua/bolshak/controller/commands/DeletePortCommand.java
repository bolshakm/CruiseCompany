package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Port;
import ua.bolshak.model.service.ExcursionService;
import ua.bolshak.model.service.PortService;
import ua.bolshak.properties.RequestParams;
import ua.bolshak.properties.TextResources;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeletePortCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static TextResources text = TextResources.getInstance();
    private static final String ERROR_MASSAGE = params.getProperty("ErrorMassage");
    private static final String ID_PORT = params.getProperty("idPort");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String PORT_HAS_EXCURSION = text.getProperty("port.has.excursion");
        Port port = PortService.findById(Integer.parseInt(request.getParameter(ID_PORT)));
        if (!ExcursionService.checkActiveTicker(port.getExcursions())) {
            PortService.delete(port);
        } else {
            request.setAttribute(ERROR_MASSAGE, PORT_HAS_EXCURSION);
        }
        return new ToPortsPageCommand().execute(request, response);
    }
}
