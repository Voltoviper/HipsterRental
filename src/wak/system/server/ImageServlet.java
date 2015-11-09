package wak.system.server;

import com.sun.org.apache.xml.internal.security.utils.Base64;
import sun.misc.IOUtils;
import wak.system.db.DB_Connector;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
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
        String image ="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";
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
                byte[] buf = org.apache.commons.io.IOUtils.toByteArray(in);
                // this throws an exception, message and cause both null as above
                String photo64 = DatatypeConverter.printBase64Binary(buf);
                image=photo64;
            }
        }catch(SQLException e1){
            e1.printStackTrace();
        }catch(IOException e2){
            e2.printStackTrace();
        }finally{
            DB_Connector.closeDatabase();
        }
    return  image;
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
