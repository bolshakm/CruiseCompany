package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Port;
import ua.bolshak.model.service.PortService;
import ua.bolshak.properties.RequestParams;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdatePortCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static final String ID_PORT = params.getProperty("idPort");
    private static final String PORT_NAME = params.getProperty("PortName");
    private static final String CITY_PORT = params.getProperty("CityName");
    private static final String COUNTRY_NAME = params.getProperty("CountryName");
    @Override

    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Port port = PortService.findById(Integer.parseInt(request.getParameter(ID_PORT)));
        String portName = request.getParameter(PORT_NAME);
        String cityName = request.getParameter(CITY_PORT);
        String countryName = request.getParameter(COUNTRY_NAME);
        if (portName == null || cityName == null || countryName == null){
            request.setAttribute(ID_PORT, port.getId());
            request.setAttribute(PORT_NAME, port.getName());
            request.setAttribute(CITY_PORT, port.getCity());
            request.setAttribute(COUNTRY_NAME, port.getCountry());
        } else {
            port.setName(portName);
            port.setCity(cityName);
            port.setCountry(countryName);
            PortService.update(port);

        }
        return new ToPortsPage().execute(request, response);
    }
}
