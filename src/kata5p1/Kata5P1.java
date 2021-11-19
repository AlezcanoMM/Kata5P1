package kata5p1;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Kata5P1 {

    private String name = "Kata5.db";
    
    public static void main(String[] args) {
        Kata5P1 kata5P1 = new Kata5P1();
        kata5P1.dbQuery();
        kata5P1.createNewTable();
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
    
    public void createNewDatabase(){
        
        Connection conn = null;
        String url = "jdbc:sqlite:" + name;
        
        try {
            conn = DriverManager.getConnection(url);
            if(conn != null){
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("El driver es " + meta.getDriverName());
                System.out.println("Se ha creado una nueva BD.");
            }
        } catch (SQLException ex) {
            System.out.println("" + ex.getMessage());
        } finally {
            close(conn);
        }
        
    }
    
    public void createNewTable(){
        
        Connection conn = null;
        String sql = "CREATE TABLE IF NOT EXISTS EMAIL (\n"
                + " Id integer PRIMARY KEY AUTOINCREMENT, \n"
                + " Mail text NOT NULL);";
        
        try {
            conn = this.connect();
            Statement st = conn.createStatement();
            st.execute(sql);
        } catch (SQLException ex) {
            System.out.println("" + ex.getMessage());
        } finally {
            close(conn);
        }
        
    }
    
    public void insertData(String email){
        
        Connection conn = null;
        String sql = "INSERT INTO CORREOS(Direccion) VALUES (?)";
        
        try {
            conn = this.connect();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, email);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("" + ex.getMessage());
        } finally {
            close(conn);
        }
        
    }
    
}
