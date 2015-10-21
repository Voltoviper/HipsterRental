package wak.system.email;

import sun.misc.IOUtils;
import wak.objects.Bestellung;
import wak.user.Kunde;

import java.io.*;
import java.util.Properties;
import java.util.Scanner;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

/**
 * Created by chris_000 on 21.10.2015.
 */
public class emailservice {
    final static String email= "software@voltoviper.de";
    public static Session getGMailSession()
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
    public static void sendzusage(Session session, Kunde kunde, Bestellung b){
        try {
            MimeMessage msg = new MimeMessage(session);
            InternetAddress adressTo = new InternetAddress(kunde.getEmail());
            msg.setRecipient(Message.RecipientType.TO, adressTo);
            msg.setSubject(MimeUtility.encodeText("Auftragsbestätigung für Bestellung: "+b.getId(), "utf-8", "B"));
            msg.setFrom(new InternetAddress(email));
            String input = emailservice.class.getResource("./zusage.html").getPath();
            File f = new File(input);
            String content = new Scanner(f).useDelimiter("\\Z").next();

            System.out.println(content);

            msg.setContent(content, "text/html");
            Transport.send(msg);

        }catch(MessagingException e){
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
