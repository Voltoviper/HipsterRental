package wak.user;

import java.util.UUID;

/**
 * Created by Christoph Nebendahl on 20.09.2015.
 */
public class Mitarbeiter extends Person{
    boolean admin;
    UUID uuid;

    public Mitarbeiter(String vorname, String nachname, boolean admin) {
        this.admin = admin;
        this.vorname = vorname;
        this.nachname = nachname;
        this.id = "M001";

    }
    public Mitarbeiter(UUID uuid, String id, boolean isAdmin){
        this.admin = isAdmin;
        this.uuid = uuid;
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
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
    public void setVorname(String vorname) {

    }

    @Override
    public void setNachname(String nachname) {

    }

}
