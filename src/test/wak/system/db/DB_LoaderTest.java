package test.wak.system.db;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import wak.system.db.DB_Loader;
import wak.system.server.Seitenaufbau;

import static org.junit.Assert.assertEquals;

/** 
* DB_Loader Tester. 
* 
* @author <Authors name> 
* @since <pre>Okt 22, 2015</pre> 
* @version 1.0 
*/ 
public class DB_LoaderTest { 

@Before
public void before() throws Exception {
   DB_Loader loader = new DB_Loader();
} 

@After
public void after() throws Exception {
} 


/** 
* 
* Method: Produkteanlegen() 
* 
*/ 
@Test
public void testProdukteanlegen() throws Exception {

   assertEquals(6, Seitenaufbau.katalog.size());

} 

/** 
* 
* Method: Kategorieanlegen() 
* 
*/ 
@Test
public void testKategorieanlegen() throws Exception {
   assertEquals(7, Seitenaufbau.kategorien.size());
} 

} 
