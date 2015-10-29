package wak.system.server;

import wak.objects.Kategorie;
import wak.system.db.DB_Connector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Created by chris_000 on 29.10.2015.
 */
@WebServlet(name = "KategorieAnlegen")
public class KategorieAnlegen extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        try {
            DB_Connector.connecttoDatabase();
            int oberkategorie;
            String einfuegen="INSERT INTO kategorie (name, oberkategorie) VALUES (?,?);";
            PreparedStatement einfuegen_ps = DB_Connector.con.prepareStatement(einfuegen);
            einfuegen_ps.setString(1, name);
            if (request.getParameter("oberkategorie") != null) {
                oberkategorie = Integer.parseInt(request.getParameter("oberkategorie"));
                einfuegen_ps.setInt(2, oberkategorie);
                einfuegen_ps.executeUpdate();
            } else{
                einfuegen_ps.setNull(2, Types.INTEGER);
                einfuegen_ps.executeUpdate();
            }
            String getId="SELECT `AUTO_INCREMENT`-1 AS id\n" +
                    "FROM  INFORMATION_SCHEMA.TABLES\n" +
                    "WHERE TABLE_SCHEMA = 'softwareengineering2'\n" +
                    "AND   TABLE_NAME   = 'kategorie';";
            PreparedStatement getId_ps = DB_Connector.con.prepareStatement(getId);
            ResultSet getID_rs = getId_ps.executeQuery();
            getID_rs.next();
            int id = getID_rs.getInt("id");
            Seitenaufbau.kategorien.add(new Kategorie(name, id));
        }catch(SQLException e1){
            e1.printStackTrace();
        }finally{

            DB_Connector.closeDatabase();
            String nextJSP = "/jsp/mitarbeiter/Uebersicht-Mitarbeiter.jsp";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
            dispatcher.forward(request, response);
        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
