package kata5p1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Kata5P1 {

    private String name = "Kata5.db";
    
    public static void main(String[] args) {
        Kata5P1 kata5P1 = new Kata5P1();
        kata5P1.dbQuery();
    }
    
    private Connection connect(){
        Connection conn = null;
        String url = "jdbc:sqlite:" + name;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException ex) {
            System.out.println("" + ex.getMessage());
        }
        return conn;
    }
    
    private void close(Connection conn){
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println("" + ex.getMessage());
            }
        }
    }
    
    public void dbQuery(){
        Connection conn = null;
        String sql = "SELECT * FROM PEOPLE";
        
        try {
            conn = this.connect();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                System.out.println(rs.getInt("Id") + "\t" + rs.getString("Name") + "\t" + rs.getString("Apellidos") + "\t" + rs.getString("Departamento") + "\t");
            }
            
        } catch (SQLException ex) {
            System.out.println("" + ex.getMessage());
        } finally {
            close(conn);
        }   
    }
    
}
