package user_interface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import mysql.DB;
import mysql.WorkDB;

public class StartFrame extends JFrame {

    private JPanel panel;
    private JLabel labelLogin, labelPass, labelHost;
    private JTextField tfLogin, tfPass, tfHost;
    private JButton create, delete, connect;

    public StartFrame() {
        setSize(260, 240); //размер фрейма
        setLocationRelativeTo(null); //центрирование
        setDefaultCloseOperation(EXIT_ON_CLOSE); //закрываем пограмму
        setTitle("StartFrame");//название окна
        initComponents();//инициализация компонентов фрейма своим методом
        action();//обработчик нажатий
        setVisible(true);//отрисовка во фрейм - виден (true)
        setResizable(false);//запрет на изменение размера окна
    }

    private void initComponents() {
        panel = new JPanel();

        labelLogin = new JLabel("Login");
        labelPass = new JLabel("Pass");
        labelHost = new JLabel("Host");
        tfLogin = new JTextField("root", 20);
        tfPass = new JPasswordField(20);
        tfHost = new JTextField("localhost:3306", 20);
        create = new JButton("Create DB");
        delete = new JButton("Delete DB");
        connect = new JButton("Connect DB");

        panel.add(labelLogin);
        panel.add(tfLogin);
        panel.add(labelPass);
        panel.add(tfPass);
        panel.add(labelHost);
        panel.add(tfHost);
        panel.add(create);
        panel.add(connect);
        panel.add(delete);
        add(panel);
    }

    private void action() {

        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    WorkDB.create("jdbc:mysql://" + tfHost.getText() + "/", tfLogin.getText(), tfPass.getText());
                    JOptionPane.showMessageDialog(panel, "База данных создана", "Успех!", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(panel, ex, "MySQL Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    WorkDB.delete("jdbc:mysql://" + tfHost.getText() + "/", tfLogin.getText(), tfPass.getText());
                    JOptionPane.showMessageDialog(panel, "База данных удалена", "Успех!", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(panel, ex, "MySQL Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        connect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
                    DB db = new DB("jdbc:mysql://" + tfHost.getText() + "/" + WorkDB.NAME_DB, tfLogin.getText(), tfPass.getText());
                    new LoginFrame(db);
                } catch (SQLException ex) {
                    Logger.getLogger(StartFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });    
    }
}
