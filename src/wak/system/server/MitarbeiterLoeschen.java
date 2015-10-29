package wak.system.server;

import wak.system.db.DB_Connector;

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
 * Created by chris_000 on 29.10.2015.
 */
@WebServlet(name = "MitarbeiterLoeschen")
public class MitarbeiterLoeschen extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String MitarbeiterNummer = request.getParameter("id");
        DB_Connector.connecttoDatabase();
        try {
            String mitarbeiterloeschen = "DELETE FROM `softwareengineering2`.`nutzer` WHERE `id`=? ;";
            PreparedStatement mitarbeiterloeschen_ps = DB_Connector.con.prepareStatement(mitarbeiterloeschen);
            mitarbeiterloeschen_ps.setString(1,MitarbeiterNummer);
            mitarbeiterloeschen_ps.executeUpdate();
        }catch(SQLException e1){
            e1.printStackTrace();
        }finally{
            DB_Connector.closeDatabase();
            String nextJSP = "/jsp/mitarbeiter/Uebersicht-Mitarbeiter.jsp";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
            dispatcher.forward(request, response);
        }
    }
}
