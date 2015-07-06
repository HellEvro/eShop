package entity.dao;

import com.mysql.jdbc.PreparedStatement;
import entity.Orders;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mysql.DB;

public class DaoOrders implements DAOInterface<Orders> {

    private DB db;

    public DaoOrders(DB db) {
        this.db = db;
    }

    @Override
    public void insert(Orders ob) {
        try {
            PreparedStatement ps = (PreparedStatement) db.getCn().prepareStatement(
                    "insert into " + ob.getClass().getSimpleName() + " (users_id,goods_id,goods_count,payment) values(?,?,?,?)");
            ps.setInt(1, ob.getUsers_id());
            ps.setInt(2, ob.getGoods_id());
            ps.setInt(3, ob.getGoods_count());
            ps.setString(4, ob.getPayment());
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("Ошибка запроса в БД: " + ex);
        }
    }

    @Override
    public void delete(Orders ob) throws SQLException{
        db.update("update " + ob.getClass().getSimpleName()
                + " set delete_status =" + ob.getDelete_status()
                + " where orders_id = " + ob.getOrders_id());
    }

    @Override
    public void update(Orders ob) {
        try {
            PreparedStatement ps = null;
            try {
                ps = (PreparedStatement) db.getCn().prepareStatement("UPDATE "
                        + ob.getClass().getSimpleName() + " SET "
                        + " goods_count=?, "
                        + " payment=? "
                        + " where orders_id=" + ob.getOrders_id());
            } catch (SQLException ex) {
                Logger.getLogger(DaoOrders.class.getName()).log(Level.SEVERE, null, ex);
            }
            ps.setInt(1, ob.getGoods_count());
            ps.setString(2, ob.getPayment());
            ps.execute();

        } catch (SQLException ex) {
            Logger.getLogger(DaoOrders.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
        public int getLastInsertId() throws SQLException {
        ResultSet rs = db.query("SELECT LAST_INSERT_ID() FROM orders");
        rs.next();
        return rs.getInt(1);
    }


}
