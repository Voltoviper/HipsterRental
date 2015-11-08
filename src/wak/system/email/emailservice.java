package wak.system.email;

import wak.objects.Bestellung;
import wak.objects.Produkt;
import wak.system.Formatter;
import wak.system.db.DB_Connector;
import wak.system.server.Seitenaufbau;
import wak.user.Kunde;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by chris_000 on 21.10.2015.
 */
public class emailservice {
    final static String email= "software@voltoviper.de";
    public static Session getSession()
    {
        final Properties props = new Properties();

        // Zum Empfangen
        props.setProperty( "mail.pop3.host", "voltoviper.de" );
        props.setProperty( "mail.pop3.user", email );
        props.setProperty( "mail.pop3.password", "soft123456" );
        props.setProperty( "mail.pop3.port", "110" );
        props.setProperty( "mail.pop3.auth", "true" );
        props.setProperty( "mail.pop3.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory" );

        // Zum Senden
        props.setProperty("mail.smtp.host", "voltoviper.de");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty( "mail.smtp.port", "25" );
        props.setProperty( "mail.smtp.socketFactory.port", "25" );
        props.setProperty( "mail.smtp.socketFactory.fallback", "false" );

        return Session.getInstance( props, new javax.mail.Authenticator() {
            @Override protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication( props.getProperty( "mail.pop3.user" ),
                        props.getProperty( "mail.pop3.password" ) );
            }
        } );
//    session.setDebug( true );
    }
    public static void postMail( Session session, String recipient,
                                 String subject, String message )
            throws MessagingException
    {
        Message msg = new MimeMessage( session );

        InternetAddress addressTo = new InternetAddress( recipient );
        msg.setRecipient(Message.RecipientType.TO, addressTo);

        msg.setSubject(subject);
        msg.setFrom(new InternetAddress(email));

    }
    public static void sendgenehmigung(Session session, Kunde kunde, Bestellung b, boolean genehmigt){
        try {
            MimeMessage msg = new MimeMessage(session);
            InternetAddress adressTo = new InternetAddress(kunde.getEmail());
            msg.setRecipient(Message.RecipientType.TO, adressTo);
            msg.setSubject(MimeUtility.encodeText("Auftragsaffirmation der Bestellung: " + b.getId(), "utf-8", "B"));
            msg.setFrom(new InternetAddress(email));
            msg.setContent(getContext(genehmigt, kunde, b), "text/html; charset=UTF-8");
            Transport.send(msg);

        }catch(MessagingException e){
            e.printStackTrace();
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    private static String getContext(boolean genehmigt, Kunde kunde, Bestellung b){

        StringBuffer content = new StringBuffer();
        content.append("<!DOCTYPE html><html lang=\"de\"><head><meta charset=\"utf-8\"><title></title></head><body>");
        content.append("Hallo " + kunde.getVorname() +" " + kunde.getNachname()+"<br>");
        if(genehmigt){
            content.append("Ihre Bestellung mit der Bestellid:" + b.getId() +"wird mit dieser E-Mail validiert!<br> Hochachtungsvoll <br> Das Hipster Rental Team");
            content.append("</body></html>");
            return content.toString();
        }else{
            content.append("Leider gibt es ein kleines Problem mit der Bestellung unter der BestellID: "+b.getId()+". Bitte setzen Sie sich mit uns in Verbindung, um dieses Problem aus der Welt zu schaffen.<br> Hochachtungsvoll <br> Das Hipster Rental Team");
            content.append("</body></html>");
            return content.toString();
        }
    }
    public static void sendZusammenfassung(Session session, Kunde kunde, Bestellung b){
        try {
            MimeMessage msg = new MimeMessage(session);
            InternetAddress addressTo = new InternetAddress(kunde.getEmail());
            msg.setRecipient(Message.RecipientType.TO, addressTo);
            msg.setFrom(new InternetAddress(email));
            msg.setSubject("Auftragszusammenfassung zu BestellID: " + b.getId());
            StringBuffer content = new StringBuffer();
            content.append("<!DOCTYPE html><html lang=\"de\"><head><meta charset=\"utf-8\"><title></title></head><body>");
            content.append(getBestelldetails(b.getId()));
            msg.setContent(content.toString(),"text/html; charset=UTF-8");
            Transport.send(msg);

        }catch(MessagingException e){
            e.printStackTrace();
        }
    }
    private static String getBestelldetails(int bestellid){
        DB_Connector.connecttoDatabase();
        StringBuffer buffer= new StringBuffer();

        try{
            buffer.append("<table width=\"100%\"");
            //Allgemeine Informationen
            String info_string = "SELECT bestellung.id, bestellung.Nutzerid,bestellung.genehmigt,  bestellung.von, nutzer.vorname, nutzer.nachname ,bestellung.bis, kunde.email, kunde.organame, kunde.strasse, kunde.hausnummer, kunde.plz, kunde.ort, kunde.telefonnummer, kunde.handynummer FROM softwareengineering2.bestellung inner join kunde ON(bestellung.Nutzerid=kunde.Nutzerid) inner join nutzer on(bestellung.Nutzerid=nutzer.id) WHERE bestellung.id=?;\n";
            PreparedStatement info_ps = DB_Connector.con.prepareStatement(info_string);
            info_ps.setInt(1,bestellid);
            ResultSet info_rs = info_ps.executeQuery();
            info_rs.next();
            String nutzerid = info_rs.getString("Nutzerid");
            String vorname = info_rs.getString("vorname");
            String nachname = info_rs.getString("nachname");
            String email = info_rs.getString("email");
            Timestamp von = info_rs.getTimestamp("von");
            Time von_time = info_rs.getTime("von");
            Timestamp bis = info_rs.getTimestamp("bis");
            Time bis_time = info_rs.getTime("bis");
            int genehmigt = info_rs.getInt("genehmigt");
            int tage = Seitenaufbau.getTage(von, bis);

            buffer.append("<th colspan=4 align=center><b>Bestellnummer: " + bestellid + "</b></th>");
            buffer.append("<tr><td class=\"umrandung\"><b>Kundennummer:</b> " + nutzerid + "</td><td class=\"umrandung\"><b>Vorname: </b>" + vorname + "</td><td class=\"umrandung\"><b>Nachname: </b>" + nachname + "</td><td class=\"umrandung\"> <b>E-Mail: </b>" + email + "</td></tr>" +
                    "<tr><td class=\"umrandung\">Von: " + Formatter.dateFormatter(von) + "</td><td class=\"umrandung\">Bis: " + Formatter.dateFormatter(bis) + "</td>");
            buffer.append("<td></td><td></td></tr>");
            buffer.append("<tr><td class\"umrandung\">Anzahl Tage: " + tage + "</td></tr>");
            buffer.append("</table><table width=100%>");
            //Tabelle der Positionen
            buffer.append("<tr><td><b>Pos.</b></td><td><b>Name</b></td><td><b>Bezeichnung</b></td><td><b>Hersteller</b></td><td><b>Mietzins pro Tag</b></td><td><b>Kategorie</b></td></tr>");
            String position_string = "SELECT bestellposition.position, produkt.name AS produktname,bezeichnung,hersteller_name, mietzins,kategorie.name AS kategoriename FROM bestellposition INNER JOIN produkt ON(bestellposition.Produktid = produkt.id) INNER JOIN kategorie ON(Kategorieid = kategorie.id) WHERE Bestellungid = ? ;\n";
            PreparedStatement position_ps = DB_Connector.con.prepareStatement(position_string);
            position_ps.setInt(1,bestellid);
            ResultSet position_rs = position_ps.executeQuery();
            int pos =0, kategorieid;
            String name, bezeichnung, hersteller_name, kategorie;
            double mietzins, miete=0.0;
            ArrayList<Double> waren = new ArrayList<Double>();
            while(position_rs.next()){
                pos=position_rs.getInt("position");
                name=position_rs.getString("produktname");
                bezeichnung=position_rs.getString("bezeichnung");
                hersteller_name = position_rs.getString("hersteller_name");
                mietzins = position_rs.getDouble("mietzins");
                kategorie = position_rs.getString("kategoriename");
                miete+=mietzins;
                waren.add(mietzins);
                buffer.append("<tr><td>" + pos + "</td><td>" + name + "</td><td>" + bezeichnung + "</td><td>" + hersteller_name + "</td><td>" + Formatter.formatdouble(mietzins) + "</td><td>" + kategorie + "</td></tr>");
            }
            buffer.append("<tr><td colspan=4><b>Summe pro Tag</b></td><td><b>" + Formatter.formatdouble(miete) + "</b></td><td></td></tr>");
            double gesamtsumme = Seitenaufbau.getgesamtsumme(miete, tage);
            double endsumme = Seitenaufbau.getEndsumme(nutzerid, tage, waren);
            double rabatt = gesamtsumme-endsumme;
            buffer.append("<tr><td colspan=4><b>Summe Gesamt ohne Rabatt</b></td><td><b>" + Formatter.formatdouble(gesamtsumme) + "</b></td><td></td></tr>");
            buffer.append("<tr><td colspan=4><b>Rabatt</b></td><td><b>" + Formatter.formatdouble(rabatt) + "</b></td><td></td></tr>");
            buffer.append("<tr><td colspan=4><b>Summe Gesamt inkl. Rabatt</b></td><td><b>" + Formatter.formatdouble(endsumme) + "</b></td><td></td></tr>");
            buffer.append("<tr><td colspan=4><b></b></td><td><b>" + "" + "</b></td><td></td></tr>");
            // Tabelle ende
            buffer.append("</table>");
        }catch(SQLException e2){
                buffer.append(e2);
        }finally{
            DB_Connector.closeDatabase();
        }
        return buffer.toString();
    }
}
