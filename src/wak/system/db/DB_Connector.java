package wak.system.db;

import java.sql.*;

/**
 * Created by Christoph Nebendahl on 20.09.2015.
 */
public class DB_Connector {
final static String user = "test";
    final static String passwd = "qwertz123!";
    Connection con;
    private void connecttoDatabase(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Fehler beim Laden des MySQL Treibers");
        }
        try {
               con = DriverManager.getConnection(
                       "jdbc:mysql://localhost/softwareengineering", user, passwd);
           }catch(SQLException e){
               System.out.println("Fehler bei der Kommunikation mit der Datenbank");
                       System.out.println(e.getErrorCode());
           }
    }

    public void updateDatabase(String query){
        try{
            connecttoDatabase();
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch(SQLException e1){
            System.out.println("Problem mit der Datenbankabfrage");
            e1.printStackTrace();
        }
        try {
            if(!con.isClosed()) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public ResultSet QueryDatabase(String query){
        ResultSet rs = null;
        try {
            if(con==null|con.isClosed()){
                connecttoDatabase();
                Statement state = con.createStatement();
              rs = state.executeQuery(query);
            }
        } catch (SQLException e) {
           System.out.println("Fehler bei der Datenbank");
        }
        if(rs==null){
            return null;
        }else{
            return rs;
        }
    }
}
