package wak.objects;

import java.util.ArrayList;

/**
 * Created by Christoph Nebendahl on 20.09.2015.
 */
public class Paket {
    String name, beschreibung, detail;
    double mietzins;
    Kategorie kategorie;
    ArrayList<Produkt> produkte;

    public Paket(String name, String beschreibung, String detail, double mietzins, Kategorie kategorie,  ArrayList<Produkt> produkte) {
        this.name = name;
        this.beschreibung = beschreibung;
        this.detail = detail;
        this.mietzins = mietzins;
        this.kategorie = kategorie;
        this.produkte = produkte;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public double getMietzins() {
        return mietzins;
    }

    public void setMietzins(double mietzins) {
        this.mietzins = mietzins;
    }

    public Kategorie getKategorie() {
        return kategorie;
    }

    public void setKategorie(Kategorie kategorie) {
        this.kategorie = kategorie;
    }

    public  ArrayList<Produkt> getProdukte() {
        return produkte;
    }

    public void setProdukte( ArrayList<Produkt> produkte) {
        this.produkte = produkte;
    }

    public void addProdukt(Produkt p){
        produkte.add(p);
    }
}
