package wak.system.server;

import wak.system.db.DB_Connector;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.*;

/**
 * Created by Christoph Nebendahl on 04.11.2015.
 */
@WebServlet(name = "ImageServlet")
public class ImageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id= Integer.getInteger(request.getParameter("id"));
        String a = request.getParameter("item");
        ServletOutputStream out = response.getOutputStream();
        Blob photo = null;
        Statement stmt = null;
        ResultSet rs = null;
        String Query;

        try {
            DB_Connector.connecttoDatabase();
            switch(a){
                case "produkt": Query = "SELECT bild FROM bild WHERE Produktid=?";break;
                case "kategorie": Query="SELECT bild FROM kategorie WHERE id =?";break;
                case "produktmain": Query="SELECT bild FROM bild WHERE Produktid=? AND main=1";break;
                default: Query="";
            }

            PreparedStatement Query_ps=DB_Connector.con.prepareStatement(Query);
            Query_ps.setInt(1,id);
            ResultSet query_rs = Query_ps.executeQuery();
            query_rs.next();
            photo = query_rs.getBlob("bild");

            response.setContentType("image/gif");
            InputStream in = photo.getBinaryStream();
            int length = (int) photo.length();



            int bufferSize = 1024;
            byte[] buffer = new byte[bufferSize];

            while ((length = in.read(buffer)) != -1) {
                out.write(buffer, 0, length);
            }
            in.close();
            out.flush();
        }catch(SQLException e1){
            e1.printStackTrace();
        }finally{
            DB_Connector.closeDatabase();
        }
    }
    public static String getImage(int id, int a){
        StringBuffer s = new StringBuffer();
        InputStream in = null;
        try {
        //id= Integer.getInteger(request.getParameter("id"));
        // a = request.getParameter("item");
        Blob photo = null;
        Statement stmt = null;
        ResultSet rs = null;
        String Query;



            DB_Connector.connecttoDatabase();
            switch(a){
                case 1: Query = "SELECT bild FROM bild WHERE Produktid=?";break;
                case 2: Query="SELECT bild FROM kategorie WHERE id =?";break;
                case 3: Query="SELECT bild FROM bild WHERE Produktid=? AND main=1";break;
                default: Query="";
            }

            PreparedStatement Query_ps=DB_Connector.con.prepareStatement(Query);
            Query_ps.setInt(1,id);
            ResultSet query_rs = Query_ps.executeQuery();
            if(query_rs.next()) {
                photo = query_rs.getBlob("bild");

                in = photo.getBinaryStream();


                in.close();
            }
        }catch(SQLException e1){
            e1.printStackTrace();
        }catch(IOException e2){
            e2.printStackTrace();
        }finally{
            DB_Connector.closeDatabase();
        }
    return  fileToBuffer(in);
    }
    public static String fileToBuffer(InputStream is)  {
        StringBuilder sb = new StringBuilder();
        if (is != null) {


        try {
            try (BufferedReader rdr = new BufferedReader(new InputStreamReader(is))) {
                for (int c; (c = rdr.read()) != -1; ) {
                    sb.append((char) c);
                }
            }
        }catch(IOException e1){
            e1.printStackTrace();
        }return sb.toString();
        }else{
            return "";
        }

    }
}
