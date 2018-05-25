package ua.bolshak.controller.commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ICommand {
    /**
     * @param request
     * @param response
     * @return String page for redirect
     * @throws IOException
     * @throws ServletException
     */
    String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
}
