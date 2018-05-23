package ua.bolshak.controller.commands;

import ua.bolshak.properties.RequestParams;
import ua.bolshak.util.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PaginationCommand implements ICommand{
    private static RequestParams params = RequestParams.getInstance();
    private static final String PAGE_NUMBER = params.getProperty("pageNumber");
    private static final String PAGE_NUMBERS = params.getProperty("pageNumbers");
    private static final String BEGIN = params.getProperty("begin");
    private static final String END = params.getProperty("end");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        return Page.ERROR.getPage();
    }

    void addPagination(HttpServletRequest request, int countOnTheOnePage, int listSize, String listName) {
        String pageNumber = request.getParameter(PAGE_NUMBER + listName);
        int intPageNumber = 1;
        if (pageNumber != null) {
            intPageNumber = Integer.parseInt(pageNumber);
        }
        int begin = countOnTheOnePage * intPageNumber - countOnTheOnePage;
        int end = countOnTheOnePage * intPageNumber - 1;
        List<Integer> pageNumbers = new ArrayList<>();
        if (listSize / countOnTheOnePage >= 3) {
            if (listSize % countOnTheOnePage == 0) {
                if (intPageNumber == 1 || intPageNumber == 2) {
                    for (int i = 1; i <= 3; i++) {
                        pageNumbers.add(i);
                    }
                } else {
                    if (intPageNumber == listSize / countOnTheOnePage || intPageNumber == listSize / countOnTheOnePage - 1) {
                        for (int i = listSize / countOnTheOnePage - 2; i <= listSize / countOnTheOnePage; i++) {
                            pageNumbers.add(i);
                        }
                    } else {
                        for (int i = intPageNumber - 1; i <= intPageNumber + 1; i++) {
                            pageNumbers.add(i + 1);
                        }
                    }
                }
            } else {
                if (intPageNumber == 1 || intPageNumber == 2) {
                    for (int i = 1; i <= 3; i++) {
                        pageNumbers.add(i);
                    }
                } else {
                    if (intPageNumber == listSize / countOnTheOnePage || intPageNumber == listSize / countOnTheOnePage + 1) {
                        for (int i = listSize / countOnTheOnePage - 1; i <= listSize / countOnTheOnePage + 1; i++) {
                            pageNumbers.add(i);
                        }
                    } else {
                        for (int i = intPageNumber - 1; i <= intPageNumber + 1; i++) {
                            pageNumbers.add(i + 1);
                        }
                    }
                }
            }
        } else {
            if (listSize % countOnTheOnePage == 0) {
                for (int i = 1; i <= listSize / countOnTheOnePage; i++) {
                    pageNumbers.add(i);
                }
            } else {
                for (int i = 1; i <= listSize / countOnTheOnePage + 1; i++) {
                    pageNumbers.add(i);
                }
            }
        }
        request.setAttribute(BEGIN + listName, begin);
        request.setAttribute(END + listName, end);
        request.setAttribute(PAGE_NUMBERS + listName, pageNumbers);
    }
}
