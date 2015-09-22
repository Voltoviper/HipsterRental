package test.wak.user; 


import org.junit.Test;
import wak.user.Kunde;

import static junit.framework.TestCase.assertEquals;

/** 
* Kunde Tester. 
* 
* @author Christoph Nebendahl
* @since <pre>Sep 20, 2015</pre> 
* @version 1.0 
*/ 
public class KundeTest {
    Kunde testing;
    public static void main (String args[]){
        new KundeTest();
    }
    public KundeTest(){
        testing = new Kunde("1","Christoph", "Nebendahl", "christoph@mail-nebendahl","04307-5198","WAK","01573-1655616","Eckernfoerder Str.",61,"24116","Kiel");
        testing();
    }

    @Test
    public void testing(){
        assertEquals("Christoph",testing.getVorname());
    }

} 
