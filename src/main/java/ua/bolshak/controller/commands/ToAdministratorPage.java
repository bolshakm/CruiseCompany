package ua.bolshak.controller.commands;

//import ua.bolshak.model.entity.Cruise;
//import ua.bolshak.model.service.CruiseService;
//import ua.bolshak.model.service.CruiseStatusService;
//import ua.bolshak.model.service.RouteService;
//import ua.bolshak.properties.RequestParams;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;

public class ToAdministratorPage implements ICommand {
//    private static RequestParams params = RequestParams.getInstance();
//    private static final String CRUISES = params.getProperty("Cruises");
//    private static final String CRUISE_STATUS = params.getProperty("CruiseStatuses");
//    private static final String ROUTES = params.getProperty("Routes");
//    private static final String PAGE_NUMBER = params.getProperty("pageNumber");
//    private static final String PAGE_NUMBERS = params.getProperty("pageNumbers");
//    private static final String BEGIN = params.getProperty("begin");
//    private static final String END = params.getProperty("end");


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        String page = "/jsp/cruise.jsp";
//        List<Cruise> cruises = CruiseService.findAll();
//        request.setAttribute(CRUISES, cruises);
//        request.setAttribute(CRUISE_STATUS, CruiseStatusService.findAll());
//        request.setAttribute(ROUTES, RouteService.findAll());
//        addPagination(request, 5, cruises.size());
        return new ToCruisesPage().execute(request, response);
    }

//    private void addPagination(HttpServletRequest request, int countOnTheOnePage, int listSize) {
//        String pageNumber = request.getParameter(PAGE_NUMBER);
//        int intPageNumber = 1;
//        if (pageNumber != null) {
//            intPageNumber = Integer.parseInt(pageNumber);
//        }
//        int begin = countOnTheOnePage * intPageNumber - countOnTheOnePage;
//        int end = countOnTheOnePage * intPageNumber - 1;
//        List<Integer> pageNumbers = new ArrayList<>();
//        if (listSize / countOnTheOnePage >= 3) {
//            if (listSize % countOnTheOnePage == 0) {
//                if (intPageNumber == 1 || intPageNumber == 2) {
//                    for (int i = 1; i <= 3; i++) {
//                        pageNumbers.add(i);
//                    }
//                } else {
//                    if (intPageNumber == listSize / countOnTheOnePage || intPageNumber == listSize / countOnTheOnePage - 1) {
//                        for (int i = listSize / countOnTheOnePage - 2; i <= listSize / countOnTheOnePage; i++) {
//                            pageNumbers.add(i);
//                        }
//                    } else {
//                        for (int i = intPageNumber - 1; i <= intPageNumber + 1; i++) {
//                            pageNumbers.add(i + 1);
//                        }
//                    }
//                }
//            } else {
//                if (intPageNumber == 1 || intPageNumber == 2) {
//                    for (int i = 1; i <= 3; i++) {
//                        pageNumbers.add(i);
//                    }
//                } else {
//                    if (intPageNumber == listSize / countOnTheOnePage || intPageNumber == listSize / countOnTheOnePage + 1) {
//                        for (int i = listSize / countOnTheOnePage - 1; i <= listSize / countOnTheOnePage + 1; i++) {
//                            pageNumbers.add(i);
//                        }
//                    } else {
//                        for (int i = intPageNumber - 1; i <= intPageNumber + 1; i++) {
//                            pageNumbers.add(i + 1);
//                        }
//                    }
//                }
//            }
//        } else {
//            if (listSize % countOnTheOnePage == 0) {
//                for (int i = 1; i <= listSize / countOnTheOnePage; i++) {
//                    pageNumbers.add(i);
//                }
//            } else {
//                for (int i = 1; i <= listSize / countOnTheOnePage + 1; i++) {
//                    pageNumbers.add(i);
//                }
//            }
//        }
//        request.setAttribute(BEGIN, begin);
//        request.setAttribute(END, end);
//        request.setAttribute(PAGE_NUMBERS, pageNumbers);
//    }
}
