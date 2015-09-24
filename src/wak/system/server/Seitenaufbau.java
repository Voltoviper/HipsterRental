package wak.system.server;

import wak.system.db.DB_Connector;

import javax.servlet.http.HttpServlet;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

/**
 * Created by chris_000 on 24.09.2015.
 */
public class Seitenaufbau extends HttpServlet{

    public static void getEmpfehlungen(JspWriter stream){
        DB_Connector.connecttoDatabase();

        String produkte_string = "SELECT id,name, bezeichnung, mietzins FROM produkt ORDER BY id";
        PreparedStatement produkte_ps = null;
        ResultSet produkte_rs;

        try {
            produkte_ps = DB_Connector.con.prepareStatement(produkte_string);
           produkte_rs = produkte_ps.executeQuery();

            int zaehler=0;
            for(int i=0;i<5;i++){
                stream.print("<tr>\n");
                for(int ii=0;ii<3;ii++){
                    if(produkte_rs.next()) {
                        int id = produkte_rs.getInt("id");
                        String name = produkte_rs.getString("name");
                        String bezeichnung = produkte_rs.getString("bezeichnung");
                        Double mietzins = produkte_rs.getDouble("mietzins");
                        String mietzins_string = formatdouble(mietzins);
                        stream.print("<td onmouseover=this.style.background=\"#FCFD7A\" onmouseout=this.style.background=\"#FCFD5A\" style=\"width:33%; align:center; border:solid 1px #000000\" onclick=self.location.href=\"./jsp/artikel.jsp?id=" + id + "\">");
                        stream.print("<table style=\"max-width:100%\" border=0 ><tr><td colspan=\"2\">" +
                                name +
                                "</td></tr><tr><td rowspan=\"2\" style=\" min-width:30pt; max-width:30pt; min-height:30pt ; max-height:30pt\">" +
                                "Bild" +
                                "</td><td>" +
                                bezeichnung +
                                "</td></tr><tr><td>" +
                                mietzins_string + "" +
                                "</td></tr></table>");

                        stream.print("</td>\n");
                        zaehler++;
                    }
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

            writer.print("<table><tr><td onclick=self.location.href=\"../index.jsp\" style=\"min-width:60pt;text-align:center\" onmouseover=this.style.color=\"#FCFD5A\" onmouseout=this.style.color=\"#000000\">Shop</td>" +
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
    public static void getArtikel(JspWriter writer,String id){
       try {
           int int_id = Integer.parseInt(id);
           DB_Connector.connecttoDatabase();

           String produkt_string = "SELECT name, bezeichnung, details, beschreibung, mietzins, Kategorieid,hersteller_name FROM produkt WHERE id=?";
           PreparedStatement produkt_ps = null;

           ResultSet produkt_rs;

           produkt_ps = DB_Connector.con.prepareStatement(produkt_string);
           produkt_ps.setInt(1,int_id);
           produkt_rs = produkt_ps.executeQuery();
           produkt_rs.next();
           String name= produkt_rs.getString("name");
           String beschreibung = produkt_rs.getString("beschreibung");
           String details = produkt_rs.getString("details");
           String bezeichnung = produkt_rs.getString("bezeichnung");
           String hersteller = produkt_rs.getString("hersteller_name");
           Double mietzins = produkt_rs.getDouble("mietzins");
           String mietzins_string = formatdouble(mietzins);
           writer.print("<td style=\"width:33%; align:center; border:solid 1px #000000\" >");
           writer.print("<table border=0 width=100%><th colspan=3>"+name+"</th><tr><td rowspan =4 style=\"min-width=300pt; min-height=300pt\"> Bild</td>" +
                   "<td width=\"100\">Beschreibung:</td><td>"+beschreibung+"</td></tr><tr>" +
                   "<td width=\"100\">Miete:</td><td>"+mietzins_string+"</td></tr><tr>" +
                   "<td width=\"100\">Bezeichnung:</td><td>"+bezeichnung+"</td></tr><tr>" +
                   "<td width=\"100\">Hersteller:</td><td>"+hersteller+"</table>");
       }catch(IOException e){

       } catch (SQLException e) {
        }
    }
    private static  String formatdouble(Double d){
        DecimalFormat format = new DecimalFormat("#####0.00");
        return format.format(d)+"&#8364";
    }
}
