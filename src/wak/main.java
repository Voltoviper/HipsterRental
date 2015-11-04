package wak;

import wak.objects.Bestellung;
import wak.objects.Kategorie;
import wak.objects.Produkt;
import wak.system.email.emailservice;
import wak.user.Kunde;

import javax.mail.Session;
import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by chris_000 on 21.10.2015.
 */
public class main {

    public static void main(String[] args) {

        File directory = new File("web/img/produkte/main.jpg");
        System.out.println(directory.getParentFile().mkdirs());




    }
}
