package ua.bolshak.controller.commands;

import ua.bolshak.model.service.CruiseService;
import ua.bolshak.util.Page;
import ua.bolshak.util.StatisticMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToStatisticPageCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("CruiseComesMoney", new StatisticMap(CruiseService.getMapAllCruiseComesMoney()));
        return Page.STATISTIC.getPage();
    }
}
