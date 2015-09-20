package wak.user;

/**
 * Created by Christoph Nebendahl on 20.09.2015.
 */
public class Mitarbeiter extends Person{
    boolean admin;

    public Mitarbeiter(String vorname, String nachname, boolean admin) {
        this.admin = admin;
        this.vorname = vorname;
        this.nachname = nachname;
        this.id = "M001";
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

}
