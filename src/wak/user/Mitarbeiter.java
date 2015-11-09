package wak.user;

import wak.system.db.DB_Connector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Created by Christoph Nebendahl on 20.09.2015.
 */
public class Mitarbeiter extends Person{
    boolean admin;
    UUID uuid;

    public Mitarbeiter(String vorname, String nachname, String username, String passwort){
        this.vorname = vorname;
        this.nachname=nachname;
        this.username=username;
        this.passwort=passwort;
        this.admin=false;
        this.id = generateID(false);
        inDBEintragen(this);
    }



    public Mitarbeiter(String vorname, String nachname, boolean admin) {
        this.admin = admin;
        this.vorname = vorname;
        this.nachname = nachname;


    }
    public Mitarbeiter(UUID uuid, String id, boolean isAdmin){
        this.admin = isAdmin;
        this.uuid = uuid;
        this.id = id;
    }

    public UUID getUuid() {
        if(uuid==null){
            return null;
        }else {
            return uuid;
        }
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
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
        this.vorname=vorname;
    }

    @Override
    public void setNachname(String nachname) {
        this.nachname=nachname;
    }

    @Override
    public void setUsername(String Username) {
        this.username=Username;
    }

    @Override
    public void setPasswort(String passwort) {
        this.passwort=passwort;
    }
    private void inDBEintragen(Mitarbeiter mitarbeiter) {
        DB_Connector.connecttoDatabase();

        try{
            String nutzereintragen="INSERT INTO `softwareengineering2`.`nutzer` (`benutzername`, `passwort`, `nachname`, `vorname`, `id`) VALUES (?, ?, ?, ?,?);";
            PreparedStatement nutzereintragen_ps=DB_Connector.con.prepareStatement(nutzereintragen);
            nutzereintragen_ps.setString(1, mitarbeiter.getUsername());
            nutzereintragen_ps.setString(2, mitarbeiter.getPasswort());
            nutzereintragen_ps.setString(3, mitarbeiter.getNachname());
            nutzereintragen_ps.setString(4, mitarbeiter.getVorname());
            nutzereintragen_ps.setString(5, mitarbeiter.getId());
            nutzereintragen_ps.executeUpdate();
            String mitarbeitereintragen="INSERT INTO `softwareengineering2`.`mitarbeiter` (`isadmin`, `Nutzerid`) VALUES (?, ?);";
            PreparedStatement mitarbeitereintragen_ps=DB_Connector.con.prepareStatement(mitarbeitereintragen);
            mitarbeitereintragen_ps.setInt(1,0);
            mitarbeitereintragen_ps.setString(2,mitarbeiter.getId());
            mitarbeitereintragen_ps.executeUpdate();
        }catch(SQLException e1){

        }finally{
            DB_Connector.closeDatabase();
        }





    }

    private String generateID(boolean b) {
        DB_Connector.connecttoDatabase();
        String id=null;
        try {
            if (b) {

            } else {
                String str = "SELECT 0+RIGHT(id,9)as id from nutzer WHERE LEFT(id, 1)='M' order by id DESC;";
                PreparedStatement str_ps = DB_Connector.con.prepareStatement(str);
                ResultSet str_rs= str_ps.executeQuery();
                str_rs.next();
                int i = str_rs.getInt("id")+1;

                id = String.valueOf(i);
                if(!(id.length()==9)){
                    id =("000000000" + String.valueOf(i)).substring(id.length());
                }
                id="M"+id;

            }
        }catch(SQLException e1){

        }finally{
            DB_Connector.closeDatabase();
        }

        return id;
    }
}
