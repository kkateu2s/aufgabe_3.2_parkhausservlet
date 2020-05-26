<%--
  Created by IntelliJ IDEA.
  User: SLyse
  Date: 12.05.2020
  Time: 12:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Parkhaus</title>
    <script src="https://d3js.org/d3.v5.min.js"></script>
    <script src="https://ccmjs.github.io/mkaul-components/parkhaus/versions/ccm.parkhaus-9.0.0.js";></script>
    <script src="https://cdn.plot.ly/plotly-latest.min.js"></script>
  </head>
  <body>
    <h1>Parkhaus</h1>
    <ccm-parkhaus-9-0-0
      server_url = "http://localhost:8080/DemoServlet1_war_exploded/DemoServlet"
      name = "ParkhausServlet5"
      client_categories='["any", "family", "female"]'
      extra_buttons= '["sum", "avg", "extra"]'
      extra_charts='["Barchart", "Piechart"]'>
    </ccm-parkhaus-9-0-0>
  </body>
</html>
