package entity.dao;

import com.mysql.jdbc.PreparedStatement;
import entity.Goods;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import mysql.DB;

/**
 *
 * @author Evro
 */
public class DaoGoods implements DAOInterface<Goods> {

    private DB db;

    public DaoGoods(DB db) {
        this.db = db;
    }

    @Override
    public void insert(Goods ob) throws SQLException {

        PreparedStatement ps = (PreparedStatement) db.getCn().prepareStatement(
                "insert into " + ob.getClass().getSimpleName()
                + " (item_name,item_description,item_price)"
                + " values(?,?,?)");
        ps.setString(1, ob.getItem_name());
        ps.setString(2, ob.getItem_description());
        ps.setInt(3, ob.getItem_price());
        ps.execute();

    }

    @Override
    public void delete(Goods ob) throws SQLException {
        db.update("update " + ob.getClass().getSimpleName()
                + " set delete_status =" + ob.getDelete_status()
                + " where goods_id = " + ob.getGoods_id());
    }

    @Override
    public void update(Goods ob) throws SQLException {

        PreparedStatement ps = null;

        ps = (PreparedStatement) db.getCn().prepareStatement("update "
                + ob.getClass().getSimpleName() + " set item_name=?,"
                + " item_description =?, item_price =?"
                + " where goods_id=" + ob.getGoods_id());

        ps.setString(1, ob.getItem_name());
        ps.setString(2, ob.getItem_description());
        ps.setInt(3, ob.getItem_price());
        ps.execute();

    }

    public ArrayList<Goods> selectAll() throws SQLException {
        ArrayList<Goods> tmp = new ArrayList<Goods>();
        ResultSet rs = this.db.query("SELECT * FROM goods");
        while (rs.next()) {
            tmp.add(new Goods(
                    rs.getInt("goods_id"), 
                    rs.getString("item_name"),
                    rs.getString("item_description"),
                    rs.getInt("item_price")
            ));
        }
        return tmp;
    }


}
