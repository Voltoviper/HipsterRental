package wak.system.server;

import wak.objects.Kategorie;
import wak.objects.Paket;
import wak.objects.Produkt;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Christoph Nebendahl on 08.11.2015.
 */
@WebServlet(name = "Paketeintragen")
@MultipartConfig
public class Paketeintragen extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Kategorie kat = null;
        String name = request.getParameter("name");
        String bezeichnung = request.getParameter("bezeichnung");
        String hersteller = request.getParameter("hersteller");
        String beschreibung = request.getParameter("beschreibung");
        String details = request.getParameter("details");
        double mietzins= Double.parseDouble(request.getParameter("mietzins"));
        String Kategorie_name = request.getParameter("kategorie");


        Part part = request.getPart("bild");
        InputStream fileContent = part.getInputStream();

        Produkt[][] produkte = new Produkt[300][300];
        for (int i=0; i<20;i++){
            if(!request.getParameter("produktid_"+i).equals(" ")) {
                produkte[i][0] = getProdukt(Integer.parseInt(request.getParameter("produktid_" + i)));
                produkte[i][1] = getProdukt(Integer.parseInt(request.getParameter("alt1_" + i)));
                produkte[i][2] = getProdukt(Integer.parseInt(request.getParameter("alt2_" + i)));
                produkte[i][3] = getProdukt(Integer.parseInt(request.getParameter("alt3_" + i)));
            }else{
                break;
            }
        }


        for(Kategorie k: Seitenaufbau.kategorien){
            if(k.getName().equals(Kategorie_name)){
                kat=k;
                break;
            }
        }

        Paket p = new Paket(name, bezeichnung, beschreibung, hersteller, details, mietzins, kat, produkte,true);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private static Produkt getProdukt(int id){
        Produkt produkt=null;
        for(Produkt p:Seitenaufbau.katalog){
            if(p.getId()==id){
                produkt = p;
                break;
            }
        }
        return produkt;
    }
}
