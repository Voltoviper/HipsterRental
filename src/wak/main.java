package wak;

import wak.objects.Bestellung;
import wak.objects.Kategorie;
import wak.objects.Produkt;
import wak.system.email.emailservice;
import wak.user.Kunde;

import javax.mail.Session;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by chris_000 on 21.10.2015.
 */
public class main {

    public static void main(String[] args){

       Session session = emailservice.getSession();
        Produkt p1 = new Produkt("Verstärker", 70.0, new Kategorie("Verstärker", null, 1));
        UUID uuid = UUID.randomUUID();
        ArrayList<Produkt> liste = new ArrayList<Produkt>();
        liste.add(p1);
        Kunde k = new Kunde("K00000001", uuid);
        k.setNachname("Lücke");
        k.setVorname("Christian");
        k.setEmail("christoph@mail-nebendahl.de");
        try {
            Bestellung b = new Bestellung(k, liste, new Timestamp(1445419482), new Timestamp(1445419482));
            emailservice.sendgenehmigung(session, k, b, true);
            emailservice.sendgenehmigung(session, k, b, false);
            emailservice.sendZusammenfassung(session, k, b);
        }catch(Exception e1){
            e1.getMessage();
        }

    }
}
