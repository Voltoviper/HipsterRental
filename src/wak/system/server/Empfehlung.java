package wak.system.server;

import wak.system.db.DB_Connector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Christoph Nebendahl on 24.09.2015.
 */
public class Empfehlung extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Generieren einer BestellID
        try{
            String empfehlung_string = "SELECT id FROM softwareengineering2.bestellung order by id DESC;";
            PreparedStatement empfehlung_state = DB_Connector.con.prepareStatement(empfehlung_string);
            ResultSet empfehlung_set = empfehlung_state.executeQuery();
            resp.setContentType("text/html");
            while(empfehlung_set.next()){
                int empfehlung_id=empfehlung_set.getInt("id");
                Writer out = resp.getWriter();
                out.write("Test");
            }
            req.getRequestDispatcher("data.jsp").forward(req, resp);

        }catch(SQLException e){

        }

    }
    public static void getEmpfehlungen(JspWriter stream){

        try {
            int zaehler=0;
            for(int i=0;i<5;i++){
                stream.print("<tr>\n");
                for(int ii=0;ii<3;ii++){
                    stream.print("<td style=\"width:33%; text-align:center\">");
                    stream.print("Empfehlung" +zaehler);
                    stream.print("</td>\n");
                    zaehler++;
                }
                stream.print("</tr>\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
