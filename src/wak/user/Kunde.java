package wak.user;

/**
 * Created by Christoph Nebendahl on 20.09.2015.
 */
public class Kunde extends Person {
    String email, telefon, organame, handy;
    Adresse addr;

    public Kunde(String id, String vorname, String nachname,String email, String telefon, String organame, String handy, String strasse, int hausnummer, String plz, String ort) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.email = email;
        this.telefon = telefon;
        this.organame = organame;
        this.handy = handy;
        this.addr = new Adresse(strasse, ort, plz, hausnummer);
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getOrganame() {
        return organame;
    }

    public void setOrganame(String organame) {
        this.organame = organame;
    }

    public String getHandy() {
        return handy;
    }

    public void setHandy(String handy) {
        this.handy = handy;
    }

    public Adresse getAddr() {
        return addr;
    }

    public void setAddr(Adresse addr) {
        this.addr = addr;
    }

    @Override
    public String getVorname() {
        return vorname;
    }

    @Override
    public String getNachname() {
        return nachname;
    }

    @Override
    public String getId() {
        return id;
    }
}
