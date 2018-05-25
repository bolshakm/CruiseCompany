package ua.bolshak.controller.commands;

import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

public class LoginCommandTest extends Mockito {

    private HttpServletRequest request = mock(HttpServletRequest.class);
    private HttpServletResponse response = mock(HttpServletResponse.class);

    @Test
    public void execute() throws IOException, ServletException {
        new LoginCommand().execute(request, response);
        verify(request, atLeast(1)).getParameter("Login");
        verify(request, atLeast(1)).getParameter("Password");
        verify(request, atLeast(1)).getSession();
    }

    @Test
    public void matcherNameTest() throws IOException, ServletException {
        when(request.getParameter("Login")).thenReturn("q");
        when(request.getParameter("Password")).thenReturn("1234");
        when(request.getParameter("actionLogin")).thenReturn("test");
        new LoginCommand().execute(request, response);
        verify(request, atLeast(1)).setAttribute("ErrorMassage", "Wrong input!");
    }

    @Test
    public void matcherPasswordTest() throws IOException, ServletException {
        when(request.getParameter("Login")).thenReturn("login");
        when(request.getParameter("Password")).thenReturn("14");
        when(request.getParameter("actionLogin")).thenReturn("test");
        new LoginCommand().execute(request, response);
        verify(request, atLeast(1)).setAttribute("ErrorMassage", "Wrong input!");
    }
}