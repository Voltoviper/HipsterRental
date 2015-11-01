package wak.system.server;

import wak.objects.Bestellung;
import wak.objects.Produkt;
import wak.system.db.DB_Connector;
import wak.system.email.emailservice;
import wak.user.Kunde;

import javax.mail.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Christoph Nebendahl on 01.11.2015.
 */
@WebServlet(name = "ProfilUpdate")
public class ProfilUpdate extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
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
            Kunde kunde = null;
            for(Kunde k:Seitenaufbau.kunde){
                if(k.getUuid().equals(cook.getValue())){
                    kunde = k;
                    //Pr?fen, ob die Daten ge?ndert wurden
                    if(!(k.getVorname().equals(request.getParameter("vorname")))){
                        k.setVorname(request.getParameter("vorname"));
                    }
                    if(!(k.getNachname().equals(request.getParameter("nachname")))){
                        k.setNachname(request.getParameter("nachname"));
                    }
                    if(!(k.getAddr().getStrasse().equals(request.getParameter("strasse")))){
                        k.getAddr().setStrasse(request.getParameter("strasse"));
                        try {
                            DB_Connector.connecttoDatabase();
                            String dbEmail = "UPDATE `softwareengineering2`.`kunde` SET `strasse`=? WHERE `Nutzeridid`=?;";
                            PreparedStatement dbEmail_ps = DB_Connector.con.prepareStatement(dbEmail);
                            dbEmail_ps.setString(1,request.getParameter("strasse"));
                            dbEmail_ps.setString(2, k.getId());
                            dbEmail_ps.executeUpdate();
                        }catch(SQLException e1){
                            e1.printStackTrace();
                        }finally{
                            DB_Connector.closeDatabase();
                        }
                    }
                    if(!(k.getAddr().getHausnummer()== Integer.parseInt(request.getParameter("hausnummer")))){
                        k.getAddr().setHausnummer(Integer.parseInt(request.getParameter("hausnummer")));
                        try {
                            DB_Connector.connecttoDatabase();
                            String dbEmail = "UPDATE `softwareengineering2`.`kunde` SET `hausnummer`=? WHERE `Nutzerid`=?;";
                            PreparedStatement dbEmail_ps = DB_Connector.con.prepareStatement(dbEmail);
                            dbEmail_ps.setInt(1, Integer.parseInt(request.getParameter("hausnummer")));
                            dbEmail_ps.setString(2, k.getId());
                            dbEmail_ps.executeUpdate();
                        }catch(SQLException e1){
                            e1.printStackTrace();
                        }finally{
                            DB_Connector.closeDatabase();
                        }
                    }
                    if(!(k.getAddr().getPlz().equals(request.getParameter("plz")))){
                        k.getAddr().setPlz(request.getParameter("plz"));
                        try {
                            DB_Connector.connecttoDatabase();
                            String dbEmail = "UPDATE `softwareengineering2`.`kunde` SET `plz`=? WHERE `Nutzerid`=?;";
                            PreparedStatement dbEmail_ps = DB_Connector.con.prepareStatement(dbEmail);
                            dbEmail_ps.setString(1,request.getParameter("plz"));
                            dbEmail_ps.setString(2, k.getId());
                            dbEmail_ps.executeUpdate();
                        }catch(SQLException e1){
                            e1.printStackTrace();
                        }finally{
                            DB_Connector.closeDatabase();
                        }
                    }
                    if(!(k.getAddr().getOrt().equals(request.getParameter("ort")))){
                        k.getAddr().setOrt(request.getParameter("ort"));
                        try {
                            DB_Connector.connecttoDatabase();
                            String dbEmail = "UPDATE `softwareengineering2`.`kunde` SET `ort`=? WHERE `Nutzerid`=?;";
                            PreparedStatement dbEmail_ps = DB_Connector.con.prepareStatement(dbEmail);
                            dbEmail_ps.setString(1,request.getParameter("ort"));
                            dbEmail_ps.setString(2, k.getId());
                            dbEmail_ps.executeUpdate();
                        }catch(SQLException e1){
                            e1.printStackTrace();
                        }finally{
                            DB_Connector.closeDatabase();
                        }
                    }
                    if(!(k.getTelefon().equals(request.getParameter("telefon")))){
                        k.setTelefon(request.getParameter("telefon"));
                    }
                    if(!(k.getHandy().equals(request.getParameter("handy")))){
                        k.setHandy((request.getParameter("handy")));
                    }
                    if(!(k.getEmail().equals(request.getParameter("email")))){
                        k.setEmail(request.getParameter("email"));
                    }



                }

            }

        }
        String dispatcher="/jsp/Profil.jsp";
        RequestDispatcher d = getServletContext().getRequestDispatcher(dispatcher);
        d.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
