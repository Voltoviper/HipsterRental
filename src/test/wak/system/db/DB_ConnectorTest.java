package test.wak.system.db;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import wak.system.db.DB_Connector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import static org.junit.Assert.*;

/** 
* DB_Connector Tester. 
* 
* @author Christoph Nebendahl
* @since <pre>Okt 13, 2015</pre> 
* @version 1.0 
*/ 
public class DB_ConnectorTest { 

@Before
public void before() throws Exception {
    System.out.println("Starte Datenbankverbindung");
    DB_Connector.connecttoDatabase();
} 

@After
public void after() throws Exception {
    System.out.println("Schließe Datenbankverbindung");
    DB_Connector.closeDatabase();
} 

/** 
* 
* Method: connecttoDatabase() 
* 
*/ 
@Test
public void testConnecttoDatabase() throws Exception{
    System.out.println("Starte Datenbankverbindungtest");
    DB_Connector.connecttoDatabase();
    assertFalse(DB_Connector.con.isClosed());
    System.out.println("Datenbankverbindung erfolgreich");


}
    @Test
    public void testQuerys() throws Exception{
        System.out.println("Starte Query Test");
        ArrayList<String> querys = new ArrayList<String>();
        //Userabfrage
        querys.add("SELECT passwort, id FROM softwareengineering2.nutzer;");
        //Warenkorbprodukte
        querys.add("SELECT id,name, bezeichnung, mietzins FROM produkt ORDER BY id");
        //Kategorieauflistung
        querys.add("SELECT name,id FROM kategorie WHERE oberkategorie is null ORDER BY name");
        //Produkt auslesen
        querys.add("SELECT name, bezeichnung, details, beschreibung, mietzins, Kategorieid,hersteller_name FROM produkt WHERE id=1");
        //Produkt übersicht
        querys.add("SELECT name, bezeichnung, mietzins  FROM produkt WHERE id=1");
        //Oberkategorie
        querys.add("Select id,name FROM kategorie WHERE oberkategorie=2 Order BY id");
        //Produkte pro Kategorie
        querys.add("SELECT id,name, bezeichnung, mietzins FROM produkt WHERE Kategorieid=3 ORDER BY id");
        //Informationen über die Kunden
        querys.add("SELECT nutzer.vorname, nutzer.nachname, kunde.strasse, kunde.hausnummer, kunde.plz, kunde.ort, kunde.telefonnummer, kunde.handynummer, kunde.email FROM kunde Inner join nutzer on kunde.Nutzerid=nutzer.id WHERE kunde.Nutzerid=\"K000000001\"");
        //Bestellübersicht
        querys.add("select Bestellungid, von, bis ,gesamtkosten from (select Bestellungid ,round(sum(mietzins), 2) as Gesamtkosten from bestellposition inner join produkt ON(bestellposition.Produktid = produkt.id) group by Bestellungid) as temp inner join bestellung ON(Bestellungid = bestellung.id) where genehmigt = 0;");
        //Bestellpositionen
        querys.add("Select position, bezeichnung,name , hersteller_name, mietzins, kategorieid from bestellposition inner join produkt ON(bestellposition.Produktid = produkt.id) WHERE Bestellungid=1;");

        for(String s:querys){
            PreparedStatement result_ps = DB_Connector.con.prepareStatement(s);
            ResultSet result_rs = result_ps.executeQuery();
            assertTrue(result_rs.next());
        }

        System.out.println("QueryTest erfolgreich");
    }
@Test
    public void testsum() throws Exception{
    System.out.println("Starte Rundungs Test");
    PreparedStatement result_ps = DB_Connector.con.prepareStatement("select gesamtkosten from (select Bestellungid ,round(sum(mietzins), 2) as Gesamtkosten from bestellposition inner join produkt ON(bestellposition.Produktid = produkt.id) group by Bestellungid) as temp");
            ResultSet result_rs = result_ps.executeQuery();
    result_rs.next();
    assertEquals(result_rs.getDouble("gesamtkosten") , 121.30,0.00);
    System.out.println("RundungsTest erfolgreich");
}


} 
