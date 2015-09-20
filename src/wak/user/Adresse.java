package wak.user;

/**
 * Created by Christoph Nebendahl on 20.09.2015.
 */
public class Adresse {
    String strasse, ort, plz;
    int hausnummer;

    public Adresse(String strasse, String ort, String plz, int hausnummer) {
        this.strasse = strasse;
        this.ort = ort;
        this.plz = plz;
        this.hausnummer = hausnummer;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public int getHausnummer() {
        return hausnummer;
    }

    public void setHausnummer(int hausnummer) {
        this.hausnummer = hausnummer;
    }

    @Override
    public String toString() {
        return strasse + ' ' + hausnummer + '/' + plz + ' ' + ort;
    }
}
