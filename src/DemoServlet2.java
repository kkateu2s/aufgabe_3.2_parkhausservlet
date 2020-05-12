import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DemoServlet2")
public class DemoServlet2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Get the query
        String [] query = request.getQueryString().split("=");
        String key = query[0];
        String value = query[1];

        // Output Browser
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>Hello "+ value +"</h1>");

        // Output Console
        System.out.println("Hello "+ value);
    }
}
