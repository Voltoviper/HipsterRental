package wak.system.server;

import wak.objects.Bestellung;
import wak.objects.Produkt;
import wak.objects.Warenkorb;
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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Christoph Nebendahl on 13.10.2015.
 * @author Christoph Nebendahl
 */
@WebServlet(name = "Bestelleintragung")
public class Bestelleintragung extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String weiterleitung = "/index.jsp";
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
            Kunde k = null;
            for(Kunde kunde:Seitenaufbau.kunde) {
                try {
                    if (kunde.getUuid().equals(cook.getValue())) {
                        k = kunde;
                        //Pr�fen, ob die Daten ge�ndert wurden
                        if (!(k.getVorname().equals(request.getParameter("vorname")))) {
                            k.setVorname(request.getParameter("vorname"));
                        }
                        if (!(k.getNachname().equals(request.getParameter("nachname")))) {
                            k.setNachname(request.getParameter("nachname"));
                        }
                        if (!(k.getAddr().getStrasse().equals(request.getParameter("strasse")))) {
                            k.getAddr().setStrasse(request.getParameter("strasse"));
                        }
                        if (!(k.getAddr().getHausnummer() == Integer.parseInt(request.getParameter("hausnummer")))) {
                            k.getAddr().setHausnummer(Integer.parseInt(request.getParameter("hausnummer")));
                        }
                        if (!(k.getAddr().getPlz().equals(request.getParameter("plz")))) {
                            k.getAddr().setPlz(request.getParameter("plz"));
                        }
                        if (!(k.getAddr().getOrt().equals(request.getParameter("ort")))) {
                            k.getAddr().setOrt(request.getParameter("ort"));
                        }
                        if (!(k.getTelefon().equals(request.getParameter("telefon")))) {
                            k.setTelefon(request.getParameter("telefon"));
                        }
                        if (!(k.getHandy().equals(request.getParameter("handy")))) {
                            k.setHandy((request.getParameter("handy")));
                        }
                        if (!(k.getEmail().equals(request.getParameter("email")))) {
                            k.setEmail(request.getParameter("email"));
                        }
                        break;
                    }
                }catch(NullPointerException e1){
                }
            }
            String vorname = request.getParameter("vorname");
            String nachname = request.getParameter("nachname");
            String email = request.getParameter("email");
            String telefon = request.getParameter("telefon");
            String handy =request.getParameter("handy");
            String strasse = request.getParameter("strasse");
            int hausnummer = Integer.parseInt(request.getParameter("hausnummer"));
            String plz = request.getParameter("plz");
            String ort =  request.getParameter("ort");
            String uuid = cook.getValue();
            if(k==null){
                k = new Kunde(null, vorname, nachname, email, telefon, handy, strasse, hausnummer, plz, ort, uuid, true);
                for(Warenkorb w:Seitenaufbau.koerbe){
                    if(w.getUuid().equals(uuid)){
                        k.setKorb(w);
                        break;
                    }
                }
            }




            ArrayList<Produkt> produkte  = new ArrayList<Produkt>();
            for(int i:k.getKorb().getProdukt_id()){
                for(Produkt p: Seitenaufbau.katalog){
                    if(p.getId()==i){
                        produkte.add(p);
                    }
                }
            }
            String von = request.getParameter("von");
            String bis = request.getParameter("bis");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy' 'HH:mm", Locale.GERMANY);
            LocalDateTime von_date = LocalDateTime.parse(von, formatter);
            LocalDateTime bis_date = LocalDateTime.parse(bis, formatter);

            try{
                if((von_date.getHour()<13)||(bis_date.getHour()<13)||(von_date.getHour()>17)||(bis_date.getHour()>17)){
                    throw new Exception("Außerhalb der Geschäftzeiten");
                }

                DB_Connector.connecttoDatabase();
                String feiertag ="SELECT sum((case when (datum like ? or datum like ?)  THEN 1 ELSE 0 END)) as feiertag FROM feiertage;";
                PreparedStatement feiertag_ps = DB_Connector.con.prepareStatement(feiertag);
                LocalDate von_feiertag = LocalDate.parse(von, formatter);
                LocalDate bis_feiertag = LocalDate.parse(von, formatter);
                feiertag_ps.setDate(1, Date.valueOf(von_feiertag));
                feiertag_ps.setDate(2,Date.valueOf(bis_feiertag));
                ResultSet feiertag_rs = feiertag_ps.executeQuery();
                feiertag_rs.next();
                if(feiertag_rs.getInt("feiertag")>0){
                    throw new Exception("Außerhalb der Geschäftzeiten");
                }

                Bestellung b = new Bestellung(k, produkte, Timestamp.valueOf(von_date), Timestamp.valueOf(bis_date));
                Session session = emailservice.getSession();
                emailservice.sendZusammenfassung(session, k, b);
                weiterleitung = "/index.jsp";
            }catch(Exception e1){
                weiterleitung = "/jsp/error.jsp";
                e1.printStackTrace();
            }

        }
        RequestDispatcher d = getServletContext().getRequestDispatcher(weiterleitung);
        d.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
