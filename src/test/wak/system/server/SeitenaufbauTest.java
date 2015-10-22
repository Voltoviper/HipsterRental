package test.wak.system.server; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert .*;

import wak.system.db.DB_Connector;
import wak.system.server.Seitenaufbau;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

/** 
* Seitenaufbau Tester. 
* 
* @author Christoph Nebendahl
* @since <pre>Okt 19, 2015</pre> 
* @version 1.0 
*/ 
public class SeitenaufbauTest { 

@Before
public void before() throws Exception {
    System.out.println("Starte Datenbankverbindung");
    DB_Connector.connecttoDatabase();
} 

@After
public void after() throws Exception {
    System.out.println("Schlieﬂe Datenbankverbindung");
    DB_Connector.closeDatabase();
}
/** 
* 
* Method: getTage(Date von, Date bis) 
* 
*/ 
@Test
public void testGetTage() throws Exception { 
//TODO: Test goes here...
    System.out.println("Starte TageBerechnungTest");
    Timestamp von = new Timestamp(2015,10,19,11,0,0,0);
    Timestamp bis = new Timestamp(2015,10,22,11,0,0,0);
    assertEquals(Seitenaufbau.getTage(von, bis), 3.0, 0.0);
    System.out.println("TageBerechnungTest erfolgreich");
} 


/** 
* 
* Method: formatdouble(Double d) 
* 
*/ 
@Test
public void testFormatdouble() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = Seitenaufbau.getClass().getMethod("formatdouble", Double.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: getKunde(String uuid) 
* 
*/ 
@Test
public void testGetKunde() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = Seitenaufbau.getClass().getMethod("getKunde", String.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: dateFormatter(Date date) 
* 
*/ 
@Test
public void testDateFormatter() throws Exception { 
//TODO: Test goes here...
    String query = ("SELECT von FROM bestellung WHERE id=1;");
    PreparedStatement result_ps = DB_Connector.con.prepareStatement(query);
    ResultSet result_rs = result_ps.executeQuery();
    result_rs.next();
    Timestamp t = result_rs.getTimestamp("von");
    String d = "07.12.2015 13:00";
    assertEquals(Seitenaufbau.dateFormatter(t), d);
/* 
try { 
   Method method = Seitenaufbau.getClass().getMethod("dateFormatter", Date.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: getgesamtsumme(double mietzins, int tage) 
* 
*/ 
@Test
public void testGetgesamtsumme() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = Seitenaufbau.getClass().getMethod("getgesamtsumme", double.class, int.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 
@Test
    public void testGetEndsumme() throws Exception{
    String user_id = "K00000001";
    int tage = 5;
    ArrayList<Double> preise =new ArrayList<Double>();
    preise.add(25.60);
    preise.add(10.00);
    preise.add(10.00);
    assertEquals(Seitenaufbau.getEndsumme(user_id, tage, preise), 155.04,0.0);
}


} 
