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

@WebServlet(name = "/DemoServlet")
public class DemoServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String body = getBody(request);
        System.out.println("POST: " + body);

        // Fetch Data
        String[] query = body.split(",");
        String command = query[0];

        if("enter".equals(command)) {
            ServletContext application = getApplication();
            // Insert upcoming code here

        }

        if("leave".equals(command)) {
            ServletContext application = getApplication();
            Float price = Float.parseFloat(query[4]) / 100;
            Float time = Float.parseFloat(query[3]);
            application.setAttribute("sum", getPersistentSum()+ price);

            application.setAttribute("car_counter", getCarCount() + 1);
            System.out.println(getCarCount());

            application.setAttribute("sum_time", getAvgParkingTime() + time);
            System.out.println(getAvgParkingTime());
        }

        /*
        for(int i = 0; i<query.length; i++) {
            System.out.println("Pos"+i+": "+query[i]);
        }
        */
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
            out.println(output + " - " + avgTime);
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
        time = (Float)application.getAttribute("sum_time");
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
