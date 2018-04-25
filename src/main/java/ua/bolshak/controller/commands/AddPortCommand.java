package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Port;
import ua.bolshak.model.service.PortService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddPortCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Port port = new Port();
        String portName = request.getParameter("PortName");
        String cityName = request.getParameter("CityName");
        String countryName = request.getParameter("CountryName");
        port.setName(portName);
        port.setCity(cityName);
        port.setCountry(countryName);
        PortService.add(port);
        return new ToPortsPage().execute(request, response);
    }
}
