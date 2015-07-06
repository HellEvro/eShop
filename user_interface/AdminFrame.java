package user_interface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import mysql.DB;
import user_interface.table.TableGoodsPanel;
import user_interface.table.TableOrdersPanel;
import user_interface.table.TableUsersPanel;

public class AdminFrame extends JFrame {

    private JTabbedPane tp;
    private JScrollPane scroll;
    private JPanel panel;
    private JButton logout, startFrame;
    private DB db;

    AdminFrame(DB db) {
        this.db = db;
        setSize(690, 500); //размер фрейма
        setLocationRelativeTo(null); //центрирование
        setDefaultCloseOperation(EXIT_ON_CLOSE); //закрываем пограмму
        setTitle("AdmnFrame");//название окна
        initComponents();//инициализация компонентов фрейма своим методом
        action();//обработчик нажатий
        setVisible(true);//отрисовка во фрейм - виден (true)
        setResizable(false);//запрет на изменение размера окна
    }

    private void initComponents() {
        logout = new JButton("Выйти");
        logout.setBounds(555, 5, 100, 20);
        panel = new JPanel(null);
        tp = new JTabbedPane();
        tp.add("Users", new TableUsersPanel(db));
        tp.add("Goods", new TableGoodsPanel(db));
        tp.add("Orders", new TableOrdersPanel(db));
        tp.setBounds(10, 10, 663, 450);
        panel.add(logout);
        panel.add(tp);
        add(panel);
    }

    public void action() {
        logout.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginFrame(db);
                dispose();
            }
        });

    }

}
