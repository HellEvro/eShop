package entity.dao;

import com.mysql.jdbc.PreparedStatement;
import entity.Users;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mysql.DB;

public class DaoUsers implements DAOInterface<Users> {

    private DB db;

    public DaoUsers(DB db) {
        this.db = db;
    }

    @Override
    public void insert(Users ob) {
        try {
            PreparedStatement ps = (PreparedStatement) db.getCn().prepareStatement("INSERT INTO "
                    + ob.getClass().getSimpleName() + " (login,password,role,balance)"
                    + " values(?,?,?,?)");
            ps.setString(1, ob.getLogin());
            ps.setString(2, ob.getPassword());
            ps.setInt(3, ob.getRole());
            ps.setInt(4, ob.getBalance());
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("Ошибка запроса в БД: " + ex);

        }
    }

    @Override
    public void delete(Users ob) throws SQLException {
        db.update("update " + ob.getClass().getSimpleName()
                + " set delete_status =" + ob.getDelete_status()
                + " where users_id = " + ob.getUsers_id());
    }

    public void blockUser(Users ob) throws SQLException {
        db.update("update " + ob.getClass().getSimpleName()
                + " set block_status=" + ob.getDelete_status()
                + " where users_id=" + ob.getUsers_id());
    }

    public void setBalance(Users ob) throws SQLException {
        db.update("update " + ob.getClass().getSimpleName()
                + " set balance=" + ob.getDelete_status()
                + " where users_id=" + ob.getUsers_id());
    }

    @Override
    public void update(Users ob) {
        try {
            PreparedStatement ps = null;
            try {
                ps = (PreparedStatement) db.getCn().prepareStatement("update "
                        + ob.getClass().getSimpleName() + " set login=?,"
                        + " password=?, role=?, balance=? , block_status=?"
                        + " where users_id=" + ob.getUsers_id());
            } catch (SQLException ex) {
                Logger.getLogger(DaoUsers.class.getName()).log(Level.SEVERE, null, ex);
            }
            ps.setString(1, ob.getLogin());
            ps.setString(2, ob.getPassword());
            ps.setInt(3, ob.getRole());
            ps.setInt(4, ob.getBalance());
            ps.setInt(5, ob.getBlock_status());
            System.out.println(ps);
            ps.execute();

        } catch (SQLException ex) {
            Logger.getLogger(DaoUsers.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void updateBalance(Users ob) throws SQLException {
        PreparedStatement ps = (PreparedStatement) db.getCn().prepareStatement(
                "UPDATE " + ob.getClass().getSimpleName()
                + " SET balance =? WHERE login='"
                + ob.getLogin() + "'");
        ps.setInt(1, ob.getBalance());
        ps.execute();

    }

    public Users getUser(ResultSet rs) throws SQLException {
        return new Users(rs.getInt("users_id"),
                rs.getString("login"),
                rs.getString("password"),
                rs.getInt("role"),
                rs.getInt("balance"),
                rs.getInt("block_status")
        );
    }

}
