package wak.objects;

import org.apache.commons.collections4.CollectionUtils;
import wak.system.db.DB_Connector;
import wak.system.server.Seitenaufbau;
import wak.user.Kunde;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

/**
 * Created by Christoph Nebendahl on 20.09.2015.
 */
public class Bestellung {
    Kunde kunde;
    ArrayList<Produkt> Position;
    Timestamp von, bis, bestellungdatum;
    int id;
    Produkt[][] zwischenprodukte;

    public Bestellung(int id, Kunde k , Timestamp von, Timestamp bis){
        this.kunde = k;
        this.Position = new ArrayList<Produkt>();
        this.von = von;
        this.bis = bis;
        this.id=id;
    }

    public Bestellung (Kunde kunde, ArrayList<Produkt> Produkte, Timestamp von, Timestamp bis)throws Exception{
        this.kunde =kunde;
        this.Position = Produkte;
        this.von = von;
        this.bis = bis;
        this.bestellungdatum = Timestamp.valueOf(LocalDateTime.now());
        if(ueberschneidet(this)) {
            Bestellung_eintragen(this);
        }else{
            throw new Exception("Fehler! Bestellung konnte nicht eingetragen werden!");
        }
    }

    public boolean ueberschneidet(Bestellung b){
        DB_Connector.connecttoDatabase();
        boolean moeglich=true;
        String bestellung = "SELECT anzVerfuegbareG(?,?,?) as anz";

        Collections.sort(this.Position);
        int zaehler=0;
        try {
            for (Object p : Seitenaufbau.katalog) {
                if (p.getClass() == Produkt.class) {
                    if (CollectionUtils.cardinality((Produkt) p, this.getPosition()) != 0) {

                        Produkt produkt = (Produkt) p;
                        PreparedStatement bestellung_ps = DB_Connector.con.prepareStatement(bestellung);
                        bestellung_ps.setInt(1, produkt.getId());
                        bestellung_ps.setTimestamp(2, b.getVon());
                        bestellung_ps.setTimestamp(3, b.getBis());
                        ResultSet bestellung_rs = bestellung_ps.executeQuery();
                        bestellung_rs.next();
                        int max_anzahl = bestellung_rs.getInt("anz");
                        if (max_anzahl < CollectionUtils.cardinality(produkt, this.getPosition())) {
                            return false;
                        } else {
                            moeglich = true;
                        }
                    }
                } else if (p.getClass() == Paket.class) {
                    if (CollectionUtils.cardinality((Paket) p, this.getPosition()) != 0) {
                        PreparedStatement bestellung_ps = DB_Connector.con.prepareStatement(bestellung);
                        Paket paket = (Paket) p;
                        ArrayList<Produkt> listprodukte = new ArrayList<Produkt>();
                        for (int i = 0; i < paket.getProdukte().length; i++) {
                            if (paket.getProdukte()[i][0] != null) {
                                listprodukte.add(paket.getProdukte()[i][0]);
                            }
                        }
                        zwischenprodukte = new Produkt[300][300];
                        for (int i = 0; i < paket.getProdukte().length; i++) {
                            if (paket.getProdukte()[i][0] != null){
                                bestellung_ps.setInt(1, paket.getProdukte()[i][0].getId());
                            bestellung_ps.setTimestamp(2, b.getVon());
                            bestellung_ps.setTimestamp(3, b.getBis());
                            ResultSet bestellung_rs = bestellung_ps.executeQuery();
                            bestellung_rs.next();
                            int max_anzahl = bestellung_rs.getInt("anz");

                            if (max_anzahl < CollectionUtils.cardinality(paket.getProdukte()[i][0], listprodukte)) {
                                moeglich = false;

                                int ii = 1;
                                while (!moeglich && ii < 4) {
                                    if (paket.getProdukte()[i][ii] != null){
                                        bestellung_ps.setInt(1, paket.getProdukte()[i][ii].getId());
                                    bestellung_ps.setTimestamp(2, b.getVon());
                                    bestellung_ps.setTimestamp(3, b.getBis());
                                    System.out.println(bestellung_ps.toString());
                                    bestellung_rs = bestellung_ps.executeQuery();
                                    bestellung_rs.next();
                                        System.out.println(bestellung_rs.getInt("anz"));
                                    max_anzahl = bestellung_rs.getInt("anz");
                                    if (max_anzahl < CollectionUtils.cardinality(paket.getProdukte()[i][0], listprodukte)) {
                                        moeglich = false;
                                    } else {
                                        moeglich = true;
                                        zwischenprodukte[i][0] = paket.getProdukte()[i][ii];
                                        break;
                                    }
                                    ii++;
                                }else {
                                        ii++;
                                    }


                                }

                            } else {
                                moeglich = true;
                                zwischenprodukte[i][0]=paket.getProdukte()[i][0];
                            }
                            if (!moeglich) {
                                return false;
                            }
                        }
                        }
                    }

                }
            }

        }catch(SQLException e1){
            e1.printStackTrace();
        }finally{
            DB_Connector.closeDatabase();
        }
       return moeglich;
    }


