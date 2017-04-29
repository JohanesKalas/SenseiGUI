/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.*;

/**
 *
 * @author Amagi
 */
public class Tester {
    public static void main(String[] args) throws ClassNotFoundException, SQLException{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection conn = DriverManager.getConnection("jdbc:sqlserver://10.100.70.70\\PBD2017;user=i14039;password=i14039;database=i14039");
		Statement sta = conn.createStatement();
		String Sql = "select * from peminjaman;";
		ResultSet rs = sta.executeQuery(Sql);
		while (rs.next()) {
			System.out.println(rs.getString("tanggal_pinjam"));
		}
        /*try {
            String dbURL = "jdbc:sqlserver://10.100.70.70\\PBD2017;User=i14039;passi14039;DatabaseName=i14039";
            //conn = DriverManager.getConnection(dbURL, user, pass);
            if(conn != null){
                DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
                System.out.println("Driver name: "+dm.getDriverName());
                System.out.println("Driver version: "+dm.getDriverVersion());
                System.out.println("Product name: "+dm.getDatabaseProductName());
                System.out.println("Product version: "+dm.getDatabaseProductVersion());
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        } finally {
            try{
                if(conn != null&& !conn.isClosed()){
                    conn.close();
                }
            } catch(SQLException ex){
                ex.printStackTrace();
            }
        }*/
       
    }
}
