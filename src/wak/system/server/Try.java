package wak.system.server;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by chris_000 on 21.09.2015.
 */
public class Try extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        PrintWriter writer = response.getWriter();
        writer.println("<html>");
        writer.println("<head><title>Hello World Servlet</title></head>");
        writer.println("<body>");
        writer.println("	<h1>Hello World from a Sevlet!</h1>");
        writer.println("<body>");
        writer.println("</html>");

        writer.close();
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        PrintWriter writer = response.getWriter();

        writer.println("<html>");
        writer.println("<head><title>Hello World Servlet</title></head>");
        writer.println("<body>");
        writer.println("	<h1>Hello World from a Sevlet!</h1>");
        writer.println("<body>");
        writer.println("</html>");

        writer.close();
    }
}
