<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>

<html>
    <head>
        <meta charset="UTF-8">
        <title>Топ 250</title>
        <style type="text/css">
            th {
                 background: #81A4FF;
            }
        </style>
    </head>
    <body>
        <div style="text-align: center">
            <label style="font-size: 25pt">Рейтинг кинопоиска</label>
            <p style="color: red;">${error}</p>

            <form name="form" method="post" action="topKinopoisk">
                <label>Укажите дату</label>
                <input id="date" name="date" placeholder="01.01.2020"/>
                <input type="submit" value="Найти"/><br>
            </form>

            <hr style="border: 20px">

            <table align="center" border="1" cellpadding="5" cellspacing="1" >
                 <tr>
                    <th>Позиция</th>
                    <th>Название</th>
                    <th>Год выхода</th>
                    <th>Рейтинг</th>
                    <th>Количество голосов</th>
                    <th>Дата рейтинга</th>
                 </tr>
                 <c:forEach items="${filmList}" var="film" >
                    <tr>
                       <td>${film.position}</td>
                       <td>${film.name}</td>
                       <td>${film.year}</td>
                       <td>${film.rating}</td>
                       <td>${film.numberOfVotes}</td>
                       <td>${film.formattedInstant}</td>
                    </tr>
                 </c:forEach>
            </table>
        </div>
   </body>
</html>