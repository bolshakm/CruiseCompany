package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Bonus;
import ua.bolshak.model.service.BonusService;
import ua.bolshak.properties.RequestParams;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteBonusCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static final String ID_BONUS = params.getProperty("idBonus");
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Bonus bonus = BonusService.findById(Integer.parseInt(request.getParameter(ID_BONUS)));
        BonusService.delete(bonus);
        return new ToShipsPageCommand().execute(request, response);
    }
}
