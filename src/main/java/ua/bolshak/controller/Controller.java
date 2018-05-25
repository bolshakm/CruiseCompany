package ua.bolshak.controller;

import org.apache.log4j.Logger;
import ua.bolshak.controller.commands.ICommand;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CruiseCompany")
public class Controller extends HttpServlet{
    private static Logger LOGGER = Logger.getLogger(Controller.class);
    private ControllerHelper controllerHelper = ControllerHelper.getInstance();

    public Controller() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        String page;
        ICommand command = controllerHelper.getCommand(request);
        try {
            page = command.execute(request, response);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
