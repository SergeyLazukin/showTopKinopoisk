package org.example.showTopKinopoisk.web.servlets;

import org.example.showTopKinopoisk.model.Film;
import org.example.showTopKinopoisk.repository.FilmRepository;
import org.springframework.web.HttpRequestHandler;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FilmListServlet implements HttpRequestHandler {

    @Resource
    private FilmRepository filmRepository;

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Film> list = new ArrayList<>();
        String error = "";

        String stringDate = request.getParameter("date");
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Date date;
        if(stringDate != null && stringDate.matches("\\d{1,2}\\.\\d{1,2}\\.\\d{4}")) {
            try {
                date = sdf.parse(stringDate);
                list = filmRepository.findByInstant(date.toInstant());
                if(list.isEmpty()) {
                    error = "Нет рейтинга за эту дату";
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            error = "Некорректная дата";
        }
        request.setAttribute("filmList", list);
        request.setAttribute("error", error);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/topKinopoisk.jsp");
        requestDispatcher.forward(request, response);
    }
}
