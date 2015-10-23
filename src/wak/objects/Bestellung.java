package wak.objects;

import wak.system.db.DB_Connector;
import wak.user.Kunde;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    int id;

    public Bestellung (Kunde kunde, ArrayList<Produkt> Produkte, Timestamp von, Timestamp bis){
        this.kunde =kunde;
        this.Position = Produkte;
        this.von = von;
        this.bis = bis;
        this.bestellungdatum = Timestamp.valueOf(LocalDateTime.now());
        Bestellung_eintragen(this);
    }

    private boolean ueberschneidet(Bestellung b){
        String query = "SELECT bestellung.von, bestellung.bis FROM bestellung INNER JOIN bestellposition ON bestellposition.bestellungid = bestellung.id WHERE bestellposition.Produktid=?";
        try {
        PreparedStatement bestellung = DB_Connector.con.prepareStatement(query);
        bestellung.setInt(1, b.Position.get(0).getId());
           ResultSet rs =  bestellung.executeQuery();
            while (rs.next()) {
                Timestamp von = rs.getTimestamp("von");
                Timestamp bis = rs.getTimestamp("bis");

                if(this.bis.after(von) | this.bis.equals(von)){
                    if(this.bis.before(bis)){
                        return true;
                    }

                }else{
                }
            }
            return false;

        }
        catch (SQLException e){
            System.out.println("Problem bei der Datenbankabfragenverarbeitung");
        }finally{
            return true;
        }
    }
    private void Bestellung_eintragen(Bestellung b){
        String einfuegen = "INSERT INTO bestellung (Nutzerid, von, bis, genehmigt)" + "VALUES ((select Nutzerid from kunde WHERE kunde.Nutzerid=?),?,?,?)";
        String position = "Insert INTO bestellposition (Bestellungid,Produktid,position,ProduktKategorieid) "+"VALUES(?,(select id from produkt WHERE id=?),?,(select id from kategorie WHERE id=?))";
        try {


            //Vorbereiten der Bestellung für die Datenbank
            PreparedStatement bestellung = DB_Connector.con.prepareStatement(einfuegen);
            bestellung.setString(1, kunde.getId());
            bestellung.setTimestamp(2, b.von);
            bestellung.setTimestamp(3, b.bis);
            bestellung.setInt(4,0);
            System.out.println(bestellung.toString());
            bestellung.executeUpdate();

            //Ermitteln der gegebenen ID
            PreparedStatement id = DB_Connector.con.prepareStatement("SELECT id FROM bestellung WHERE id = ( SELECT max(id) FROM bestellung )");
            ResultSet id_rs = id.executeQuery();
            id_rs.next();
            int bestell_id=id_rs.getInt("id");

            //Einfügen der Bestellpositionen
            int i = 1;
            for(Produkt p:b.getPosition()) {
                PreparedStatement bestellposition = DB_Connector.con.prepareStatement(position);
                bestellposition.setInt(1, bestell_id);
                bestellposition.setInt(2, p.getId());
                bestellposition.setInt(3, i);
                bestellposition.setInt(4, p.getKategorie().getId());
                bestellposition.executeUpdate();
                i++;
        }
        }catch(SQLException e){
            System.out.println("fehler beim Eintragen der Bestellung");
            e.printStackTrace();

        }
    }

    public Kunde getKunde() {
        return kunde;
    }

    public void setKunde(Kunde kunde) {
        this.kunde = kunde;
    }

    public ArrayList<Produkt> getPosition() {
        return Position;
    }

    public void setPosition(ArrayList<Produkt> position) {
        Position = position;
    }

    public Timestamp getVon() {
        return von;
    }

    public void setVon(Timestamp von) {
        this.von = von;
    }

    public Timestamp getBis() {
        return bis;
    }

    public void setBis(Timestamp bis) {
        this.bis = bis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
