package wak.objects;

import wak.system.db.DB_Connector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Christoph Nebendahl on 20.09.2015.
 */
public class Paket extends Produkt{

    Produkt[][] produkte;

    public Paket(String name,String bezeichnung, String beschreibung, String herstellername,  String detail, double mietzins, Kategorie kategorie,  Produkt[][] produkte, boolean eintragen) {
        super(name, bezeichnung, beschreibung, herstellername,detail, mietzins, kategorie, eintragen);

        try {
            DB_Connector.connecttoDatabase();
            PreparedStatement id_ps = DB_Connector.con.prepareStatement("SELECT id FROM produkt WHERE name =? ");
            id_ps.setString(1, this.getName());
            ResultSet id_rs = id_ps.executeQuery();
            id_rs.next();
            int produkt_id = id_rs.getInt("id");
            this.setId(produkt_id);

        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            DB_Connector.closeDatabase();
        }
        this.produkte = produkte;

        if(eintragen){
            eintragen(this);
        }

    }

    private void eintragen(Paket paket) {
        try {
            DB_Connector.connecttoDatabase();
            String paket_einfuegen = "INSERT INTO `softwareengineering2`.`paketposition` (`Id`, `position`, `Produktid`, `AlternativproduktA`, `AlternativproduktB`, `AlternativproduktC`) VALUES (?, ?, ?, ?, ?, ?);";
            PreparedStatement paket_ps = DB_Connector.con.prepareStatement(paket_einfuegen);
            for(int i=0; i<20;i++) {
                if(paket.getProdukte()[i][0]!=null) {
                    paket_ps.setInt(1, paket.getId());
                    paket_ps.setInt(2, i + 1);
                    paket_ps.setInt(3, paket.getProdukte()[i][0].getId());
                    paket_ps.setInt(4, paket.getProdukte()[i][1].getId());
                    paket_ps.setInt(5, paket.getProdukte()[i][2].getId());
                    paket_ps.setInt(6, paket.getProdukte()[i][3].getId());
                    paket_ps.executeUpdate();
                }else{
                    break;
                }

            }


        }catch(SQLException e1){
            e1.printStackTrace();
        }finally{
            DB_Connector.closeDatabase();
        }
    }

    public  Produkt[][] getProdukte() {
        return produkte;
    }

    public void setProdukte( Produkt[][] produkte) {
        this.produkte = produkte;
    }

}
