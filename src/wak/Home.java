package wak;

import wak.system.db.DB_Connector;
import wak.user.Kunde;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by Christoph Nebendahl on 19.09.2015.
 */
public class Home {

    public static void main(String args[]){
       Kunde k = new Kunde("Christoph", "Nebendahl", "christoph@mail-nebendahl","04307-5198","WAK","01573-1655616","Eckernfoerder Str.",61,"24116","Kiel");
        System.out.println(k.getAddr());
       System.out.println(Timestamp.valueOf(LocalDateTime.now()));
        DB_Connector conn = new DB_Connector();
        //conn.updateDatabase("INSERT INTO 'kategorie' ('name', 'bild_pfad') VALUES ('Test','C:Christoph')");
       conn.updateDatabase("INSERT INTO kategorie (name, bild_pfad)" + "VALUES ('Test','C:')");
       ResultSet rs = conn.QueryDatabase("SELECT * FROM kategorie WHERE 1");
        try {
            while(rs.next()){
                String em = rs.getString("id");
                System.out.println(em);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
