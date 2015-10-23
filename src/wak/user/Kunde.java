package wak.user;

import wak.objects.Warenkorb;

import java.util.UUID;

/**
 * Created by Christoph Nebendahl on 20.09.2015.
 */
public class Kunde extends Person {
    String email, telefon, organame, handy;
    Adresse addr;
    UUID uuid;
    Warenkorb korb;

    /**
     * Kunde wird angelegt.
     * @param id
     * @param vorname
     * @param nachname
     * @param email
     * @param telefon
     * @param organame
     * @param handy
     * @param strasse
     * @param hausnummer
     * @param plz
     * @param ort
     * @param uuid
     */
    public Kunde(String id, String vorname, String nachname,String email, String telefon, String organame, String handy, String strasse, int hausnummer, String plz, String ort, UUID uuid) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.email = email;
        this.telefon = telefon;
        this.organame = organame;
        this.handy = handy;
        this.addr = new Adresse(strasse, ort, plz, hausnummer);
        this.id = id;
        this.uuid = uuid;
    }

    /**
     * Kunde wird nur für die Zuordnung von SessionID und Kundennummer angelegt. Es muss später erweitert werden!
     * @param id
     * @param uuid
     */
    public Kunde(String id, UUID uuid){
        this.id=id;
        this.uuid=uuid;
    }

    public Warenkorb getKorb() {
        return korb;
    }

    public void setKorb(Warenkorb korb) {
        this.korb = korb;
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

    public String getUuid(){
        return uuid.toString();
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

    @Override
    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    @Override
    public void setNachname(String nachname) {
        this.nachname= nachname;
    }
}
