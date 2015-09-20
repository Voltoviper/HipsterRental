package wak;

import wak.user.Kunde;

/**
 * Created by Christoph Nebendahl on 19.09.2015.
 */
public class Home {

    public static void main(String args[]){
       Kunde k = new Kunde("christoph@mail-nebendahl","04307-5198","WAK","01573-1655616","Eckernfoerder Str.",61,"24116","Kiel");
        System.out.print(k.getAddr());
    }
}
