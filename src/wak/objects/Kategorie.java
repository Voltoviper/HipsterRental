package wak.objects;

/**
 * Created by Christoph Nebendahl on 20.09.2015.
 */
public class Kategorie {
    String name, bild_pfad;
    Kategorie oberkategorie;
    int id;


    public Kategorie(String name, String bild_pfad, int id) {
        this.name = name;
        this.bild_pfad = bild_pfad;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBild_pfad() {
        return bild_pfad;
    }

    public void setBild_pfad(String bild_pfad) {
        this.bild_pfad = bild_pfad;
    }

    public Kategorie getOberkategorie() {
        return oberkategorie;
    }

    public void setOberkategorie(Kategorie oberkategorie) {
        this.oberkategorie = oberkategorie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
