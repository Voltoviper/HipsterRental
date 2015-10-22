package wak.system.server;

import wak.objects.Bestellung;
import wak.objects.Produkt;
import wak.objects.Warenkorb;
import wak.user.Kunde;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Christoph Nebendahl on 13.10.2015.
 * @author Christoph Nebendahl
 */
@WebServlet(name = "Bestelleintragung")
public class Bestelleintragung extends HttpServlet {
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
                    //Prüfen, ob die Daten geändert wurden
                    if(!(k.getVorname().equals(request.getParameter("vorname")))){

                    }
                    if(!(k.getNachname().equals(request.getParameter("nachname")))){

                    }
                    if(!(k.getAddr().getStrasse().equals(request.getParameter("strasse")))){

                    }
                    if(!(k.getAddr().getHausnummer()== Integer.parseInt(request.getParameter("hausnummer")))){

                    }
                    if(!(k.getAddr().getPlz().equals(request.getParameter("plz")))){

                    }
                    if(!(k.getAddr().getOrt().equals(request.getParameter("ort")))){

                    }
                    if(!(k.getTelefon().equals(request.getParameter("telefon")))){

                    }
                    if(!(k.getHandy().equals(request.getParameter("handy")))){

                    }
                    if(!(k.getEmail().equals(request.getParameter("email")))){

                    }
                    Warenkorb waren = null;
                    for (Warenkorb w:Seitenaufbau.koerbe){
                        if(w.getUuid().equals(k.getUuid())){
                            waren=w;
                        }
                    }
                    ArrayList<Produkt> produkte  = new ArrayList<Produkt>();
                    for(int i:waren.getProdukt_id()){
                        for(Produkt p: Seitenaufbau.katalog){
                            if(p.getId()==i){
                                produkte.add(p);
                            }
                        }
                    }
                    Bestellung b = new Bestellung(k, produkte, request.getParameter("von"), request.getParameter("bis"));
                }

            }

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
