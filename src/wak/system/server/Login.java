package wak.system.server;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
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

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
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
            UUID uuid = UUID.randomUUID();
            Cookie id =
                    new Cookie("id", uuid.toString());
            response.addCookie(id);
            response.sendRedirect("./jsp/home.jsp");
        }else{
            writer.println("<html>");
            writer.println("<head><title>Hello World Servlet</title></head>");
            writer.println("<body>");
            writer.println("	<h1>Zugang verweigert </h1>");
            writer.println("<body>");
            writer.println("</html>");
        }



        writer.close();
    }
}
