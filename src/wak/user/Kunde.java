package wak.user;

import wak.objects.Warenkorb;
import wak.system.Formatter;
import wak.system.db.DB_Connector;
import wak.system.server.Seitenaufbau;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Created by Christoph Nebendahl on 20.09.2015.
 */
public class Kunde extends Person {
    String email, telefon, organame, handy;
    Adresse addr;
    UUID uuid;
    Warenkorb korb;

    /**
     * Kunde wird angelegt.
     * @param id
     * @param vorname
     * @param nachname
     * @param email
     * @param telefon
     * @param organame
     * @param handy
     * @param strasse
     * @param hausnummer
     * @param plz
     * @param ort
     * @param uuid
     */
    public Kunde(String id, String vorname, String nachname,String email, String telefon, String organame, String handy, String strasse, int hausnummer, String plz, String ort, UUID uuid) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.email = email;
        this.telefon = telefon;
        this.organame = organame;
        this.handy = handy;
        this.addr = new Adresse(strasse, ort, plz, hausnummer);
        this.id = id;
        this.uuid = uuid;
    }
    public Kunde(String id, String vorname, String nachname,String email, String telefon, String handy,  String strasse, int hausnummer, String plz, String ort, String uuid,  boolean eintragen) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.email = email;
        this.telefon = telefon;
        this.handy = handy;
        this.addr = new Adresse(strasse, ort, plz, hausnummer);
        this.username = email;
        this.passwort = Formatter.hashen(email);
        if(id!=null){
            this.id = id;
        }else{
            this.id=generateID(false);
        }
        this.uuid = java.util.UUID.fromString(uuid);
        if(eintragen){
            eintragen(this);
        }
        Seitenaufbau.kunde.add(this);
    }


    public Kunde(String id, String vorname, String nachname,String email, String telefon, String handy, Adresse addr, String username, String passwort, boolean eintragen) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.email = email;
        this.telefon = telefon;
        this.handy = handy;
        this.addr = addr;
        if(id!=null){
            this.id = id;
        }else{
            this.id=generateID(false);
        }
        this.username = username;
        this.passwort = passwort;
        if(eintragen){
            eintragen(this);
        }
        Seitenaufbau.kunde.add(this);
    }

    private void eintragen(Kunde kunde) {
        DB_Connector.connecttoDatabase();
        try {
            String nutzereintragen = "INSERT INTO `softwareengineering2`.`nutzer` (`benutzername`, `passwort`, `nachname`, `vorname`, `id`) VALUES (?, ?, ?, ?,?);";
            PreparedStatement nutzereintragen_ps = DB_Connector.con.prepareStatement(nutzereintragen);
            nutzereintragen_ps.setString(1, kunde.getUsername());
            nutzereintragen_ps.setString(2, kunde.getPasswort());
            nutzereintragen_ps.setString(3, kunde.getNachname());
            nutzereintragen_ps.setString(4, kunde.getVorname());
            nutzereintragen_ps.setString(5, kunde.getId());
            nutzereintragen_ps.executeUpdate();
            String kundeeintragen ="INSERT INTO `softwareengineering2`.`kunde` (`email`, `organame`, `strasse`, `hausnummer`, `plz`, `ort`, `telefonnummer`, `handynummer`, `Nutzerid`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement kundeeintragen_ps = DB_Connector.con.prepareStatement(kundeeintragen);
            kundeeintragen_ps.setString(1,kunde.getEmail());
            kundeeintragen_ps.setString(2,kunde.getEmail());
            kundeeintragen_ps.setString(3,kunde.getAddr().getStrasse());
            kundeeintragen_ps.setInt(4,kunde.getAddr().getHausnummer());
            kundeeintragen_ps.setString(5,kunde.getAddr().getPlz());
            kundeeintragen_ps.setString(6,kunde.getAddr().getOrt());
            kundeeintragen_ps.setString(7,kunde.getTelefon());
            kundeeintragen_ps.setString(8,kunde.getHandy());
            kundeeintragen_ps.setString(9,kunde.getId());
            kundeeintragen_ps.executeUpdate();

        }catch (SQLException e1){
            e1.printStackTrace();
        }finally {
            DB_Connector.closeDatabase();
        }

    }

    private String generateID(boolean b) {
        DB_Connector.connecttoDatabase();
        String id=null;
        try {
            if (b) {

            } else {
                String str = "SELECT 0+RIGHT(id,9)as id from nutzer WHERE LEFT(id, 1)='K' order by id DESC;";
                PreparedStatement str_ps = DB_Connector.con.prepareStatement(str);
                ResultSet str_rs= str_ps.executeQuery();
                str_rs.next();
                int i = str_rs.getInt("id")+1;

                id = String.valueOf(i);
                if(!(id.length()==9)){
                    id =("000000000" + String.valueOf(i)).substring(id.length());
                }
                id="K"+id;

            }
        }catch(SQLException e1){

        }finally{
            DB_Connector.closeDatabase();
        }

        return id;
    }




    /**
     * Kunde wird nur für die Zuordnung von SessionID und Kundennummer angelegt. Es muss später erweitert werden!
     * @param id
     * @param uuid
     */
    public Kunde(String id, UUID uuid){
        this.id=id;
        this.uuid=uuid;
    }

    public Warenkorb getKorb() {
        return korb;
    }

    public void setKorb(Warenkorb korb) {
        this.korb = korb;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        try {
            DB_Connector.connecttoDatabase();
            String dbEmail = "UPDATE `softwareengineering2`.`kunde` SET `email`=? WHERE `Nutzerid`=?;";
            PreparedStatement dbEmail_ps = DB_Connector.con.prepareStatement(dbEmail);
            dbEmail_ps.setString(1,email);
            dbEmail_ps.setString(2, this.getId());
            dbEmail_ps.executeUpdate();
        }catch(SQLException e1){
            e1.printStackTrace();
        }finally{
            DB_Connector.closeDatabase();
        }

    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
        try {
            DB_Connector.connecttoDatabase();
            String dbEmail = "UPDATE `softwareengineering2`.`kunde` SET `telefonnummer`=? WHERE `Nutzerid`=?;";
            PreparedStatement dbEmail_ps = DB_Connector.con.prepareStatement(dbEmail);
            dbEmail_ps.setString(1,telefon);
            dbEmail_ps.setString(2, this.getId());
            dbEmail_ps.executeUpdate();
        }catch(SQLException e1){
            e1.printStackTrace();
        }finally{
            DB_Connector.closeDatabase();
        }
    }

    public String getOrganame() {
        return organame;
    }

    public void setOrganame(String organame) {
        this.organame = organame;
    }

    public String getHandy() {
        return handy;
    }

    public void setHandy(String handy) {
        this.handy = handy;
        try {
            DB_Connector.connecttoDatabase();
            String dbEmail = "UPDATE `softwareengineering2`.`kunde` SET `handynummer`=? WHERE `Nutzerid`=?;";
            PreparedStatement dbEmail_ps = DB_Connector.con.prepareStatement(dbEmail);
            dbEmail_ps.setString(1,handy);
            dbEmail_ps.setString(2, this.getId());
            dbEmail_ps.executeUpdate();
        }catch(SQLException e1){
            e1.printStackTrace();
        }finally{
            DB_Connector.closeDatabase();
        }
    }

    public Adresse getAddr() {
        return addr;
    }

    public void setAddr(Adresse addr) {
        this.addr = addr;
    }

    public String getUuid(){
        if(uuid==null){
            return null;
        }else {
            return uuid.toString();
        }
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public String getVorname() {
        return vorname;
    }

    @Override
    public String getNachname() {
        return nachname;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPasswort() {
        return passwort;
    }

    @Override
    public void setVorname(String vorname) {
        this.vorname = vorname;
        try {
            DB_Connector.connecttoDatabase();
            String dbEmail = "UPDATE `softwareengineering2`.`nutzer` SET `vorname`=? WHERE `id`=?;";
            PreparedStatement dbEmail_ps = DB_Connector.con.prepareStatement(dbEmail);
            dbEmail_ps.setString(1,vorname);
            dbEmail_ps.setString(2, this.getId());
            dbEmail_ps.executeUpdate();
        }catch(SQLException e1){
            e1.printStackTrace();
        }finally{
            DB_Connector.closeDatabase();
        }
    }

    @Override
    public void setNachname(String nachname) {
        this.nachname= nachname;
        try {
            DB_Connector.connecttoDatabase();
            String dbEmail = "UPDATE `softwareengineering2`.`nutzer` SET `nachname`=? WHERE `id`=?;";
            PreparedStatement dbEmail_ps = DB_Connector.con.prepareStatement(dbEmail);
            dbEmail_ps.setString(1,nachname);
            dbEmail_ps.setString(2, this.getId());
            dbEmail_ps.executeUpdate();
        }catch(SQLException e1){
            e1.printStackTrace();
        }finally{
            DB_Connector.closeDatabase();
        }
    }

    @Override
    public void setUsername(String Username) {

    }

    @Override
    public void setPasswort(String passwort) {
        passwort= Formatter.hashen(passwort);
        this.passwort = passwort;
        try {
            DB_Connector.connecttoDatabase();
            String dbEmail = "UPDATE `softwareengineering2`.`nutzer` SET `passwort`=? WHERE `id`=?;";
            PreparedStatement dbEmail_ps = DB_Connector.con.prepareStatement(dbEmail);
            dbEmail_ps.setString(1,passwort);
            dbEmail_ps.setString(2, this.getId());
            dbEmail_ps.executeUpdate();
        }catch(SQLException e1){
            e1.printStackTrace();
        }finally{
            DB_Connector.closeDatabase();
        }


    }
}
