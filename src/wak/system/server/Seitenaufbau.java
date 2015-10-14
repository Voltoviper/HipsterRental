package wak.system.server;

import sun.text.resources.sq.JavaTimeSupplementary_sq;
import wak.objects.Warenkorb;
import wak.system.db.DB_Connector;
import wak.user.Adresse;
import wak.user.Kunde;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by chris_000 on 24.09.2015.
 */
public class Seitenaufbau extends HttpServlet{
   public static  ArrayList<Warenkorb> koerbe = new ArrayList<Warenkorb>();
    public static ArrayList<Kunde> kunde = new ArrayList<Kunde>();

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
                    "<td onclick=self.location.href=\"../jsp/warenkorb.jsp\" style=\"min-width:60pt;text-align:center\" onmouseover=this.style.color=\"#FCFD5A\" onmouseout=this.style.color=\"#000000\">Warenkorb </td>" +
                    "<td style=\"min-width:60pt;text-align:center\" onmouseover=this.style.color=\"#FCFD5A\" onmouseout=this.style.color=\"#000000\">Buchung</td>" +
                    "<td style=\"min-width:60pt;text-align:center\" onmouseover=this.style.color=\"#FCFD5A\" onmouseout=this.style.color=\"#000000\">Profil</td>" +
                    "</tr></table>");
        }catch (IOException e){

        }
    }
    public static void getKategorie(JspWriter writer){

        try {

            DB_Connector.connecttoDatabase();

        String kategorie_string = "SELECT name,id FROM kategorie WHERE oberkategorie is null ORDER BY name";
        PreparedStatement kategorie_ps = null;
        ResultSet kategorie_rs;
        //Vorbereiten der Bestellung für die Datenbank

            kategorie_ps = DB_Connector.con.prepareStatement(kategorie_string);
            kategorie_rs = kategorie_ps.executeQuery();
            while(kategorie_rs.next()){
                String kategorie= kategorie_rs.getString("name");
                int id= kategorie_rs.getInt("id");
                writer.print("<tr><td onclick=self.location.href=\"../jsp/kategorie.jsp?katid="+id+"\" style=\"min-width:60pt;text-align:center\" onmouseover=this.style.color=\"#FCFD5A\" onmouseout=this.style.color=\"#000000\" >" +
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
           writer.print("<table border=0 width=100%><th colspan=4>"+name+"</th><tr><td rowspan =4 style=\"min-width=300pt; min-height=300pt\"> Bild</td>" +
                   "<td width=\"100\">Beschreibung:</td><td>"+beschreibung+"</td><td style:\"text-align:right\" onmouseover=this.style.background=\"#6565FC\" onmouseout=this.style.background=\"#FCFD5A\" onclick=self.location.href=\"../jsp/warenkorb.jsp?addid=" + int_id + "\">Warenkorb</td></tr><tr>" +
                   "<td width=\"100\">Miete:</td><td>"+mietzins_string+"</td><td><div class=\"fb-share-button\" data-href=\"http://www.hipster-rental.de/jsp/artikel?id="+int_id+"\" data-layout=\"button\"></div></tr><tr>" +
                   "<td width=\"100\">Bezeichnung:</td><td>"+bezeichnung+"</td></tr><tr>" +
                   "<td width=\"100\">Hersteller:</td><td>"+hersteller+"</table>");
       }catch(IOException e){

       } catch (SQLException e) {
        }
    }
    public static void getWarenkorb(JspWriter writer,Cookie[] cookies, String produkt_id){
        DB_Connector.connecttoDatabase();
        String produkt_string = "SELECT name, bezeichnung, mietzins  FROM produkt WHERE id=?";
        PreparedStatement produkt_ps = null;
        String tabelle_anfang= "<td><table border=0 style=\"width:100%\">";
        ResultSet produkt_rs;
        boolean cookie_vorhanden=false;
        Cookie cook=null;
        if(cookies!=null){
            for(int i=0;i<cookies.length;i++){
                Cookie c = cookies[i];
                if(c.getName().compareTo("id")==0){
                    cook = c;
                    cookie_vorhanden=true;
                    break;
                }else{
                }
            }
        }else{

        }

        try {
            if (cookie_vorhanden) {
               for(Warenkorb b: koerbe){
                   if(b.getUuid().equals(cook.getValue())){
                       if(produkt_id!=null) {
                           b.addprodukt(Integer.parseInt(produkt_id));
                       }
                        writer.print(tabelle_anfang);
                       if(b.getProdukt_id().size()==1){
                           writer.print("<td style=\"text-align: center\">Es ist 1 Produkt im Warenkorb</td>");
                       }else {
                           writer.print("<td style=\"text-align: center\">Es sind " + b.getProdukt_id().size() + " Produkte im Warenkorb</td>");
                       }
                        double summe=0.00;
                       for(Integer produkt: b.getProdukt_id()){
                           produkt_ps = DB_Connector.con.prepareStatement(produkt_string);
                           produkt_ps.setInt(1,produkt);
                           produkt_rs = produkt_ps.executeQuery();
                           produkt_rs.next();
                           String name= produkt_rs.getString("name");
                           String bezeichnung = produkt_rs.getString("bezeichnung");
                           Double mietzins = produkt_rs.getDouble("mietzins");
                           summe+=mietzins;
                           String mietzins_string = formatdouble(mietzins);
                           writer.print("<tr><td style=\"width:33%; align:center; border:solid 1px #000000\"><table style=\"width:100%\"><th colspan=\"2\" align=left>"+name+"</th><tr><td style=\" max-width: 200px;\">Bezeichnung:</td><td>"+bezeichnung+"</td><td align=right>"+mietzins_string+"</td></tr></table></td></tr>");
                       }
                       String summe_string = formatdouble(summe);
                       writer.print("<tr><td><table style=\"width:100%\"><td>Summe</td><td align=\"right\">"+summe_string+"</td></table></td></tr>");
                       writer.print("</td></tr></table>");
                       writer.print("<table border=0 width\"100%\"><tr><td width=\"90%\"></td><td><form action=\"/Bestellung\" method=\"post\"><input type=submit value=\"Kostenpflichtig bestellen\" name=\"Registrieren\"");
                       if(b.getProdukt_id().size()==0){
                           writer.print("disabled");
                       }
                       writer.print("></form></td><td><form action=\"/clear\" method=\"post\"><input type=submit value=\"Warenkorb leeren\" name=\"clear\"></form></td></tr></table> ");
                       writer.print("</td>");
                   }
               }
            } else {
                if(produkt_id==null){
                   writer.print(tabelle_anfang);
                    writer.print("<th style=\"text-align: center\">Es sind keine Produkte ausgewaehlt.</th>");
                    writer.print("</table>");
                }else{
                    //Fall, falls keine Cookie gefunden wurde.
                }
            }
        }catch(IOException e){

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void getKategorieArtikel(JspWriter writer,String kat_id){
        DB_Connector.connecttoDatabase();

        String kategorien_string = "Select id,name FROM kategorie WHERE oberkategorie=? Order BY id";
        PreparedStatement kategorien_ps = null;
        ResultSet kategorien_rs;
        String produkte_string = "SELECT id,name, bezeichnung, mietzins FROM produkt WHERE Kategorieid=? ORDER BY id";
        PreparedStatement produkte_ps = null;
        ResultSet produkte_rs;

        try {
            kategorien_ps = DB_Connector.con.prepareStatement(kategorien_string);
            kategorien_ps.setInt(1,Integer.parseInt(kat_id));
            kategorien_rs = kategorien_ps.executeQuery();
            while(kategorien_rs.next()){

                int kategorie_id = kategorien_rs.getInt("id");
                String kategorie_name = kategorien_rs.getString("name");
                produkte_ps = DB_Connector.con.prepareStatement(produkte_string);
                produkte_ps.setInt(1,kategorie_id);
                produkte_rs = produkte_ps.executeQuery();

                int zaehler=0;
                String kat_name =".";
                for(int i=0;i<5;i++){
                    writer.print("<tr>\n");
                    if(!kat_name.equals(kategorie_name)){
                        writer.print("<td><b>"+kategorie_name+"</b></td></tr><tr>\n");
                        kat_name=kategorie_name;
                    }

                    for(int ii=0;ii<3;ii++){
                        if(produkte_rs.next()) {
                            int id = produkte_rs.getInt("id");
                            String name = produkte_rs.getString("name");
                            String bezeichnung = produkte_rs.getString("bezeichnung");
                            Double mietzins = produkte_rs.getDouble("mietzins");
                            String mietzins_string = formatdouble(mietzins);
                            writer.print("<td onmouseover=this.style.background=\"#FCFD7A\" onmouseout=this.style.background=\"#FCFD5A\" style=\"width:33%; align:center; border:solid 1px #000000\" onclick=self.location.href=\"./artikel.jsp?id=" + id + "\">");
                            writer.print("<table style=\"max-width:100%\" border=0 ><tr><td colspan=\"2\">" +
                                    name +
                                    "</td></tr><tr><td rowspan=\"2\" style=\" min-width:30pt; max-width:30pt; min-height:30pt ; max-height:30pt\">" +
                                    "Bild" +
                                    "</td><td>" +
                                    bezeichnung +
                                    "</td></tr><tr><td>" +
                                    mietzins_string + "" +
                                    "</td></tr></table>");

                            writer.print("</td>\n");
                            zaehler++;
                        }
                    }
                    writer.print("</tr>\n");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void getBestellung(JspWriter writer, Cookie[] cookies){
        try {
            boolean cookie_vorhanden=false;
            Cookie cook=null;
            if(cookies!=null){
                for(int i=0;i<cookies.length;i++){
                    Cookie c = cookies[i];
                    if(c.getName().compareTo("id")==0){
                        cook = c;
                        cookie_vorhanden=true;
                        break;
                    }else{
                    }
                }
            }else{

            }
            if(cookie_vorhanden){
                writer.print("<td>");
                Kunde k = getKunde(cook.getValue());
                String vorname=" ", nachname=" ", strasse=" ",  plz=" ", ort=" ", telefon=" ", handy=" ", email = " ";
                int hausnummer=0;
                if((k.getVorname()==null)||(k.getVorname().isEmpty())){
                    String kunde_string = "SELECT nutzer.vorname, nutzer.nachname, kunde.strasse, kunde.hausnummer, kunde.plz, kunde.ort, kunde.telefonnummer, kunde.handynummer, kunde.email FROM kunde Inner join nutzer on kunde.Nutzerid=nutzer.id WHERE kunde.Nutzerid=?";
                    PreparedStatement kunde_ps = null;
                    ResultSet kunde_rs;
                    kunde_ps = DB_Connector.con.prepareStatement(kunde_string);
                    kunde_ps.setString(1, k.getId());
                    kunde_rs = kunde_ps.executeQuery();
                    kunde_rs.next();
                     vorname = kunde_rs.getString("vorname");
                     nachname = kunde_rs.getString("nachname");
                     strasse = kunde_rs.getString("strasse");
                     hausnummer = kunde_rs.getInt("hausnummer");
                     plz = kunde_rs.getString("plz");
                     ort = kunde_rs.getString("Ort");
                     telefon = kunde_rs.getString("telefonnummer");
                     handy = kunde_rs.getString("handynummer");
                     email = kunde_rs.getString("email");
                    k.setAddr(new Adresse(strasse,ort, plz, hausnummer));
                    k.setEmail(email);
                    k.setTelefon(telefon);
                    k.setHandy(handy);
                    k.setVorname(vorname);
                    k.setNachname(nachname);
                }
                writer.print("<form action=\"/Bestelleintragung\" method=\"post\"><table width=100%><tr><td>Vorname</td><td><input type=\"text\" name=\"Vorname\" value=\""+vorname+"\"></td><td rowspan=\"12\" valign=\"top\">"+getWarenkorbTabelle(k.getUuid())+"</td></tr>");
                writer.print("<tr><td>Nachname</td><td><input type=text name=Nachname value="+nachname+"></td></tr>");
                writer.print("<tr><td>Stra&#223;e</td><td><input type=text name=Strasse value="+strasse+"></td></tr>");
                writer.print("<tr><td>Hausnummer</td><td><input type=text name=Hausnummer value="+hausnummer+"></td></tr>");
                writer.print("<tr><td>PLZ</td><td><input type=text name=plz value="+plz+"></td></tr>");
                writer.print("<tr><td>Ort</td><td><input type=text name=ort value="+ort+"></td></tr>");
                writer.print("<tr><td>Telefon</td><td><input type=text name=telefon value="+telefon+"></td></tr>");
                writer.print("<tr><td>Handy</td><td><input type=text name=handy value="+handy+"></td></tr>");
                writer.print("<tr><td>E-Mail</td><td><input type=text name=email value="+email+"></td></tr>");
                writer.print("<tr><td>Von</td><td><input type=\"Text\"  id=\"von\"/><img src=\"../img/calender/cal.gif\" onclick=\"javascript:NewCssCal('von','ddMMyyyy','arrow', 'true', '24')\" style=\"cursor:pointer\"/></td></tr>");
                writer.print("<tr><td>Bis</td><td><input type=\"Text\"  id=\"bis\"/><img src=\"../img/calender/cal.gif\" onclick=\"javascript:NewCssCal('bis','ddMMyyyy','arrow', 'true', '24')\" style=\"cursor:pointer\"/>      </td></tr>");
                writer.print("<tr><td colspan=2><input type=submit value=\"Kostenpflichtig bestellen\" name=\"bestellen\"><input type=submit value=\"abbrechen\"></td></tr>");
                writer.print("</table></form>");
            }
            writer.print("</td>");
        }catch(IOException e){

        }catch(SQLException e1){
            try{writer.print(e1);}catch(IOException e2){}
        }
    }

    // Interne Funktionen
    private static  String formatdouble(Double d){
        DecimalFormat format = new DecimalFormat("#####0.00");
        return format.format(d)+"&#8364";
    }
    private static Kunde getKunde(String uuid){
        for(Kunde k: kunde){
            if(k.getUuid().equals(uuid)){
                return k;
            }
        }
        return null;
    }
    private static String getWarenkorbTabelle(String uuid){

        DB_Connector.connecttoDatabase();
        String produkt_string = "SELECT name, bezeichnung, mietzins  FROM produkt WHERE id=?";
        PreparedStatement produkt_ps = null;
        String tabelle_anfang= "<table border=0 style=\"width:100%\">";
        ResultSet produkt_rs;
        StringBuffer writer=new StringBuffer();

        try {
                for(Warenkorb b: koerbe){
                    if(b.getUuid().equals(uuid)){

                        writer.append(tabelle_anfang);
                        writer.append("<td style=\"text-align: center\">Produkte</td>");

                        double summe=0.00;
                        for(Integer produkt: b.getProdukt_id()){
                            produkt_ps = DB_Connector.con.prepareStatement(produkt_string);
                            produkt_ps.setInt(1,produkt);
                            produkt_rs = produkt_ps.executeQuery();
                            produkt_rs.next();
                            String name= produkt_rs.getString("name");
                            String bezeichnung = produkt_rs.getString("bezeichnung");
                            Double mietzins = produkt_rs.getDouble("mietzins");
                            summe+=mietzins;
                            String mietzins_string = formatdouble(mietzins);
                            writer.append("<tr><td style=\"width:33%; align:center; border:solid 1px #000000\"><table style=\"width:100%\"><th colspan=\"2\" align=left>"+name+"</th><tr><td style=\" max-width: 200px;\">Bezeichnung:</td><td>"+bezeichnung+"</td><td align=right>"+mietzins_string+"</td></tr></table></td></tr>");
                        }
                        String summe_string = formatdouble(summe);
                        writer.append("<tr><td><table style=\"width:100%\"><td>Summe</td><td align=\"right\">"+summe_string+"</td></table></td></tr>");
                        writer.append("</td></tr></table>");
                    }
                }


        }catch (SQLException e) {
            e.printStackTrace();
        }
        return writer.toString();
    }



}
