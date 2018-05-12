package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Cruise;
import ua.bolshak.model.service.CruiseService;
import ua.bolshak.model.service.CruiseStatusService;
import ua.bolshak.model.service.RouteService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ToAdministratorPage implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = "/jsp/administrator.jsp";
        List<Cruise> cruises = CruiseService.findAll();
        request.setAttribute("Cruises", cruises);
        request.setAttribute("CruiseStatuses", CruiseStatusService.findAll());
        request.setAttribute("Routes", RouteService.findAll());
        addPagination(request, 5, cruises.size());
        return page;
    }

    private void addPagination(HttpServletRequest request, int countOnTheOnePage, int listSize) {
        String pageNumber = request.getParameter("pageNumber");
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
        request.setAttribute("begin", begin);
        request.setAttribute("end", end);
        request.setAttribute("pageNumbers", pageNumbers);
    }
}
