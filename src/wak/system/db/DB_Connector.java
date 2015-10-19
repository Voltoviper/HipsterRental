package wak.system.db;

import java.sql.*;

/**
 * Created by Christoph Nebendahl on 20.09.2015.
 * @author Christoph Nebendahl
 */
public class DB_Connector {
    final static String user = "hipster2";
    final static String passwd = "YozvCCcxbfE7Fddz";
    public static Connection con;

    /**
     * Datenbank wird verbunden
     */
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
    public static void closeDatabase(){
        try {
            if(!con.isClosed()){
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
