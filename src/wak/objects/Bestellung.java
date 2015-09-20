package wak.objects;

import wak.user.Kunde;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by Christoph Nebendahl on 20.09.2015.
 */
public class Bestellung {
    Kunde kunde;
    ArrayList<Produkt> Position;
    Timestamp von, bis, bestellungdatum;


}
