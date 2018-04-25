package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Bonus;
import ua.bolshak.model.service.BonusService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddBonusCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Bonus bonus = new Bonus();
        String name = request.getParameter("BonusName");
        bonus.setName(name);
        BonusService.add(bonus);
        return new ToShipsPage().execute(request, response);
    }
}
