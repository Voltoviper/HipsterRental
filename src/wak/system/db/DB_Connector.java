package wak.system.db;

import java.sql.*;

/**
 * Created by Christoph Nebendahl on 20.09.2015.
 */
public class DB_Connector {
final static String user = "hipster2";
    final static String passwd = "YozvCCcxbfE7Fddz";
    public static Connection con;
    public static void connecttoDatabase(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Fehler beim Laden des MySQL Treibers");
        }
        try {
               con = DriverManager.getConnection(
                       "jdbc:mysql://87.106.17.78/softwareengineering2", user, passwd);
           }catch(SQLException e){
               System.out.println("Fehler bei der Kommunikation mit der Datenbank");
                e.printStackTrace();
                       System.out.println(e.getErrorCode());
           }
    }

    public static void updateDatabase(String query){
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
    public static ResultSet QueryDatabase(String query){
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
