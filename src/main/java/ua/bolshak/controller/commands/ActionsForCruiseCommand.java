package ua.bolshak.controller.commands;

import ua.bolshak.properties.RequestParams;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ActionsForCruiseCommand implements ICommand {
    private static RequestParams params = RequestParams.getInstance();
    private static final String ACTION = params.getProperty("action");
    private static final String DELETE = params.getProperty("Delete");
    private static final String UPDATE = params.getProperty("Update");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String page = null;
        String action = request.getParameter(ACTION);
        if (action.equals(UPDATE)){
            page = new ToUpdateCruiseCommand().execute(request, response);
        }
        if (action.equals(DELETE)){
            page = new DeleteCruiseCommand().execute(request, response);
        }
        return page;
    }
}
