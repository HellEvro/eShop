package user_interface;

import entity.Users;
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

public class RegisterFrame extends JFrame {

    private JPanel panel;
    private JLabel labelLogin, labelPass;
    private JTextField tfLogin, tfPass;
    private JButton enter, register;
    private DB db;

    public RegisterFrame(DB db) {
        this.db = db;
        setSize(260, 160); //размер фрейма
        setLocationRelativeTo(null); //центрирование
        setDefaultCloseOperation(EXIT_ON_CLOSE); //закрываем пограмму
        setTitle("RegisterFrame");//название окна
        initComponents();//инициализация компонентов фрейма своим методом
        action();//обработчик нажатий
        setVisible(true);//отрисовка во фрейм - виден (true)
        setResizable(false);//запрет на изменение размера окна
    }

    private void initComponents() {
        panel = new JPanel();

        labelLogin = new JLabel("New Login");
        labelPass = new JLabel("New Password");

        tfLogin = new JTextField(20);
        tfPass = new JPasswordField(20);
        register = new JButton("Register New");
        enter = new JButton("Back to Login");

        panel.add(labelLogin);
        panel.add(tfLogin);
        panel.add(labelPass);
        panel.add(tfPass);
        panel.add(register);
        panel.add(enter);

        add(panel);
    }

    private void action() {

        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResultSet rs = null;
                try {
                    rs = db.query("select * from users where login = '" + tfLogin.getText() + "'");
                    if (!rs.next()) {
                        DaoUsers du = new DaoUsers(db);
                        du.insert(new Users(tfLogin.getText(), tfPass.getText(), 1, 0));
                        JOptionPane.showMessageDialog(panel, "Пользователь <" + tfLogin.getText().toUpperCase() + "> успешно зарегистрирован", "Успешная регистрация", JOptionPane.INFORMATION_MESSAGE);
                        new LoginFrame(db);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(panel, "Такой пользователь уже существует", "Ошибка регистрации", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                }
            }
        });

        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginFrame(db);
                dispose();
            }
        });
    }
}
