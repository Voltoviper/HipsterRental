package wak.system.db;

import java.sql.*;

/**
 * Created by Christoph Nebendahl on 20.09.2015.
 */
public class DB_Connector {

    public void connectToAndQueryDatabase() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost/softwareengineering",
                    "root",
                    "Amerikaner11");

            Statement stmt = con.createStatement();
            stmt.executeUpdate("INSERT INTO kategorie (name, bild_pfad)"  + "VALUES ('Soundanlage','C:')");
            //stmt.executeUpdate("INSERT INTO Customers " + "VALUES (1002, 'McBeal', 'Ms.', 'Boston', 2004)");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
