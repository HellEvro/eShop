package user_interface;

import entity.Goods;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author teacher
 */
public class ListOfGoods extends JPanel {

    private JCheckBox jCheckBox1;
    private JLabel jLabel_Item_name;
    private JLabel jLabel_Item_price;
    private JScrollPane jScrollPane1;
    private JSpinner jSpinner1;
    private JTextArea jTextArea1;
    private Goods currentGood;

    public ListOfGoods(Goods good, int y) {
        this.currentGood = good;
        initComponents();
        setBounds(10, y, 570, 164);
        setBorder(BorderFactory.createLineBorder(Color.black));
        action();
        setVisible(true);
    }

    private void initComponents() {

        jCheckBox1 = new JCheckBox();
        SpinnerNumberModel model1 = new SpinnerNumberModel(1, 1, 10, 1);
        jSpinner1 = new JSpinner(model1);
//        ImageIcon image = new ImageIcon(System.getProperties().getProperty("user.dir").replaceAll("\\\\", "\\/") + "/images/" + this.currentGood.getImagePath());
//        jLabel1 = new JLabel();
//        jLabel1.setIcon(new ImageIcon(image.getImage().getScaledInstance(120, 120, image.getImage().SCALE_DEFAULT)));
        jLabel_Item_name = new JLabel();
        jLabel_Item_price = new JLabel();
        jScrollPane1 = new JScrollPane();
        jTextArea1 = new JTextArea();
        jSpinner1.setEnabled(false);
        jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        //jLabel1.setMaximumSize(new Dimension(120, 120));
        jLabel_Item_name.setText(this.currentGood.getItem_name());
        jLabel_Item_price.setText(String.valueOf(this.currentGood.getItem_price()) + "$");
        jTextArea1.setText(this.currentGood.getItem_description());
        jScrollPane1.setEnabled(false);
        jTextArea1.setColumns(20);
        jTextArea1.setEditable(false);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
            .addGap(39, 39, 39)
            .addComponent(jCheckBox1)
            .addGap(27, 27, 27)
            .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(42, 42, 42)
            //.addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE,120, javax.swing.GroupLayout.PREFERRED_SIZE)                        .addGap(29, 29, 29)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_Item_name)
            .addComponent(jLabel_Item_price)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE))
            .addContainerGap())
        );
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            //.addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE,120, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(30, 30, 30))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addGap(22, 22, 22)
            .addComponent(jLabel_Item_name)
            .addGap(18, 18, 18)
            .addComponent(jLabel_Item_price)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
            .addGap(0, 3, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jCheckBox1))
            .addGap(64, 64, 64))
            .addGroup(layout.createSequentialGroup()
            .addComponent(jScrollPane1)
            .addContainerGap())))
        );
    }

    public void action() {
        jCheckBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                jSpinner1.setEnabled(jSpinner1.isEnabled() ? false : true);
            }
        });
    }

    public boolean isChoosen() {
        if (jCheckBox1.isSelected()) {
            return true;
        }
        return false;
    }

    public int getAmount() {
        return (Integer) jSpinner1.getValue();
    }

    public Goods getCurrentItem() {
        return this.currentGood;
    }
}
