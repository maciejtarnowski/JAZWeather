<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="service.WebApplication" %>
<%@ page import="service.WebApplicationException" %>
<%@ page import="domain.weather.Weather" %>
<%
    WebApplication app = new WebApplication();
    Weather weather = null;
    try {
        weather = app.weatherByRequest(request);
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

        .temperature .value {
            font-size: 7rem;
        }
        .temperature sup {
            font-size: 4rem;
        }

        .pressure .value {
            font-size: 7rem;
        }

        .wind-speed .value, .clouds .value {
            font-size: 4.5rem;
        }

        .back-link {
            margin-top: 8rem;
        }

        .temperature, .pressure, .wind-speed, .clouds, .back-link {
            text-align: center;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="column column-100">
            <header>
                <h1>Pogoda</h1>
                <h2><%= weather.getCity().getName() %>, <%= weather.getCity().getCountryCode() %></h2>
            </header>
        </div>
    </div>
    <div class="row">
        <div class="column column-50">
            <div class="temperature">
                <div class="value"><%= weather.getTemperature().getValue() %><sup>&deg;C</sup></div>
                <small>Temperatura</small>
            </div>
        </div>
        <div class="column column-50">
            <div class="pressure">
                <div class="value"><%= weather.getPressure() %></div>
                <small>Ciśnienie</small>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="column column-50">
            <div class="wind-speed">
                <div class="value"><%= weather.getWindSpeed() %></div>
                <small>Prędkosć wiatru</small>
            </div>
        </div>
        <div class="column column-50">
            <div class="clouds">
                <div class="value"><%= weather.getClouds() %></div>
                <small>Zachmurzenie</small>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="column column-100 back-link">
            <a class="button button-outline" href="/index.jsp">&laquo; Wróć do listy</a>
        </div>
    </div>
</div>
</body>
</html>
