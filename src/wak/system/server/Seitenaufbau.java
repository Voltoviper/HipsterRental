package wak.system.server;

import wak.system.db.DB_Connector;

import javax.servlet.http.HttpServlet;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by chris_000 on 24.09.2015.
 */
public class Seitenaufbau extends HttpServlet{

    public static void getEmpfehlungen(JspWriter stream){
        DB_Connector.connecttoDatabase();

        String produkt_string = "SELECT name, bezeichnung, details,mietzins FROM produkt ORDER BY id";
        PreparedStatement produkt_ps = null;
        ResultSet produkt_rs;

        try {
            produkt_ps = DB_Connector.con.prepareStatement(produkt_string);
           produkt_rs = produkt_ps.executeQuery();

            int zaehler=0;
            for(int i=0;i<5;i++){
                stream.print("<tr>\n");
                for(int ii=0;ii<3;ii++){
                    produkt_rs.next();
                    String name= produkt_rs.getString("name");
                    String bezeichnung = produkt_rs.getString("bezeichnung");
                    String details = produkt_rs.getString("details");
                    stream.print("<td style=\"width:33%; align:center; border:solid 1px #000000\">");
                    stream.print("<table style=\"max-width:100%\" border=0><tr><td colspan=\"2\">"+
                            name+
                            "</td></tr><tr><td rowspan=\"2\" style=\" min-width:30pt; max-width:30pt; min-height:30pt ; max-height:30pt\">" +
                            "Bild" +
                            "</td><td>"+
                            bezeichnung+
                            "</td></tr><tr><td>"+
                            details+"" +
                            "</td></tr></table>");

                    stream.print("</td>\n");
                    zaehler++;
                }
                stream.print("</tr>\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void getMenu(JspWriter writer){

        try{
            writer.print("<table><tr><td style=\"min-width:60pt;text-align:center\" onmouseover=this.style.color=\"#FCFD5A\" onmouseout=this.style.color=\"#000000\">Shop</td>" +
                    "<td style=\"min-width:60pt;text-align:center\" onmouseover=this.style.color=\"#FCFD5A\" onmouseout=this.style.color=\"#000000\">Warenkorb </td>" +
                    "<td style=\"min-width:60pt;text-align:center\" onmouseover=this.style.color=\"#FCFD5A\" onmouseout=this.style.color=\"#000000\">Buchung</td>" +
                    "<td style=\"min-width:60pt;text-align:center\" onmouseover=this.style.color=\"#FCFD5A\" onmouseout=this.style.color=\"#000000\">Profil</td>" +
                    "</tr></table>");
        }catch (IOException e){

        }
    }
    public static void getKategorie(JspWriter writer){

        try {

            DB_Connector.connecttoDatabase();

        String kategorie_string = "SELECT name FROM kategorie WHERE oberkategorie is null ORDER BY name";
        PreparedStatement kategorie_ps = null;
        ResultSet kategorie_rs;
        //Vorbereiten der Bestellung für die Datenbank

            kategorie_ps = DB_Connector.con.prepareStatement(kategorie_string);
            kategorie_rs = kategorie_ps.executeQuery();
            while(kategorie_rs.next()){
                String kategorie= kategorie_rs.getString("name");
                writer.print("<tr><td style=\"min-width:60pt;text-align:center\" onmouseover=this.style.color=\"#FCFD5A\" onmouseout=this.style.color=\"#000000\">" +
                        kategorie+
                        "</td></tr>");
            }
        }catch(SQLException e){

        }catch(IOException e1){

        }
    }
}
