package wak.system.server;

import wak.objects.Kategorie;
import wak.objects.Produkt;
import wak.system.Formatter;
import wak.system.db.DB_Connector;
import wak.user.Mitarbeiter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by chris_000 on 28.10.2015.
 */
@WebServlet(name = "NeuerMitarbeiter")
public class NeuerMitarbeiter extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String vorname, nachname, benutzername, passwort;

        vorname = request.getParameter("vorname");
        nachname = request.getParameter("nachname");
        benutzername = request.getParameter("benutzername");
        passwort = request.getParameter("password");

        passwort= Formatter.hashen(passwort);

        //Mitarbeiter erstellen
        Mitarbeiter m = new Mitarbeiter(vorname, nachname, benutzername, passwort);
        //In Datenbank übertragen


        String nextJSP = "/index.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(request,response);

        //response.sendRedirect("../artikel.jsp?id="+produkt_id);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
