package wak;

import wak.objects.Bestellung;
import wak.objects.Kategorie;
import wak.objects.Produkt;
import wak.system.db.DB_Connector;
import wak.user.Kunde;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Created by Christoph Nebendahl on 19.09.2015.
 */
public class Home {

    public static void main(String args[]){
        DB_Connector.connecttoDatabase();
       Kunde k = new Kunde("1","Christoph", "Nebendahl", "christoph@mail-nebendahl","04307-5198","WAK","01573-1655616","Eckernfoerder Str.",61,"24116","Kiel");
        System.out.println(k.getAddr());
       System.out.println(Timestamp.valueOf(LocalDateTime.now()));
        Kategorie kat = new Kategorie("Lautsprecher", "C:", 3);
        //ArrayList<Produkt> test = new ArrayList<Produkt>();
        Produkt p1 = new Produkt("Laber", 7.0,kat);
        //test.add(p1);
        //test.add(new Produkt("Labber", 6.0,kat));
        //Bestellung b = new Bestellung(k, test, Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()));
        //conn.updateDatabase("INSERT INTO 'kategorie' ('name', 'bild_pfad') VALUES ('Test','C:Christoph')");

        // insert into "order" (customer_id, price) values ((select customer_id from customer where name = 'John'), 12.34);


        //conn.updateDatabase("INSERT INTO bestellung (Kundeid, von,bis)" + "VALUES ('1', ?, ?)");
       //ResultSet rs = conn.QueryDatabase("SELECT * FROM kategorie WHERE 1");
        /*try {
            while(rs.next()){
                String em = rs.getint("id").toString();
                String em1 = rs.getTimestamp("von").toString();
                System.out.println(em);
                System.out.
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        */
    }
}
