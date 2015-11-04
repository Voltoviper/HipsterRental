package wak.objects;

import org.apache.commons.collections4.CollectionUtils;
import wak.system.db.DB_Connector;
import wak.system.server.Seitenaufbau;
import wak.user.Kunde;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

/**
 * Created by Christoph Nebendahl on 20.09.2015.
 */
public class Bestellung {
    Kunde kunde;
    ArrayList<Produkt> Position;
    Timestamp von, bis, bestellungdatum;
    int id;

    public Bestellung (Kunde kunde, ArrayList<Produkt> Produkte, Timestamp von, Timestamp bis)throws Exception{
        this.kunde =kunde;
        this.Position = Produkte;
        this.von = von;
        this.bis = bis;
        this.bestellungdatum = Timestamp.valueOf(LocalDateTime.now());
        if(ueberschneidet(this)) {
            Bestellung_eintragen(this);
        }else{
            throw new Exception("Fehler! Bestellung konnte nicht eingetragen werden!");
        }
    }

    public boolean ueberschneidet(Bestellung b){
        DB_Connector.connecttoDatabase();
        boolean moeglich=true;
        String bestellung = "SELECT verfuegbar(?,?,?,?) AS possible;";

        Collections.sort(this.Position);
        int zaehler=0;
        Map m = CollectionUtils.getCardinalityMap(this.getPosition());
        try {
            for (Produkt p : Seitenaufbau.katalog) {
                if(CollectionUtils.cardinality(p, this.getPosition())!=0) {
                    PreparedStatement bestellung_ps = DB_Connector.con.prepareStatement(bestellung);
                    bestellung_ps.setInt(1, p.getId());
                    bestellung_ps.setInt(2, CollectionUtils.cardinality(p, this.getPosition()));
                    bestellung_ps.setTimestamp(3, b.getVon());
                    bestellung_ps.setTimestamp(4, b.getBis());
                    ResultSet bestellung_rs = bestellung_ps.executeQuery();
                    bestellung_rs.next();
                    if(bestellung_rs.getBoolean("possible")){
                        moeglich = true;
                    }else{
                        moeglich = false;
                        break;
                    }
                }
            }
        }catch(SQLException e1){
            e1.printStackTrace();
        }finally{
            DB_Connector.closeDatabase();
        }
       return moeglich;
    }
    private void Bestellung_eintragen(Bestellung b){
        String einfuegen = "INSERT INTO bestellung (Nutzerid, von, bis, genehmigt)" + "VALUES ((select Nutzerid from kunde WHERE kunde.Nutzerid=?),?,?,?)";
        String position = "Insert INTO bestellposition (Bestellungid,Produktid,position) "+"VALUES(?,(select id from produkt WHERE id=?),?)";
        try {


            //Vorbereiten der Bestellung für die Datenbank
            DB_Connector.connecttoDatabase();
            PreparedStatement bestellung = DB_Connector.con.prepareStatement(einfuegen);
            bestellung.setString(1, kunde.getId());
            bestellung.setTimestamp(2, b.von);
            bestellung.setTimestamp(3, b.bis);
            bestellung.setInt(4, 0);
            bestellung.executeUpdate();
            DB_Connector.closeDatabase();

            //Ermitteln der gegebenen ID
            DB_Connector.connecttoDatabase();
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
                System.out.println(bestellposition.toString());
                bestellposition.executeUpdate();
                i++;
            }
        }catch(SQLException e){
            System.out.println("fehler beim Eintragen der Bestellung");
            e.printStackTrace();

        }finally {
            DB_Connector.closeDatabase();
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
