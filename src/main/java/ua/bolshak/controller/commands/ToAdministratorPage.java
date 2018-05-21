package ua.bolshak.controller.commands;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ToAdministratorPage implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        return new ToCruisesPage().execute(request, response);
    }
}
