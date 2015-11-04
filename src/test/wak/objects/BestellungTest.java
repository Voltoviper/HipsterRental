package test.wak.objects;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import wak.objects.Bestellung;
import wak.objects.Produkt;
import wak.system.db.DB_Loader;
import wak.system.server.Seitenaufbau;

import java.sql.Timestamp;
import java.util.ArrayList;

import static org.junit.Assert.assertFalse;



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
  new DB_Loader();
} 

@After
public void after() throws Exception { 
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
@Test
public void testUeberschneidet() throws Exception {
   ArrayList<Produkt> produkte = new ArrayList<Produkt>();
   produkte.add(Seitenaufbau.katalog.get(1));
   produkte.add(Seitenaufbau.katalog.get(2));
   produkte.add(Seitenaufbau.katalog.get(3));
   produkte.add(Seitenaufbau.katalog.get(1));
   Bestellung b = new Bestellung(Seitenaufbau.kunde.get(1), produkte, new Timestamp(new DateTime().getMillis()), new Timestamp(new DateTime().plusDays(1).getMillis()));

   assertFalse(b.ueberschneidet(b));

} 



} 
