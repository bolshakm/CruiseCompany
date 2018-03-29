package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Port;
import ua.bolshak.model.service.PortService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdatePortCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Port port = PortService.findById(Integer.parseInt(request.getParameter("idPort")));
        String portName = request.getParameter("PortName");
        String cityName = request.getParameter("CityName");
        String countryName = request.getParameter("CityName");
        if (portName == null || cityName == null || countryName == null){
            request.setAttribute("idPort", port.getId());
            request.setAttribute("PortName", port.getName());
            request.setAttribute("CityName", port.getCity());
            request.setAttribute("CountryName", port.getCountry());
        } else {
            port.setName(portName);
            port.setCity(cityName);
            port.setCountry(countryName);
            PortService.update(port);

        }
        return new ToPortsPage().execute(request, response);
    }
}
