package user_interface.table;

import entity.Orders;

import entity.dao.DaoOrders;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import mysql.DB;
import user_interface.MyTable;

public class TableOrdersPanel extends TablePanel {

    private JPanel panel = this;

    public TableOrdersPanel(DB db) {
        super(db);
        initComponents();
        remove(add);
        action();
    }

    @Override
    public void action() {
        delete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (table.getSelectedRow() == -1) {
                        JOptionPane.showMessageDialog(panel,
                                "Select the line you want to remove");
                    } else {
                        db.update("update orders set delete_status =1"
                                + " where orders_id ="
                                + table.getValueAt(table.getSelectedRow(), 0));
                        updateTable();
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(panel, "error in delete " + ex);
                }
            }
        });

        add.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                DaoOrders daoOrders = new DaoOrders(db);
                daoOrders.insert(new Orders());
                updateTable();
            }
        });

        change.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (table.getSelectedRow() != -1) {
                    try {
                        DaoOrders dop = new DaoOrders(db);
                        dop.update(new Orders(
                                Integer.valueOf(table.getValueAt(table.getSelectedRow(), 0).toString()), //orders_id
                                Integer.valueOf(table.getValueAt(table.getSelectedRow(), 3).toString()), //goods_count
                                table.getValueAt(table.getSelectedRow(), 4).toString() //payment
                        ));

                        updateTable();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(panel, "Incorrect data " + ex);
                        updateTable();
                    }
                } else {
                    JOptionPane.showMessageDialog(panel, "Select the line you want to change");
                }
            }
        });
    }

    @Override
    public void createTable() {
        try {
            table = new MyTable(db.query("SELECT "
                    + " orders_id,"
                    + " users_id,"
                    + " (SELECT item_name from goods WHERE orders.goods_id = goods.goods_id) as item_name,"
                    + " goods_count,"
                    + " payment "
                    + " FROM orders WHERE delete_status=0")) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    if (column == 0 || column == 1 || column == 2) {
                        return false;
                    } else {
                        return true;
                    }
                }
            };
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error creating table\n" + ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
