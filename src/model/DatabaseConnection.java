/*
    Clase principal referente a la conexión con la base de datos
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class DatabaseConnection {
    Statement ejecutor = null; //declaración de sql statement
    PreparedStatement preparar = null;
    /*Declaración de variables de conexión */
    public Connection conn;
    String driver = "com.mysql.cj.jdbc.Driver";
    String user = "root";
    String pass = "";
    String url = "jdbc:mysql://"
            + "/tic_tac_toe?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&allowMultiQueries=true&useSSL=false";

    public void getConnection() throws SQLException { //método que va a aperturar la conexión con la base de datos
        conn = null;
        try {
            Class.forName(driver);
            conn = (Connection) DriverManager.getConnection(url, user, pass);
            if (conn != null) {
                System.out.println("Conexión exitosa");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No funcionó la conexión");
            e.printStackTrace();
        }
    }

    public void closeConnection() { //método que va a cerrar la conexión con la base de datos
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}
