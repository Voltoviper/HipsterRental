package wak;

import org.joda.time.DateTime;
import wak.objects.Bestellung;
import wak.objects.Produkt;
import wak.system.db.DB_Loader;
import wak.system.server.Seitenaufbau;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by chris_000 on 21.10.2015.
 */
public class main {

    public static void main(String[] args) {

        try {
            new DB_Loader();


        }catch(Exception e){
            e.printStackTrace();
    }



    }
}
