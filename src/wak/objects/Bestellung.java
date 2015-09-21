package wak.objects;

import wak.user.Kunde;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Created by Christoph Nebendahl on 20.09.2015.
 */
public class Bestellung {
    Kunde kunde;
    ArrayList<Produkt> Position;
    Timestamp von, bis, bestellungdatum;

    public Bestellung (Kunde kunde, ArrayList<Produkt> Produkte, Timestamp von, Timestamp bis){
        this.kunde =kunde;
        this.Position = Produkte;
        this.von = von;
        this.bis = bis;
        this.bestellungdatum = Timestamp.valueOf(LocalDateTime.now());
    }
}
