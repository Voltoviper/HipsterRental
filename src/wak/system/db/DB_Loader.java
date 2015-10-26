package wak.system.db;

import wak.objects.Kategorie;
import wak.objects.Produkt;
import wak.system.db.DB_Connector;
import wak.system.server.Seitenaufbau;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by chris_000 on 22.10.2015.
 */
public class DB_Loader {
    static Connection con = DB_Connector.con;
    static boolean runned=false;
    public DB_Loader(){
        try {
            if(!runned) {
                Kategorieanlegen();
                Produkteanlegen();

                runned = true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

   private void Produkteanlegen() throws SQLException{

        DB_Connector.connecttoDatabase();
        String produkt_string = "SELECT * FROM produkt";
        PreparedStatement produkt_ps = DB_Connector.con.prepareStatement(produkt_string);
        ResultSet produkt_rs = produkt_ps.executeQuery();
        String name, bezeichnung, beschreibung, hersteller_name, details;
        int id, kategorie;
        double mietzins;
        while(produkt_rs.next()){
            name = produkt_rs.getString("name");
            bezeichnung= produkt_rs.getString("bezeichnung");
            beschreibung = produkt_rs.getString("beschreibung");
            hersteller_name = produkt_rs.getString("hersteller_name");
            details = produkt_rs.getString("details");
            id = produkt_rs.getInt("id");
            mietzins = produkt_rs.getDouble("mietzins");
            kategorie = produkt_rs.getInt("Kategorieid");
            Kategorie Kat=null;
            for(Kategorie k:Seitenaufbau.kategorien){
                if(k.getId()==kategorie){
                    Kat = k;
                }
            }
            Produkt p = new Produkt(id, name, bezeichnung, beschreibung, hersteller_name, details, mietzins, null, Kat);
            Seitenaufbau.katalog.add(p);
        }


    }
    private void Kategorieanlegen()throws SQLException{
        DB_Connector.connecttoDatabase();
        String kategorie_string = "SELECT * FROM kategorie";
        PreparedStatement kategorie_ps = DB_Connector.con.prepareStatement(kategorie_string);
        ResultSet kategorie_rs = kategorie_ps.executeQuery();
        String name;
        int id, oberid;
        Kategorie ober;
        while(kategorie_rs.next()){
            name = kategorie_rs.getString("name");
            id=kategorie_rs.getInt("id");
            Kategorie k = new Kategorie(name, id);
            Seitenaufbau.kategorien.add(k);
        }
    }

}
