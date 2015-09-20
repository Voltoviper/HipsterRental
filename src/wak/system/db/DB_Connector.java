package wak.system.db;

import java.sql.*;

/**
 * Created by Christoph Nebendahl on 20.09.2015.
 */
public class DB_Connector {

    public void connectToAndQueryDatabase(String username, String password) throws SQLException {

        Connection con = DriverManager.getConnection(
                "jdbc:myDriver:myDatabase",
                username,
                password);

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT a, b, c FROM Table1");

        while (rs.next()) {
            int x = rs.getInt("a");
            String s = rs.getString("b");
            float f = rs.getFloat("c");
        }
    }
}
