package wak.objects;

import wak.system.db.DB_Connector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;

public class Produkt {
    String name, bezeichnung, beschreibung, herstellername, details;
    double mietzins;
    Produkt alternative;
    Kategorie kategorie;
    String[] bilder;
    Geraet[] geraete;
    int id;

    public Produkt(String name, double mietzins, Kategorie kategorie) {
        this.name = name;
        this.mietzins = mietzins;
        this.kategorie = kategorie;
        this.id = generiereID();
        produkt_eintragen(this);
    }

    public int getId() {
        return id;
    }

    int generiereID(){
        int produkt_id=0;
        String produkt_id_query = ("SELECT id from produkt order by id DESC");
        try {
            PreparedStatement produkt_id_result = DB_Connector.con.prepareStatement(produkt_id_query);
            ResultSet produkt_id_set = produkt_id_result.executeQuery();
            produkt_id_set.next();
            produkt_id = produkt_id_set.getInt("id")+1;
            System.out.println(produkt_id);
        }catch(SQLException e){
            System.out.println("Fehler beim generieren einer ProduktID");
        }
        return produkt_id;
    }

    public void setId(int id) {
        //Generieren einer Produkt ID

        this.id = id;
    }

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

    @Override
    public String toString() {
        return "Produkt{" +
                "name='" + name + '\'' +
                ", bezeichnung='" + bezeichnung + '\'' +
                ", beschreibung='" + beschreibung + '\'' +
                ", herstellername='" + herstellername + '\'' +
                ", details='" + details + '\'' +
                ", mietzins=" + mietzins +
                ", alternative=" + alternative +
                ", kategorie=" + kategorie +
                ", bilder=" + Arrays.toString(bilder) +
                ", geraete=" + Arrays.toString(geraete) +
                ", id=" + id +
                '}';
    }

    public void produkt_eintragen(Produkt p){
        String einfuegen = "INSERT INTO produkt (id,name, bezeichnung, hersteller_name, beschreibung, details, mietzins, Kategorieid, alternative)" + "VALUES (?,?, ?, ?, ?, ?, ?,(select id from kategorie WHERE kategorie.id=?),?)";
        PreparedStatement bestellung = null;
        //Vorbereiten der Bestellung für die Datenbank
        try {
            bestellung = DB_Connector.con.prepareStatement(einfuegen);
            bestellung.setInt(1, generiereID());
            bestellung.setString(2, p.getName());
            bestellung.setString(3, p.getBezeichnung());
            bestellung.setString(4, p.getHerstellername());
            bestellung.setString(5, p.getBeschreibung());
            bestellung.setString(6, p.getDetails());
            bestellung.setDouble(7, p.getMietzins());
            bestellung.setInt(8, p.getKategorie().getId());
            if(p.getAlternative()!=null){
                bestellung.setInt(9, p.getAlternative().getId());
            }else{
                bestellung.setNull(9, Types.INTEGER);
            }

            System.out.println(bestellung.toString());
            bestellung.executeUpdate();
        }catch(SQLException e){
            System.out.println("Fehler bei der Produkteintragung");
            bestellung.toString();
            e.printStackTrace();
        }


    }
}
