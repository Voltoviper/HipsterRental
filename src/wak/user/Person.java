package wak.user;

/**
 * Created by Christoph Nebendahl on 20.09.2015.
 */
public abstract class Person {
    String vorname, nachname, id;

    public abstract String getVorname();
    public abstract String getNachname();
    public abstract String getId();
    public abstract void setVorname(String vorname);
    public abstract void setNachname(String nachname);

}
