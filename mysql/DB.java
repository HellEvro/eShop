package mysql;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DB {

    private Connection cn;
    private Statement st;
    private ResultSet rs;

    public Connection getCn() {
        return cn;
    }

    public DB(String url, String user, String password) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        
                cn = (Connection) DriverManager.getConnection(url, user, password);
                st = (Statement) cn.createStatement();
       
        } catch (ClassNotFoundException ex) {
            System.out.println("ошибка при загрузке драйвера " + ex);
        }
    }

    public void update(String sql) throws SQLException {

            st.executeUpdate(sql);
       
    }

    public ResultSet query(String sql) throws SQLException {
     
            rs = st.executeQuery(sql);
   
        return rs;
    }

    public void showResultSet(ResultSet rs) {
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                System.out.print(rsmd.getColumnName(i) + "\t");
            }
            while (rs.next()) {
                System.out.println();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    System.out.print(rs.getString(i) + "\t");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void close() {
        try {
            st.close();
            cn.close();
        } catch (SQLException ex) {
            System.out.println("error in close " + ex);
        }
    }

}
