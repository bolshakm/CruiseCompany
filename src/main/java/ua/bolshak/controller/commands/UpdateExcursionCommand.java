package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Excursion;
import ua.bolshak.model.service.ExcursionService;
import ua.bolshak.model.service.PortService;
import ua.bolshak.properties.RequestParams;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateExcursionCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static final String ID_EXCURSION = params.getProperty("idExcursion");
    private static final String NAME = params.getProperty("name");
    private static final String EXCURSION_NAME = params.getProperty("ExcursionName");
    private static final String PORT_ID = params.getProperty("PortId");
    private static final String PRICE = params.getProperty("price");
    private static final String ID_PORT = params.getProperty("idPort");


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Excursion excursion = ExcursionService.findById(Integer.parseInt(request.getParameter(ID_EXCURSION)));
        String name = request.getParameter(NAME);
        if (name == null){
            request.setAttribute(ID_EXCURSION,excursion.getId());
            request.setAttribute(EXCURSION_NAME, excursion.getName());
            request.setAttribute(PORT_ID, excursion.getPort().getId());
            request.setAttribute(PRICE, excursion.getPrice());
        } else {
            String price = request.getParameter(PRICE);
            String idPort = request.getParameter(ID_PORT);
            excursion.setName(name);
            excursion.setPrice(Double.parseDouble(price));
            excursion.setPort(PortService.findById(Integer.parseInt(idPort)));
            ExcursionService.update(excursion);
        }
        return new ToPortsPage().execute(request, response);
    }
}
