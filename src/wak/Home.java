package wak;

import wak.objects.Bestellung;
import wak.system.db.DB_Connector;
import wak.user.Kunde;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by Christoph Nebendahl on 19.09.2015.
 */
public class Home {

    public static void main(String args[]){
        DB_Connector.connecttoDatabase();
       Kunde k = new Kunde("1","Christoph", "Nebendahl", "christoph@mail-nebendahl","04307-5198","WAK","01573-1655616","Eckernfoerder Str.",61,"24116","Kiel");
        System.out.println(k.getAddr());
       System.out.println(Timestamp.valueOf(LocalDateTime.now()));
        Bestellung b = new Bestellung(k, null, Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()));

    }
}
