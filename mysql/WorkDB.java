package mysql;

import entity.Goods;
import entity.Orders;
import entity.Users;
import entity.dao.DaoGoods;
import entity.dao.DaoOrders;
import entity.dao.DaoUsers;
import java.sql.SQLException;

public class WorkDB {

    public static final String NAME_DB = "eshop";

    public static void create(String url, String user, String password) throws SQLException {
        DB db = new DB(url, user, password);
        db.update("create database " + NAME_DB + " DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci");
        db.update("use " + NAME_DB);
        db.update("CREATE table users ("
                + "users_id int auto_increment primary key,"
                + "login varchar(30) not null unique, "
                + "password varchar(128) not null, "
                + "role int(1),"
                + "balance int DEFAULT '0',"
                + "block_status int(1) DEFAULT '0',"
                + "delete_status int(1) NOT NULL DEFAULT '0')");
        DaoUsers du = new DaoUsers(db);
        du.insert(new Users("admin", "3333", 0, 1000000));
        du.insert(new Users("user", "0000", 1, 200000));
        du.insert(new Users("user1", "1111", 1, 100000));
        du.insert(new Users("user2", "2222", 1, 10000));

        db.update("CREATE table goods ("
                + " goods_id int auto_increment primary key, "
                + " item_name varchar(30), "
                + " item_description text, "
                + " item_price int, "
                + " delete_status int(1) NOT NULL DEFAULT '0')");
        DaoGoods dg = new DaoGoods(db);
        dg.insert(new Goods("HP 2000", "Server 19 stack suite", 1024));
        dg.insert(new Goods("Mouse G20", "Logitech mouse - dark laser technology", 30));
        dg.insert(new Goods("Keybord G510", "Logitech gaming keyboard", 50));
        dg.insert(new Goods("HDD 2Tb", "Seagate HDD for Servers", 199));

        db.update("CREATE table orders ("
                + " orders_id int auto_increment primary key,"
                + " users_id int,"
                + " goods_id int,"
                + " goods_count int,"
                + " payment ENUM('processing','reject','payed'),"
                + " delete_status int(1) NOT NULL DEFAULT '0',"
                + " foreign key(users_id) references users(users_id),"
                + " foreign key(goods_id) references goods(goods_id))");
        DaoOrders dor = new DaoOrders(db);
        dor.insert(new Orders(2, 4, 10, "processing"));
        dor.insert(new Orders(3, 1, 1, "payed"));
        dor.insert(new Orders(2, 3, 100, "reject"));
        dor.insert(new Orders(1, 2, 100, "reject"));
        dor.insert(new Orders(3, 4, 10, "processing"));
        
    }

    public static void delete(String url, String user, String password) throws SQLException {
        DB db = new DB(url, user, password);
        db.update("drop database " + NAME_DB);
        db.close();
    }
}
