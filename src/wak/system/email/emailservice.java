package wak.system.email;

import com.sun.xml.internal.org.jvnet.mimepull.MIMEMessage;
import sun.misc.IOUtils;
import wak.objects.Bestellung;
import wak.objects.Produkt;
import wak.system.server.Seitenaufbau;
import wak.user.Kunde;

import java.io.*;
import java.util.Properties;
import java.util.Scanner;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

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
            msg.setFrom(email);
            msg.setSubject("Auftragszusammenfassung zu BestellID: " + b.getId());
            StringBuffer content = new StringBuffer();
            content.append("<!DOCTYPE html><html lang=\"de\"><head><meta charset=\"utf-8\"><title></title></head><body>");
            content.append("Dies ist eine automatisch generierte E-Mail:<br> Es wurde folgende Bestellung eingereicht ");

            content.append("<table><tr><td>Name:"+kunde.getVorname()+"</td><td>Nachname: "+kunde.getNachname()+"</td></tr>");
            content.append("<tr><td>Von: "+b.getVon()+"</td><td>Bis: "+b.getBis()+"</td></tr>");
            content.append("</table><table border=\"1\"><tr><td>Position</td><td>Name</td><td>Bezeichnung</td><td>Hersteller</td><td>Mietzins pro Tag</td></tr><tr>");
            int i = 0;
            for (Produkt p:b.getPosition()){
                i++;
                content.append("<td>"+i+"</td><td>"+p.getName()+
                        "</td><td>"+p.getBezeichnung()+"</td><td>"+p.getHerstellername()+"</td><td>"+p.getMietzins()+"</td></tr><tr>");
            }

            content.append(        "</tr></table>");
            content.append("Vielen Dank<br>Das Hipster Rental Team");
            msg.setContent(content.toString(),"text/html; charset=UTF-8");
            Transport.send(msg);

        }catch(MessagingException e){
            e.printStackTrace();
        }
    }

}
