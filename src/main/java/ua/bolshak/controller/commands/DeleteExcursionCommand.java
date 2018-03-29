package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Excursion;
import ua.bolshak.model.service.ExcursionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteExcursionCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Excursion excursion = ExcursionService.findById(Integer.parseInt(request.getParameter("idExcursion")));
        ExcursionService.delete(excursion);
        return new ToPortsPage().execute(request, response);
    }
}
