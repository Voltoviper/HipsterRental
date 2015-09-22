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

        if(true){
           String einfuegen = "INSERT INTO bestellung (id,Kundeid, von, bis)" + "VALUES (?,(select id from kunde WHERE kunde.id='1'),?,?)";
            try {
                String bestellung_id_String = "SELECT id FROM softwareengineering2.bestellung order by id DESC;";
                PreparedStatement bestellung_id = DB_Connector.con.prepareStatement(bestellung_id_String);
                PreparedStatement bestellung = DB_Connector.con.prepareStatement(einfuegen);
                ResultSet bestellung_id_set = bestellung_id.executeQuery();
                bestellung_id_set.next();
                bestellung.setInt(1, bestellung_id_set.getInt("id")+1);
                bestellung.setTimestamp(2,von);
                bestellung.setTimestamp(3, bis);
                System.out.println(bestellung.toString());
                bestellung.executeUpdate();
            }catch(SQLException e){
                System.out.println("fehler beim Eintragen der Bestellung");
                e.printStackTrace();
            }
        }
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
}
