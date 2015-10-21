package wak;

import wak.objects.Bestellung;
import wak.objects.Kategorie;
import wak.objects.Produkt;
import wak.system.email.emailservice;
import wak.user.Kunde;

import javax.mail.MessagingException;
import javax.mail.Session;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by chris_000 on 21.10.2015.
 */
public class main {

    public static void main(String[] args){

       Session session = emailservice.getGMailSession();
        Produkt p1 = new Produkt("Verstärker", 70.0, new Kategorie("Verstärker", null, 1));
        UUID uuid = UUID.randomUUID();
        ArrayList<Produkt> liste = new ArrayList<Produkt>();
        liste.add(p1);
        Kunde k = new Kunde("K00000001", uuid);
        k.setEmail("christoph@mail-nebendahl.de");
        Bestellung b = new Bestellung(k, liste, new Timestamp(1445419482), new Timestamp(1445419482));
        emailservice.sendzusage(session, k, b );

    }
}
