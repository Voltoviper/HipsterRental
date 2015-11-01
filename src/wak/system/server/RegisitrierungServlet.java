package wak.system.server;

import wak.system.Formatter;
import wak.user.Adresse;
import wak.user.Kunde;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Christoph Nebendahl on 01.11.2015.
 */
@WebServlet(name = "RegisitrierungServlet")
public class RegisitrierungServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String vorname, nachname, strasse, plz, ort, telefonnummer, handynummer, benutzername, email, kennwort;
        int hausnummer;

        vorname = request.getParameter("vorname");
        nachname = request.getParameter("nachname");
        strasse = request.getParameter("strasse");
        plz = request.getParameter("plz");
        ort = request.getParameter("ort");
        telefonnummer = request.getParameter("telefonnummer");
        handynummer = request.getParameter("handynummer");
        benutzername = request.getParameter("benutzername");
        email = request.getParameter("email");
        kennwort = request.getParameter("kennwort");
        hausnummer = Integer.parseInt(request.getParameter("hausnummer"));
        //Hashen des Kennwortes
        kennwort = Formatter.hashen(kennwort);


        Kunde k = new Kunde(null, vorname, nachname, email, telefonnummer, handynummer, new Adresse(strasse, ort, plz, hausnummer),  benutzername, kennwort, true);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
