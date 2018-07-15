import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;

public class GUI extends JFrame {
    private JPanel panel;

    private JLabel label;

    private JButton button;


    public GUI(){




        panel = new JPanel();

        label = new JLabel("이미지를 보려면 아래 버튼을 누르세요.");


        button = new JButton();
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setContentAreaFilled(false);

        ImageIcon icon = new ImageIcon("C:\\Users\\stck6\\Documents\\DCMS\\USER\\src\\cancel.png");

        button.setIcon(icon);

        button.setSize(100, 100);

        button.addActionListener(this::actionPerformed);

        panel.add(label);

        panel.add(button);

        button.

        button.setPreferredSize(new Dimension(45, 28));


        this.add(panel);

        this.setVisible(true);

    }


    public void actionPerformed(ActionEvent e) {

        ImageIcon dog = new ImageIcon("dog2.png");

        label.setIcon(dog);

        label.setText(null);

    }



}