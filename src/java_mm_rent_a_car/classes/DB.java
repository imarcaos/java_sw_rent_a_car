package java_mm_rent_a_car.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author Marcos Melo
 */
public class DB {
    // Module Connection
    
    // Connection Parameters
    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String serverName = "localhost";
    private static int portNumber = 3306;
    private static String dbName = "mc_car_rental";
    private static String userName = "root";
    private static String userPass = "";    
    private static String url = "jdbc:mysql://"+serverName+":"+portNumber+"/"+dbName;
    
    // Metod to create and return connection
    public static Connection getConnection() {        
        Connection conn = null;
        
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, userName, userPass);
            return conn;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro de Conexão: " + e.getMessage());
            return null;
        }
    }
    
    
}
