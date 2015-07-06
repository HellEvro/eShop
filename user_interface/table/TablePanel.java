/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user_interface.table;

import javax.swing.*;
import mysql.DB;
import user_interface.MyTable;

/**
 *
 * @author teacher
 */
public abstract class TablePanel extends JPanel {

    protected DB db;
    protected JScrollPane scroll;
    protected MyTable table;
    protected JButton delete, add, change, logout;

    public TablePanel(DB db) {
        this.db = db;
        setLayout(null);
    }

    public void initComponents() {
        createTable();
        scroll = new JScrollPane(table);
        scroll.setBounds(5, 5, 650, 300);
        delete = new JButton("Удалить");
        add = new JButton("Добавить");
        change = new JButton("Изменить");
        
        add.setBounds(20, 350, 100, 20);
        change.setBounds(160, 350, 100, 20);
        delete.setBounds(300, 350, 100, 20);
        
        add(scroll);
        add(add);
        add(delete);
        add(change);
        //setBackground(Color.red);
    }

    public abstract void action();

    public abstract void createTable();

    public void updateTable() {
        remove(scroll);
        createTable();
        scroll = new JScrollPane(table);
        scroll.setBounds(5, 5, 650, 300);
        add(scroll);
        updateUI();
    }

}
