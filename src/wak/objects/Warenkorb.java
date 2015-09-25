package wak.objects;

import java.util.ArrayList;

/**
 * Created by chris_000 on 25.09.2015.
 */
public class Warenkorb {
   ArrayList<Integer> produkt_id = new ArrayList<Integer>();
    String uuid;

    public Warenkorb(String uuid) {
        this.uuid = uuid;
    }

    public void addprodukt(int id){
        produkt_id.add(id);
    }

    public void deleteprodukt(int id){

    }

    public ArrayList<Integer> getProdukt_id() {
        return produkt_id;
    }

    public void setProdukt_id(ArrayList<Integer> produkt_id) {
        this.produkt_id = produkt_id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
