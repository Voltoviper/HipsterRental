package wak.system.server;

import wak.objects.Warenkorb;
import wak.system.Formatter;
import wak.system.db.DB_Connector;
import wak.system.db.DB_Loader;
import wak.user.Kunde;
import wak.user.Mitarbeiter;
import wak.user.Person;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.UUID;

/**
 * Created by Crhistoph Nebendahl on 23.09.2015.
 * Login Klasse die gleichzeitig auch überprüft welche Person sich angemeldet hat.
 * @author Christoph Nebendahl
 * @version 1.1
 */
public class Login extends HttpServlet {

    Person p;
   static  String test;
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws javax.servlet.ServletException, IOException {
    DB_Connector.connecttoDatabase();

        if((request.getParameter("registrieren")!=null)&&(request.getParameter("registrieren").equals("Registrieren"))){
            String nextJSP = "/jsp/Registrierung.jsp";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
            dispatcher.forward(request, response);
        }else{
        if(request.getParameter("logout")!=null){
            Cookie[] cookies = request.getCookies();
            boolean cookie_vorhanden=false;
            Cookie cook=null;
            if(cookies!=null){
                for(int i=0;i<cookies.length;i++){
                    Cookie c = cookies[i];
                    if(c.getName().compareTo("id")==0){
                        cook = c;
                        cookie_vorhanden=true;
                        cook.setMaxAge(0);
                        response.addCookie(cook);
                        break;
                    }else{

                    }
                }
            }else{

            }
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }else{
            String nutzer= request.getParameter("user");
            String passwd=request.getParameter("passwd");

            //Hashen des Kennwortes für die Überprüfung
            String hashtext = Formatter.hashen(passwd);
        try {

            //Abfrage, ob es die KOmbination in der Datenbank gibt
            PrintWriter writer = response.getWriter();
            writer.println(hashtext);
            String nutzer_string = "SELECT passwort, id FROM softwareengineering2.nutzer WHERE benutzername=?;";
            PreparedStatement nutzer_ps = null;
            ResultSet nutzer_rs;
            nutzer_ps = DB_Connector.con.prepareStatement(nutzer_string);
            nutzer_ps.setString(1, nutzer);
            nutzer_rs = nutzer_ps.executeQuery();
            while(nutzer_rs.next()) {
                String pass = nutzer_rs.getString("passwort");
                String int_id = nutzer_rs.getString("id");
                writer.println(pass);
                if (hashtext.matches(pass)) {
                    test = nutzer;
                    UUID uuid = UUID.randomUUID();
                    Kunde kunde=null;
                    for(Kunde k: Seitenaufbau.kunde){
                        if (int_id.equals(k.getId())){
                            kunde=k;
                            kunde.setUuid(uuid);
                        }
                    }

                    //Entscheiden um was für eine Person es sich handelt
                    String c=null;
                    switch(int_id.charAt(0)){
                        case 'M':c="M";Mitarbeiter m = new Mitarbeiter(uuid, int_id, false);Seitenaufbau.mitarbeiter.add(m);break;
                        case 'A':c="M";Mitarbeiter a = new Mitarbeiter(uuid, int_id, true);Seitenaufbau.mitarbeiter.add(a);break;
                        case 'K':c="K";Warenkorb w = new Warenkorb(uuid.toString());Seitenaufbau.koerbe.add(w); kunde.setKorb(w);break;
                        default: request.getRequestDispatcher("index.jsp");
                    }
                    writer.print(c);

                    //Cookie
                    //Weiterleitung je nachdem was für eine Person sich angemeldet hat


                    String dispatcher="/jsp/redirect.html";



                    Cookie id = new Cookie("id", uuid.toString());
                    id.setDomain("localhost");
                    id.setPath("./");
                    response.addCookie(id);

                    RequestDispatcher d = getServletContext().getRequestDispatcher(dispatcher);
                    d.forward(request, response);
                } else {
                    //response.sendRedirect("./jsp/nologin.jsp");
                }
            }
            writer.close();
            }catch(SQLException e) {
        }

    }
        DB_Connector.closeDatabase();
    }}
    public static void getLogin(JspWriter writer, Cookie[] cookies){
        new DB_Loader();
       try {
        boolean cookie_vorhanden=false;
           if(cookies!=null){
                for(int i=0;i<cookies.length;i++){
                    Cookie c = cookies[i];
                    if(c.getName().compareTo("id")==0){
                        cookie_vorhanden=true;
                        break;
                    }else{
                    }
                }
           }else{

           }
            if(cookie_vorhanden){
                writer.print("<td class=\"login\">\n" +
                        "        <table style=\"width:100%\">\n" +
                        "          <tr>\n" +
                        "            <td align=\"center\">Hallo</td>\n" +
                        "          </tr>\n" +
                        "          <tr>\n" +
                        "            <td align=\"center\">"+test+"</td>\n" +
                                    "</tr>"+"<td><form action=\"/home\" method=\"post\"><input type=\"submit\" name=\"logout\" value=\"Logout\"></form></td>"+
                        "        </table>\n" +
                        "    </td>");

            }else{
                writer.print(" <td class=\"login\">\n" +
                        "      <form action=\"/home\" method=\"post\">\n" +
                        "        <table>\n" +
                        "          <tr>\n" +
                        "            <td align=\"center\">Benutzername:</td>\n" +
                        "          </tr>\n" +
                        "          <tr>\n" +
                        "            <td align=\"center\"><input type=\"text\" name=\"user\"/></td>\n" +
                        "          </tr>\n" +
                        "          <tr>\n" +
                        "            <td align=\"center\">Kennwort:</td>\n" +
                        "          </tr>\n" +
                        "          <tr>\n" +
                        "            <td align=\"center\"><input type=\"password\" name=\"passwd\"/></td>\n" +
                        "          </tr>\n" +
                        "          <tr><td align=\"center\"><input type=\"submit\" value=\"Login\" name=\"Login\"/><input type=\"submit\" value=\"Registrieren\" name=\"registrieren\"/></td></tr>\n" +
                        "        </table>\n" +
                        "      </form></td>");
            }
       }catch(IOException e){

       }

    }
}
