import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import javax.json.*;


@WebServlet(name = "/DemoServlet")
public class DemoServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String body = getBody(request);
        System.out.println("POST: " + body);

        // Fetch Data
        String[] query = body.split(",");
        String command = query[0];
        boolean status;

        if("leave".equals(query[0])) {
            status = false;
        } else {
            status = true;
        }

        if("enter".equals(command)) {
            ServletContext application = getApplication();


            car newcar = new car(status, query[1], Long.parseLong(query[2]), query[5], query[8], query[6], Integer.parseInt(query[7]));
            newcar.appendList(newcar);
            System.out.println(car.returnTicket().toString()+ " SizeOfTicketList: " + car.numberToIndex.size());
            System.out.println("SizeOfCarsList: " + car.cars.size());
        }

        if("leave".equals(command)) {
            ServletContext application = getApplication();
            Float price = Float.parseFloat(query[4]) / 100;
            Float time = Float.parseFloat(query[3]);
            // Update die Summe
            application.setAttribute("sum", getPersistentSum()+ price);
            // Update den car_counter
            application.setAttribute("car_counter", getCarCount() + 1);
            //System.out.println(getCarCount());
            // Update die durchschnittliche Parkdauer
            application.setAttribute("avg_time", getAvgParkingTime() + (time / 1000));
            //System.out.println(getAvgParkingTime());
            System.out.println("-----");

            int index = car.returnIndex(query[1]);
            car leavingCar = car.cars.get(index);

            car leftcar = new car(status, query[1], leavingCar.einfahrtszeit, Long.parseLong(query[2]), query[5], query[8], query[6], Integer.parseInt(query[7]), price, time);
            car.cars.set(index, leftcar);

            System.out.println(car.returnCars());
            //System.out.println(car.returnTicket().toString()+ " SizeOfTicketList: " + car.numberToIndex.size());
        }

        if("occupied".equals(command)) {
            ServletContext application = getApplication();
            application.setAttribute("lost", getLostCars() + 1);
            //Diese Autos haben keinen Parkplatz gefunden und müssen aus der Car- und Index-Liste geloescht werden
            String search = query[1].substring(4);
            search = search.substring(0, search.length()-1);

            int index = car.returnIndex(search);
            //System.out.println("Returned index for "+ search +" : " + index);
            car.cars.remove(index);
            car.numberToIndex.remove(index);
            //System.out.println(car.returnTicket().toString()+ " SizeOfTicketList: " + car.numberToIndex.size());
        }

        for(int i = 0; i<query.length; i++) {
            System.out.println("Pos"+i+": "+query[i]);
        }
        System.out.println("-----");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Preparations
        // Get the query
        String [] query = request.getQueryString().split("=");
        String command = query[0];
        String param = query[1];

        if("cmd".equals(command) && "sum".equals(param)) {
            // Output Console
            Float sum = getPersistentSum();
            System.out.println("sum = " + sum);

            // Output Browser
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
            ((DecimalFormat) numberFormat).applyPattern("###.##");
            String output = numberFormat.format(sum);
            out.println(output);
        } else if ("cmd".equals(command) && "avg".equals(param)) {
            Float avgPrice = getPersistentSum() / getCarCount();
            Float avgTime = getAvgParkingTime() / getCarCount();

            // Output Browser
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
            ((DecimalFormat) numberFormat).applyPattern("###.##");
            String output = numberFormat.format(avgPrice);
            out.println(output + " - " + avgTime + "s");
        } else if ("cmd".equals(command) && "extra".equals(param)) {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println(getLostCars() + " Autos haben keinen Platz im Parkhaus gefunden!");
        } else if("cmd".equals(command) && "Barchart".equals(param)){
            response.setContentType("text/plain");
            PrintWriter out = response.getWriter();

            //values stehen in data[1], kategorien in data[0]
            String[][] data = statistik.statDataBarStream();

            // JSON-Statistikobjekt/String hier...
            out.println("{\n" + " \"data\": [\n" + " {\n" +
                    " \"x\": [\n" + " \"female\",\n" + " \"any\",\n" + " \"family\"\n" + " ],\n" +
                    " \"y\": [\n" + data[1][0]+",\n" + data[1][1]+",\n" + data[1][2]+"\n" + " ],\n" +
                    " \"type\": \"bar\"\n" + " }\n" + " ]\n" + "}");
        } else if("cmd".equals(command) && "Piechart".equals(param)) {
            response.setContentType("text/plain");
            PrintWriter out = response.getWriter();
            //values stehen in data[1], kategorien in data[0]
            String[][] data = statistik.statDataPieStream();

            // JSON-Statistikobjekt/String hier...
            out.println("{\n" + " \"data\": [\n" + " {\n" +
                    " \"labels\": [\n" + " \" female\",\n" + " \"any\",\n" + " \"family\"\n" + " ],\n" +
                    " \"values\": [\n" + data[1][0]+",\n" + data[1][1]+",\n" + data[1][2]+"\n" + " ],\n" +
                    " \"type\": \"pie\"\n" + " }\n" + " ]\n" + "}");

            /*
            JsonObject kategorieRoot = Json.createObjectBuilder()
                    .add("data", Json.createArrayBuilder()
                            .add(Json.createObjectBuilder()
                                    .add("values", data[1])
                                    .add("labels", data[0])
                                    .add("type", "pie")
                            )
                    ).build();
            System.out.println(kategorieRoot.toString());
            out.println(kategorieRoot.toString());
            //falls JSON String defekt -> ServerFehler 500 -> nix passiert
             */
        } else {
            System.out.println("Invalid Command: " + request.getQueryString());
        }

        // Output Console
        System.out.println("GET: "+ request.getQueryString());
    }

    private ServletContext getApplication() {
        return getServletConfig().getServletContext();
    }

    private Float getPersistentSum() {
        Float sum;
        ServletContext application = getApplication();
        sum = (Float)application.getAttribute("sum");
        if(sum == null) sum = 0.0f;
        return sum;
    }

    private Integer getLostCars() {
        Integer sum;
        ServletContext application = getApplication();
        sum = (Integer) application.getAttribute("lost");
        if(sum == null) sum = 0;
        return sum;
    }

    // Zähle wieviele Autos im Parkhaus waren
    private Float getCarCount() {
        Float cars;
        ServletContext application = getApplication();
        cars = (Float)application.getAttribute("car_counter");
        if(cars == null) cars = 0.0f;
        return cars;
    }

    // Speichere die Dauer jedes Parkaufenthaltes
    private Float getAvgParkingTime() {
        Float time;
        ServletContext application = getApplication();
        time = (Float)application.getAttribute("avg_time");
        if(time == null) time = 0.0f;
        return time;
    }

    // Methode um den Body aus dem Post zu extrahieren
    private static String getBody(HttpServletRequest request) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
        return stringBuilder.toString();
    }
}
