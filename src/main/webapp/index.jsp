<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="service.WebApplication" %>
<%@ page import="domain.city.City" %>
<%@ page import="java.util.List" %>
<%@ page import="service.WebApplicationException" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.github.slugify.Slugify" %>
<%
    WebApplication app = new WebApplication();
    List<City> cities = new ArrayList<>();
    try {
        cities = app.listCities();
    } catch (WebApplicationException ex) {
        response.sendError(500, ex.getMessage());
    }
%>
<html>
<head>
    <meta charset="utf-8">
    <title>Pogoda</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,300italic,700,700italic">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/3.0.3/normalize.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/milligram/1.1.0/milligram.min.css">
    <style type="text/css">
        header {
            text-align: center;
            margin-top: 20px;
        }
        header h1 {
            font-weight: 700;
        }

        .city {
            margin: 10px 0 0 0;
            padding: 10px;
            box-sizing: border-box;
        }

        .city h2 {
            float: left;
        }

        .city .cta {
            float: right;
        }

        .city:hover {
            background-color: #FAFAFA;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="column column-100">
                <header>
                    <h1>Pogoda</h1>
                </header>
            </div>
        </div>
        <% for (City city : cities) { %>
            <div class="row city">
                <div class="column column-100">
                    <h2><%= city.getName() %></h2>
                    <a class="button cta" href="/weather.jsp?s=<%= new Slugify().slugify(city.getName()) %>">Szczegóły &raquo;</a>
                </div>
            </div>
        <% } %>
    </div>
</body>
</html>
