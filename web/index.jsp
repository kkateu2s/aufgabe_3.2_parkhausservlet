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
    <script src="https://ccmjs.github.io/mkaul-components/parkhaus/versions/ccm.parkhaus-7.0.0.js";></script>

  </head>
  <body>
    <h1>Parkhaus</h1>
    <ccm-parkhaus-7-0-0
      server_url = "http://localhost:8080/DemoServlet1_war_exploded/DemoServlet"
      name = "DemoParkhaus_slysek2s"
      Max = "18"
      simulation.max = "3"
      simulation.enter = "2"
      simulation.delay = "2"
      extra_buttons= '["sum", "avg", "extra"]'>
    </ccm-parkhaus-7-0-0>
  </body>
</html>
