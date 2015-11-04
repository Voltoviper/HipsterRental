package wak.system.server;

import wak.objects.Bestellung;
import wak.system.db.DB_Connector;
import wak.system.email.emailservice;

import javax.mail.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by chris_000 on 04.11.2015.
 */
@WebServlet(name = "Bestellgenehmigung")
public class Bestellgenehmigung extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String bestell_id=request.getParameter("bestellid");
            int genehmigt=Integer.parseInt(request.getParameter("genehmigt"));

        try {
            DB_Connector.connecttoDatabase();
            if (genehmigt == 1) {
                String query = "UPDATE `softwareengineering2`.`bestellung` SET `genehmigt`=1 WHERE `id`=?";
                PreparedStatement query_ps = DB_Connector.con.prepareStatement(query);
                query_ps.setInt(1, Integer.parseInt(bestell_id));
                query_ps.executeUpdate();
                Session session = emailservice.getSession();
                Bestellung bestellung=null;
                for(Bestellung b:Seitenaufbau.bestellungen){
                    if(b.getId()==Integer.parseInt(bestell_id)){
                        bestellung=b;
                    }
                }
                emailservice.sendgenehmigung(session,  bestellung.getKunde(),bestellung, true);
            }else{
                String query = "DELETE FROM `softwareengineering2`.`bestellung` WHERE `id`=?";
                PreparedStatement query_ps = DB_Connector.con.prepareStatement(query);
                query_ps.setInt(1, Integer.parseInt(bestell_id));
                query_ps.executeUpdate();
                Session session = emailservice.getSession();
                Bestellung bestellung=null;
                for(Bestellung b:Seitenaufbau.bestellungen){
                    if(b.getId()==Integer.parseInt(bestell_id)){
                        bestellung=b;
                    }
                }
                emailservice.sendgenehmigung(session,  bestellung.getKunde(),bestellung, false);
            }
        }catch(SQLException e1){
            e1.printStackTrace();
        }finally{
            DB_Connector.closeDatabase();
        }
        String redirect ="/jsp/mitarbeiter/bestelluebersicht.jsp";
        RequestDispatcher d = getServletContext().getRequestDispatcher(redirect);
        d.forward(request, response);
    }

}
