package wak.objects;

/**
 * Created by Christoph Nebendahl on 20.09.2015.
 */
public class Produkt {
    String name, bezeichnung, beschreibung, herstellername, details;
    double mietzins;
    Produkt alternative;
    Kategorie kategorie;
    String [] bilder;
    Geraet[] geraete;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getHerstellername() {
        return herstellername;
    }

    public void setHerstellername(String herstellername) {
        this.herstellername = herstellername;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public double getMietzins() {
        return mietzins;
    }

    public void setMietzins(double mietzins) {
        this.mietzins = mietzins;
    }

    public Produkt getAlternative() {
        return alternative;
    }

    public void setAlternative(Produkt alternative) {
        this.alternative = alternative;
    }

    public Kategorie getKategorie() {
        return kategorie;
    }

    public void setKategorie(Kategorie kategorie) {
        this.kategorie = kategorie;
    }

    public String[] getBilder() {
        return bilder;
    }

    public void setBilder(String[] bilder) {
        this.bilder = bilder;
    }

    public Geraet[] getGeraete() {
        return geraete;
    }

    public void setGeraete(Geraet[] geraete) {
        this.geraete = geraete;
    }
}
