package org.example.showTopKinopoisk.web;

import org.example.showTopKinopoisk.model.Film;
import org.example.showTopKinopoisk.repository.FilmRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@VariableResolver(DelegatingVariableResolver.class)
public class TopKinopoiskVM {

    private static final Logger LOG = LoggerFactory.getLogger(TopKinopoiskVM.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    @WireVariable
    private FilmRepository filmRepository;

    private String date = "";
    private String error = "";

    @Command
    public void findFilms() {
        if(date.matches("\\d{1,2}\\.\\d{1,2}\\.\\d{4}")) {
            BindUtils.postNotifyChange(null, null,this,"films");
        } else {
            error = "Некорректная дата";
            BindUtils.postNotifyChange(null, null,this,"error");
        }
    }

    public List<Film> getFilms() {
        List<Film> list = new ArrayList<>();
        try {
            list = filmRepository.findByInstant(dateFormat.parse(date).toInstant());
            error = list.isEmpty() ? "Нет рейтинга за эту дату" : "";
        } catch (ParseException e) {
            LOG.error(e.getMessage());
        }
        BindUtils.postNotifyChange(null, null,this,"error");
        return list;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}


