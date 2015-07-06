package user_interface;

import entity.dao.DaoUsers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import mysql.DB;

public class LoginFrame extends JFrame {

    private JPanel panel;
    private JLabel labelLogin, labelPass;
    private JTextField tfLogin, tfPass;
    private JButton enter, register, back;
    private LoginFrame login;
    private DB db;

    public LoginFrame(DB db) {
        this.db = db;
        setSize(260, 160); //размер фрейма
        setLocationRelativeTo(null); //центрирование
        setDefaultCloseOperation(EXIT_ON_CLOSE); //закрываем пограмму
        setTitle("LoginFrame");//название окна
        initComponents();//инициализация компонентов фрейма своим методом
        action();//обработчик нажатий
        setVisible(true);//отрисовка во фрейм - виден (true)
        setResizable(false);//запрет на изменение размера окна
    }

    private void initComponents() {
        panel = new JPanel();

        labelLogin = new JLabel("Login");
        labelPass = new JLabel("Pass");

        tfLogin = new JTextField("user", 20);
        tfPass = new JPasswordField("0000", 20);

        back = new JButton("<");
        enter = new JButton("Login");
        register = new JButton("Registration");

        panel.add(labelLogin);
        panel.add(tfLogin);
        panel.add(labelPass);
        panel.add(tfPass);
        panel.add(back);
        panel.add(enter);
        panel.add(register);
        add(panel);

    }

    private void action() {

        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResultSet rs = null;
                try {
                    rs = db.query("select * from users where login = '" + tfLogin.getText() + "' and delete_status=0 and block_status=0");
                    if (rs.next()) {
                        if (rs.getString("password").equals(tfPass.getText())) {
                            DaoUsers du = new DaoUsers(db);
                            if (rs.getString("role").equals("0")) {
                                new AdminFrame(db);
                                dispose();
                            } else {
                                new UserFrame(db, du.getUser(rs));
                                dispose();
                            }
                        } else {
                            JOptionPane.showMessageDialog(panel, "Пароль не верен", "Ошибка входа", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(panel, "Такого пользователя не существует, или заблокирован", "Ошибка входа", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (SQLException ex) {
                }
            }
        });

        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegisterFrame(db);
                dispose();
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StartFrame();
                dispose();
            }
        });
    }
}