    private void Bestellung_eintragen(Bestellung b){
        String einfuegen = "INSERT INTO bestellung (Nutzerid, von, bis, genehmigt)" + "VALUES ((select Nutzerid from kunde WHERE kunde.Nutzerid=?),?,?,?)";
        String position = "Insert INTO bestellposition (Bestellungid,Produktid,position) "+"VALUES(?,(select id from produkt WHERE id=?),?)";
        String paket_position = "Insert INTO bestellposition (Bestellungid,Produktid,position, Referenzpos) "+"VALUES(?,(select id from produkt WHERE id=?),?,?)";
        try {


            //Vorbereiten der Bestellung für die Datenbank
            DB_Connector.connecttoDatabase();
            PreparedStatement bestellung = DB_Connector.con.prepareStatement(einfuegen);
            bestellung.setString(1, kunde.getId());
            bestellung.setTimestamp(2, b.von);
            bestellung.setTimestamp(3, b.bis);
            bestellung.setInt(4, 0);
            bestellung.executeUpdate();
            DB_Connector.closeDatabase();

            //Ermitteln der gegebenen ID
            DB_Connector.connecttoDatabase();
            PreparedStatement id = DB_Connector.con.prepareStatement("SELECT id FROM bestellung WHERE id = ( SELECT max(id) FROM bestellung )");
            ResultSet id_rs = id.executeQuery();
            id_rs.next();
            int bestell_id=id_rs.getInt("id");
            b.setId(bestell_id);

            //Einfügen der Bestellpositionen
            int i = 1;
            for (Object p : b.getPosition()) {

                int paketid=0;
                if (p.getClass() == Produkt.class) {
                    Produkt produkt = (Produkt) p;
                    PreparedStatement bestellposition = DB_Connector.con.prepareStatement(position);
                    bestellposition.setInt(1, bestell_id);
                    bestellposition.setInt(2, produkt.getId());
                    bestellposition.setInt(3, i);

                    bestellposition.executeUpdate();
                    i++;

                } else if (p.getClass() == Paket.class) {
                    paketid=i;
                    Produkt produkt = (Produkt) p;
                    PreparedStatement bestellposition = DB_Connector.con.prepareStatement(position);
                    bestellposition.setInt(1, bestell_id);
                    bestellposition.setInt(2, produkt.getId());
                    bestellposition.setInt(3, i);
                    System.out.println(bestellposition.toString());
                    bestellposition.executeUpdate();
                    i++;
                    PreparedStatement paket_position_ps = DB_Connector.con.prepareStatement(paket_position);
                    for(int ii=0;ii<zwischenprodukte.length;ii++){
                        if(zwischenprodukte[ii][0]!=null){
                            paket_position_ps.setInt(1,bestell_id);
                            paket_position_ps.setInt(2, zwischenprodukte[ii][0].getId());
                            paket_position_ps.setInt(3,i);
                            paket_position_ps.setInt(4,paketid);
                            System.out.println(paket_position_ps.toString());
                            paket_position_ps.executeUpdate();
                        }
                        i++;
                    }
                }
            }
        }catch(SQLException e){
            System.out.println("fehler beim Eintragen der Bestellung");
            e.printStackTrace();

        }finally {
            DB_Connector.closeDatabase();
        }
    }

    public Kunde getKunde() {
        return kunde;
    }

    public void setKunde(Kunde kunde) {
        this.kunde = kunde;
    }

    public ArrayList<Produkt> getPosition() {
        return Position;
    }

    public void setPosition(ArrayList<Produkt> position) {
        Position = position;
    }

    public Timestamp getVon() {
        return von;
    }

    public void setVon(Timestamp von) {
        this.von = von;
    }

    public Timestamp getBis() {
        return bis;
    }

    public void setBis(Timestamp bis) {
        this.bis = bis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bestellung that = (Bestellung) o;

        if (id != that.id) return false;
        if (kunde != null ? !kunde.equals(that.kunde) : that.kunde != null) return false;
        if (Position != null ? !Position.equals(that.Position) : that.Position != null) return false;
        if (von != null ? !von.equals(that.von) : that.von != null) return false;
        if (bis != null ? !bis.equals(that.bis) : that.bis != null) return false;
        return !(bestellungdatum != null ? !bestellungdatum.equals(that.bestellungdatum) : that.bestellungdatum != null);

    }

    @Override
    public int hashCode() {
        int result = kunde != null ? kunde.hashCode() : 0;
        result = 31 * result + (Position != null ? Position.hashCode() : 0);
        result = 31 * result + (von != null ? von.hashCode() : 0);
        result = 31 * result + (bis != null ? bis.hashCode() : 0);
        result = 31 * result + (bestellungdatum != null ? bestellungdatum.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }
}
