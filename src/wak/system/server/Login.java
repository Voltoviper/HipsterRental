package wak.system.server;

import wak.objects.Warenkorb;
import wak.user.Person;

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
import java.util.Enumeration;
import java.util.UUID;

/**
 * Created by Crhistoph Nebendahl on 23.09.2015.
 */
public class Login extends HttpServlet {

    Person p;
   static  String test;
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String nutzer= request.getParameter("user");
        String passwd=request.getParameter("passwd");
        Enumeration<String> names= request.getParameterNames();
        String hashtext = "nichts";
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(passwd.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            hashtext = number.toString(16);
        }catch(NoSuchAlgorithmException e){
            System.out.println("Fehler bei der Passwort Bearbeitung");
        }

        PrintWriter writer = response.getWriter();

        if(passwd.matches("test")){
            test=nutzer;
            request.setAttribute("user", nutzer);
            UUID uuid = UUID.randomUUID();
            Seitenaufbau.koerbe.add(new Warenkorb(uuid.toString()));
            Cookie id =
                    new Cookie("id", uuid.toString());
            id.setDomain("localhost");
            id.setPath("./");
            response.addCookie(id);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }else{
            response.sendRedirect("./jsp/nologin.jsp");
        }
        writer.close();
    }
    public static void getLogin(JspWriter writer, Cookie[] cookies){
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
