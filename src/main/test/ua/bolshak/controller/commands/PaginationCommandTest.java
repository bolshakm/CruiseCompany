package ua.bolshak.controller.commands;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import ua.bolshak.util.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class PaginationCommandTest extends Mockito {

    private HttpServletRequest request = mock(HttpServletRequest.class);
    private HttpServletResponse response = mock(HttpServletResponse.class);

    @Test
    public void addPagination() {
        when(request.getParameter("pageNumbertest")).thenReturn("2");
        new PaginationCommand().addPagination(request, 5,20, "test");
        verify(request, atLeast(1)).getParameter("pageNumbertest");
        verify(request, atLeast(1)).setAttribute("begintest", 5);
        verify(request, atLeast(1)).setAttribute("endtest", 9);

        when(request.getParameter("pageNumbertest1")).thenReturn("3");
        new PaginationCommand().addPagination(request, 5,20, "test1");
        verify(request, atLeast(1)).getParameter("pageNumbertest1");
        verify(request, atLeast(1)).setAttribute("begintest1", 10);
        verify(request, atLeast(1)).setAttribute("endtest1", 14);

        when(request.getParameter("pageNumbertest2")).thenReturn("5");
        new PaginationCommand().addPagination(request, 5,23, "test2");
        verify(request, atLeast(1)).getParameter("pageNumbertest2");
        verify(request, atLeast(1)).setAttribute("begintest2", 20);
        verify(request, atLeast(1)).setAttribute("endtest2", 24);

        when(request.getParameter("pageNumbertest3")).thenReturn(null);
        new PaginationCommand().addPagination(request, 5,23, "test3");
        verify(request, atLeast(1)).getParameter("pageNumbertest3");
        verify(request, atLeast(1)).setAttribute("begintest3", 0);
        verify(request, atLeast(1)).setAttribute("endtest3", 4);
    }

    @Test
    public void execute() throws IOException, ServletException {
        String result = new PaginationCommand().execute(request, response);
        Assert.assertEquals(result, Page.ERROR.getPage());
    }
}