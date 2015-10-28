package wak.user;

/**
 * Created by Christoph Nebendahl on 20.09.2015.
 */
public abstract class Person {
    String vorname, nachname, id, username, passwort;

    public abstract String getVorname();
    public abstract String getNachname();
    public abstract String getId();
    public abstract String getUsername();
    public abstract String getPasswort();
    public abstract void setVorname(String vorname);
    public abstract void setNachname(String nachname);
    public abstract void setUsername(String Username);
    public abstract void setPasswort(String passwort);


}
