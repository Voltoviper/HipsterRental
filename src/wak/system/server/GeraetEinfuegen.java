package wak.system.server;

import wak.system.db.DB_Connector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by chris_000 on 28.10.2015.
 */
@WebServlet(name = "GeraetEinfuegen")
public class GeraetEinfuegen extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int seriennummer=0;

        DB_Connector.connecttoDatabase();
        try {
            String sql = "SELECT MAX(seriennummer)+1 AS seriennummer FROM gereat WHERE produktid=?";
            PreparedStatement sql_ps = DB_Connector.con.prepareStatement(sql);
            ResultSet sql_rs=sql_ps.executeQuery();
            sql_rs.next();
            seriennummer=sql_rs.getInt("seriennummer");

        }catch(SQLException e1){

        }finally{
            DB_Connector.closeDatabase();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
