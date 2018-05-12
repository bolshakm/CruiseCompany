package ua.bolshak.controller.commands;

import ua.bolshak.model.entity.Ticket;
import ua.bolshak.model.entity.User;
import ua.bolshak.model.service.TicketService;
import ua.bolshak.model.service.TicketTypeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ToTicketsPageCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String page = "/jsp/tickets.jsp";
        User user = (User) request.getSession().getAttribute("user");
        List<Ticket> tickets = new ArrayList<>();
        switch (user.getRole().getId()) {
            case 1:
                tickets = TicketService.findAllWithFullCruise();
                if (tickets != null) {
                    request.setAttribute("Tickets", tickets);
                }
                request.setAttribute("TicketTypes", TicketTypeService.findAll());
                break;
            case 2:
                tickets = TicketService.findAllByUser(user);
                if (tickets != null) {
                    request.setAttribute("Tickets", tickets);
                } else {
                    request.setAttribute("InfoMassage", "Tickets not found!");
                }
                break;
            case 3:
                tickets = TicketService.findAllByShips(user.getShip());
                if (tickets != null) {
                    request.setAttribute("Tickets", tickets);
                } else {
                    request.setAttribute("InfoMassage", "Tickets not found!");
                }
                break;
        }
        if (tickets != null) {
            addPagination(request, 5, tickets.size());
        }
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


//    private void addPagination(HttpServletRequest request,int countOnTheOnePage, int listSize){
//        String pageNumber = request.getParameter("pageNumber");
//
//        int intPageNumber = 1;
//        if (pageNumber != null) {
//            intPageNumber = Integer.parseInt(pageNumber);
//        }
//        int begin = countOnTheOnePage * intPageNumber - countOnTheOnePage;
//        int end = countOnTheOnePage * intPageNumber - 1;
//        List<Integer> pageNumbers = new ArrayList<>();
//        if (listSize % countOnTheOnePage == 0){
//            if (intPageNumber == 1 || intPageNumber == 2){
//                for (int i = 1; i <= 3; i++) {
//                    pageNumbers.add(i);
//                }
//            } else {
//                if (intPageNumber == listSize / countOnTheOnePage || intPageNumber == listSize / countOnTheOnePage - 1) {
//                    for (int i = listSize / countOnTheOnePage - 2; i <= listSize / countOnTheOnePage ; i++) {
//                        pageNumbers.add(i);
//                    }
//                } else {
//                    for (int i = intPageNumber - 1; i <= intPageNumber + 1; i++) {
//                        pageNumbers.add(i + 1);
//                    }
//                }
//            }
//        } else {
//            if (intPageNumber == 1 || intPageNumber == 2){
//                for (int i = 1; i <= 3; i++) {
//                    pageNumbers.add(i);
//                }
//            } else {
//                if (intPageNumber == listSize / countOnTheOnePage + 1 || intPageNumber == listSize / countOnTheOnePage) {
//                    for (int i = listSize / countOnTheOnePage - 1; i <= listSize / countOnTheOnePage + 1; i++) {
//                        pageNumbers.add(i);
//                    }
//                } else {
//                    for (int i = intPageNumber - 1; i <= intPageNumber + 1; i++) {
//                        pageNumbers.add(i + 1);
//                    }
//                }
//            }
//        }
//
//        request.setAttribute("begin", begin);
//        request.setAttribute("end", end);
//        request.setAttribute("pageNumbers", pageNumbers);
//    }
}
