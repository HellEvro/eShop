package user_interface.table;

import entity.Users;
import entity.dao.DaoUsers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import mysql.DB;
import user_interface.MyTable;

public class TableUsersPanel extends TablePanel {

    private JPanel panel = this;

    public TableUsersPanel(DB db) {
        super(db);
        super.initComponents();
        action();
    }

    @Override
    public void action() {
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (table.getSelectedRow() == -1) {
                        JOptionPane.showMessageDialog(panel, "Необходимо выбрать строку для редактирования!");
                    } else {
                        db.update("update users set delete_status = 1 "
                                + "where users_id ="
                                + table.getValueAt(table.getSelectedRow(), 0));
                        updateTable();
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(panel, "Ошибка удаления " + ex);
                }

            }
        });

        add.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                DaoUsers daoUsers = new DaoUsers(db);
                daoUsers.insert(new Users("", "", 1, 0));
                updateTable();

            }
        });

        change.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (table.getSelectedRow() != -1) {
                    try {
                        int role = 0;
                        if (table.getValueAt(table.getSelectedRow(), 4).
                                toString().equalsIgnoreCase("user")) {
                            role = 1;
                        } else {
                            if (table.getValueAt(table.getSelectedRow(), 4).toString().equalsIgnoreCase("admin")) {
                                role = 0;
                            } else {
                                throw new SQLException("error role");
                            }
                        }
                        int block_status = 0;
                        if (table.getValueAt(table.getSelectedRow(), 5).toString().equalsIgnoreCase("active")) {
                            block_status = 0;
                        } else {
                            if (table.getValueAt(table.getSelectedRow(), 5).toString().equalsIgnoreCase("blocked")) {
                                block_status = 1;
                            } else {
                                throw new SQLException(""
                                        + "error block_status");
                            }
                        }
                        DaoUsers du = new DaoUsers(db);

                        du.update(new Users(
                                Integer.valueOf(table.getValueAt(table.getSelectedRow(), 0).toString()),
                                table.getValueAt(table.getSelectedRow(), 1).toString(),
                                table.getValueAt(table.getSelectedRow(), 2).toString(),
                                Integer.valueOf(table.getValueAt(table.getSelectedRow(), 3).toString()),
                                role,
                                block_status
                        ));
                        updateTable();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(panel, "Incorrect data " + ex);
                        updateTable();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(panel, "Incorrect data " + ex);
                        updateTable();
                    }
                } else {
                    JOptionPane.showMessageDialog(panel, "Необходимо выбрать сроку для изменения");
                }
            }
        });

    }

    @Override
    public void createTable() {
        try {
            table = new MyTable(db.query("select "
                    + "users_id,"
                    + "login,"
                    + "password,"
                    + "balance, "
                    + "case role when 1 then 'user' when 0 then 'admin' end as role,"
                    + "IF(block_status=1,'Blocked','Active')as blocked_status "
                    + "from users where delete_status=0")) {
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
