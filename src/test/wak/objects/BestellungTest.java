package test.wak.objects;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import wak.objects.Bestellung;
import wak.objects.Produkt;
import wak.system.db.DB_Connector;
import wak.system.db.DB_Loader;
import wak.system.server.Seitenaufbau;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
* Bestellung Tester. 
* 
* @author <Authors name> 
* @since <pre>Nov 3, 2015</pre> 
* @version 1.0 
*/ 
public class BestellungTest { 

@Before
public void before() throws Exception {
  DB_Connector.connecttoDatabase();
   String s =("CALL resetdb");
   PreparedStatement result_ps = DB_Connector.con.prepareCall(s);
   result_ps.execute();

  new DB_Loader();
} 

@After
public void after() throws Exception {
   DB_Connector.closeDatabase();
}

   /**
    *
    * @throws Exception
    */
@Test
public void testConstructor() throws Exception{
   ArrayList<Produkt> produkte = new ArrayList<Produkt>();
   produkte.add(Seitenaufbau.katalog.get(1));
   produkte.add(Seitenaufbau.katalog.get(2));
   produkte.add(Seitenaufbau.katalog.get(3));
   produkte.add(Seitenaufbau.katalog.get(1));
   Bestellung b = new Bestellung(Seitenaufbau.kunde.get(1), produkte, new Timestamp(new DateTime().getMillis()), new Timestamp(new DateTime().plusDays(1).getMillis()));

}


/** 
* 
* Method: ueberschneidet(Bestellung b) 
* 
*/ 
@Test (expected = Exception.class)
public void testUeberschneidet() throws Exception {
   ArrayList<Produkt> produkte = new ArrayList<Produkt>();
   produkte.add(Seitenaufbau.katalog.get(1));
   produkte.add(Seitenaufbau.katalog.get(2));
   produkte.add(Seitenaufbau.katalog.get(3));
   produkte.add(Seitenaufbau.katalog.get(1));
   Bestellung b = new Bestellung(Seitenaufbau.kunde.get(1), produkte, new Timestamp(new DateTime().plusDays(2).getMillis()), new Timestamp(new DateTime().plusDays(3).getMillis()));

   assertFalse(b.ueberschneidet(b));

    ArrayList<Produkt> produkte2 = new ArrayList<Produkt>();
    produkte2.add(Seitenaufbau.katalog.get(6));
    Bestellung b2 = new Bestellung(Seitenaufbau.kunde.get(1), produkte2, new Timestamp(new DateTime().plusYears(1).getMillis()), new Timestamp(new DateTime().plusYears(1).plusDays(1).getMillis()));
    assertTrue(b2.ueberschneidet(b2));

    ArrayList<Produkt> produkte3 = new ArrayList<Produkt>();
    produkte3.add(Seitenaufbau.katalog.get(7));
    Bestellung b3 = new Bestellung(Seitenaufbau.kunde.get(1), produkte3, new Timestamp(new DateTime().plusYears(1).getMillis()), new Timestamp(new DateTime().plusYears(1).plusDays(1).getMillis()));
    assertTrue(b3.ueberschneidet(b3));

    ArrayList<Produkt> produkte4 = new ArrayList<Produkt>();
    produkte4.add(Seitenaufbau.katalog.get(7));
    Bestellung b4 = new Bestellung(Seitenaufbau.kunde.get(1), produkte4, new Timestamp(new DateTime().plusYears(1).getMillis()), new Timestamp(new DateTime().plusYears(1).plusDays(1).getMillis()));
    assertFalse(b4.ueberschneidet(b4));
}





} 
