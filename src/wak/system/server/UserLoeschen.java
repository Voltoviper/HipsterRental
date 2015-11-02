package wak.system.server;

import wak.system.db.DB_Connector;
import wak.user.Kunde;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by chris_000 on 02.11.2015.
 */
@WebServlet(name = "UserLoeschen")
public class UserLoeschen extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        boolean cookie_vorhanden=false;
        Cookie cook=null;
        if(cookies!=null){
            for(int i=0;i<cookies.length;i++){
                Cookie c = cookies[i];
                if(c.getName().compareTo("id")==0){
                    cook = c;
                    cookie_vorhanden=true;
                    break;
                }else{
                }
            }
        }else{

        }
        if(cookie_vorhanden){
            Kunde k = Seitenaufbau.getKunde(cook.getValue());
            try {
                DB_Connector.connecttoDatabase();
                String kunde = "DELETE FROM `softwareengineering2`.`nutzer` WHERE `id`=?";
                PreparedStatement kunde_ps = DB_Connector.con.prepareStatement(kunde);
                kunde_ps.setString(1, k.getId());
                kunde_ps.executeUpdate();
            }catch(SQLException e1){
                e1.printStackTrace();
            }
        }
        String nextJSP = "/index.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
