package user_interface.table;

import entity.Goods;
import entity.dao.DaoGoods;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import mysql.DB;
import user_interface.MyTable;

/**
 *
 * @author teacher
 */
public class TableGoodsPanel extends TablePanel {

    private JPanel panel = this;

    public TableGoodsPanel(DB db) {
        super(db);
        initComponents();
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
                        db.update("update goods set delete_status =1 "
                                + "where goods_id ="
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
                DaoGoods daoGoods = new DaoGoods(db);
                try {
                    daoGoods.insert(new Goods());
                } catch (SQLException ex) {
                    Logger.getLogger(TableGoodsPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                updateTable();
            }
        });

        change.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (table.getSelectedRow() != -1) {
                    try {
                        DaoGoods dg = new DaoGoods(db);
                        dg.update(new Goods(
                                Integer.valueOf(table.getValueAt(table.getSelectedRow(), 0).toString()), //goods_id
                                table.getValueAt(table.getSelectedRow(), 1).toString(), //item_name
                                table.getValueAt(table.getSelectedRow(), 2).toString(), //item_description
                                Integer.valueOf(table.getValueAt(table.getSelectedRow(), 3).toString()) //item_price
                        ));
                        updateTable();
                    } catch (NumberFormatException | SQLException ex) {
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
            table = new MyTable(db.query("SELECT goods_id,item_name,item_description, item_price FROM goods  where delete_status=0")) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    if (column == 0) {
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
