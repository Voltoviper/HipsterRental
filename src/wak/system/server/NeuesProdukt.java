package wak.system.server;

import wak.objects.Kategorie;
import wak.objects.Produkt;
import wak.system.db.DB_Connector;

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
 * Created by chris_000 on 26.10.2015.
 */
@WebServlet(name = "NeuesProdukt")
public class NeuesProdukt extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Kategorie kat=null;
        Produkt alternative= null;
        int produkt_id=0, alternative_id = 0;
        String name, hersteller, details, bezeichnung, beschreibung, kategorie;
        double mietzins;
        name = request.getParameter("name");
        hersteller = request.getParameter("hersteller");
        details = request.getParameter("details");
        bezeichnung = request.getParameter("bezeichnung");
        beschreibung = request.getParameter("beschreibung");
        kategorie = request.getParameter("kategorie");
        alternative_id = Integer.parseInt(request.getParameter("alternative"));
        mietzins = Double.parseDouble(request.getParameter("mietzins"));

        //Kategorie zuordnen
        for(Kategorie k: Seitenaufbau.kategorien){
            if(k.getName().equals(kategorie)){
                kat=k;
                break;
            }
        }



        //Alternative prüfen
        for(Produkt palt:Seitenaufbau.katalog){
            if(palt.getId()==alternative_id){
                alternative=palt;
                break;
            }
        }


        //Produkt erstellen
        Produkt p = new Produkt(name, bezeichnung, beschreibung, hersteller, details, mietzins, alternative, kat);
        //In Datenbank übertragen



        //ID auslesen
        try {
            DB_Connector.connecttoDatabase();
            PreparedStatement id_ps = DB_Connector.con.prepareStatement("SELECT id FROM produkt WHERE name =? ");
            id_ps.setString(1, p.getName());
            ResultSet id_rs = id_ps.executeQuery();
            id_rs.next();
            produkt_id = id_rs.getInt("id");
            p.setId(produkt_id);
            DB_Connector.closeDatabase();
        }catch(SQLException e){
            e.printStackTrace();
        }


        //Weiterleiten
        response.sendRedirect("../artikel.jsp?id="+produkt_id);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
