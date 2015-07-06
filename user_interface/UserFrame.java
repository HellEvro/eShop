package user_interface;

import entity.dao.DaoGoods;
import entity.dao.DaoOrders;
import entity.dao.DaoUsers;
import entity.Goods;
import entity.Users;
import mysql.DB;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import javax.swing.*;

/**
 *
 * @author teacher
 */
class UserFrame extends JFrame {

    private DB db;
    private JPanel itemList;
    private JScrollPane scrollPane;
    private JButton logout;
    private JPanel userInfo;
    private JButton order;
    private ArrayList<ListOfGoods> goodsList;
    private Users currentUser;
    private JLabel balancelabel;
    
    public UserFrame(DB db, Users user) {
        this.goodsList = new ArrayList<ListOfGoods>();
        this.db = db;
        this.currentUser = user;
        setSize(600, 600);
        setTitle("UserFrame");
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.userInfo = new JPanel();
        this.userInfo.add(new JLabel("Привет, "));
        this.balancelabel = new JLabel("" + user.getBalance() + "$ ");
        this.userInfo.add(new JLabel(user.getLogin() + "! "));
        this.userInfo.add(new JLabel("Баланс: "));
        this.userInfo.add(balancelabel);
        this.logout = new JButton("Выйти");
        this.logout.setSize(100, 100);
        this.userInfo.add(this.logout);
        add(this.userInfo, BorderLayout.NORTH);

        DaoGoods daoGoods = new DaoGoods(db);
        ArrayList<Goods> goods = null;
        int position = 100;
        try {
            goods = daoGoods.selectAll();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        itemList = new JPanel(new GridLayout(goods.size(), 1));
        itemList.setPreferredSize(new Dimension(500, goods.size() * 200));
        for (Goods i : goods) {
            ListOfGoods tmp = new ListOfGoods(i, position);
            this.itemList.add(tmp);
            this.goodsList.add(tmp);
        }
        this.scrollPane = new JScrollPane(this.itemList);
        this.scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        add(this.scrollPane, BorderLayout.CENTER);
        this.order = new JButton("Сформировать заказ");
        this.order.setSize(100, 100);
        add(this.order, BorderLayout.SOUTH);
        setVisible(true);
        actions();
    }

    public void actions() {
        logout.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginFrame(db);
                dispose();
            }
        });
        order.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                HashMap<Goods, Integer> choosenGoods = new HashMap<Goods, Integer>();
                int totalcost = 0;
                for (ListOfGoods shopItem : goodsList) {
                    if (shopItem.isChoosen()) {
                        choosenGoods.put(shopItem.getCurrentItem(), shopItem.getAmount());
                        totalcost += shopItem.getCurrentItem().getItem_price() * shopItem.getAmount();
                    }
                }
                if (currentUser.getBalance() >= totalcost) {
                    int lastInsertID = 0;
                    try {
                        lastInsertID = new DaoOrders(db).getLastInsertId();
                    } catch (SQLException ex) {
                    }
                    StringBuilder sb = new StringBuilder("INSERT INTO orders VALUES ");
                    for (Entry<Goods, Integer> entry : choosenGoods.entrySet()) {
                        sb.append("('" + lastInsertID + "',"
                                + "'" + currentUser.getUsers_id() + "',"
                                + "'" + entry.getKey().getGoods_id() + "',"
                                + "'" + entry.getValue() + "',"
                                + "'processing','0'),");
                    }
                    String result = sb.toString();
                    try {
                        db.update(result.substring(0, result.length() - 1));
                        balancelabel.setText("" + (currentUser.getBalance() - totalcost) + "$ ");
                        currentUser.setBalance(currentUser.getBalance() - totalcost);
                        new DaoUsers(db).updateBalance(currentUser);
                        JOptionPane.showMessageDialog(null, "Общая стоимость заказа: " + totalcost, "Заказ успешно сформирован", 1);
                        
                    } catch (SQLException ex) {
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Общая стоимость заказа: " + totalcost + ". \nЭто превышает Ваш баланс", "Ошибка заказа", 1);
                }
            }
        });
    }
}
