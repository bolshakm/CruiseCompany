package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Bonus;
import ua.bolshak.model.service.BonusService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteBonusCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Bonus bonus = BonusService.findById(Integer.parseInt(request.getParameter("idBonus")));
        BonusService.delete(bonus);
        return new ToShipsPage().execute(request, response);
    }
}
