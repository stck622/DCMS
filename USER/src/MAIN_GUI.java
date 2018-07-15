import javax.swing.*;
import java.awt.*;


public class MAIN_GUI extends JFrame {

    public MAIN_GUI() {


        /*전체 화면*/
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        //setUndecorated(true);
        //gd.setFullScreenWindow(this);


        /*컨테이너 기본 설정*/
        Container container = getContentPane();
        container.setLayout(null);
        setBounds(100, 100, 800, 480);
        setDefaultCloseOperation(3);
        setVisible(true);

        JLabel mask =  new JLabel();
        JLabel clock_text = new JLabel("PM 12:00");
        JLabel background_image = new JLabel((ImageIcon) Add_image("C:\\Users\\stck6\\Documents\\DCMS\\USER\\src\\Media\\background.jpg", 840, 480));
        JButton menu_cancel_button = new JButton(((ImageIcon) Add_image("C:\\Users\\stck6\\Documents\\DCMS\\USER\\src\\Media\\cacel.png", 30, 30)));
        JButton menu_open_button = new JButton(((ImageIcon) Add_image("C:\\Users\\stck6\\Documents\\DCMS\\USER\\src\\Media\\menu.png", 100, 100)));


        JButton menu_notice_button = new JButton(((ImageIcon) Add_image("C:\\Users\\stck6\\Documents\\DCMS\\USER\\src\\Media\\notice.png", 100, 100)));
        JButton menu_date_button = new JButton(((ImageIcon) Add_image("C:\\Users\\stck6\\Documents\\DCMS\\USER\\src\\Media\\date.png", 100, 100)));
        JButton menu_food_button = new JButton(((ImageIcon) Add_image("C:\\Users\\stck6\\Documents\\DCMS\\USER\\src\\Media\\food.png", 100, 100)));
        JButton menu_RP_button = new JButton(((ImageIcon) Add_image("C:\\Users\\stck6\\Documents\\DCMS\\USER\\src\\Media\\RP.png", 100, 100)));
        JButton menu_rule_button = new JButton(((ImageIcon) Add_image("C:\\Users\\stck6\\Documents\\DCMS\\USER\\src\\Media\\rule.png", 100, 100)));
        JButton menu_use_button = new JButton(((ImageIcon) Add_image("C:\\Users\\stck6\\Documents\\DCMS\\USER\\src\\Media\\shower.png", 100, 100)));


        /*백그라운드 이미지*/
        background_image.setLocation(0, 0);
        background_image.setSize(840, 480);


        /*백그라운드 마스크*/
        mask.setBackground(new Color(50,50,50,200));
        mask.setOpaque(true);
        mask.setVisible(false);
        mask.setLocation(0, 0);
        mask.setSize(840, 480);


        /*시간 텍스트*/
        clock_text.setLocation(840 / 2 - 105, 480 / 2 - 75);
        clock_text.setSize(1000, 100);
        clock_text.setForeground(Color.white);
        clock_text.setFont(clock_text.getFont().deriveFont(Font.BOLD, 30f));
        /*스레드 실행*/
        clock_thread clock_thread = new clock_thread(clock_text);
        clock_thread.start();


        /*메뉴 버튼*/
        menu_open_button.setLocation(840 - 200, 480 - 190);
        menu_open_button.setSize(100, 100);
        menu_open_button.setBorderPainted(false);
        menu_open_button.setContentAreaFilled(false);
        menu_open_button.setFocusPainted(false);
        menu_open_button.addActionListener(e -> {
            menu_open_button.setVisible(false);
            mask.setVisible(true);
            menu_cancel_button.setVisible(true);
            menu_notice_button.setVisible(true);
            menu_date_button.setVisible(true);
            menu_food_button.setVisible(true);
            menu_open_button.setVisible(true);
            menu_RP_button.setVisible(true);
            menu_rule_button.setVisible(true);
            menu_use_button.setVisible(true);

        });

        /*메뉴 닫기*/
        menu_cancel_button.setLocation(840 - 85, 480 - 70);
        menu_cancel_button.setSize(30, 30);
        menu_cancel_button.setVisible(false);
        menu_cancel_button.setBorderPainted(false);
        menu_cancel_button.setContentAreaFilled(false);
        menu_cancel_button.setFocusPainted(false);
        menu_cancel_button.addActionListener(e -> {
            menu_open_button.setVisible(true);
            mask.setVisible(false);
            menu_cancel_button.setVisible(false);
            menu_notice_button.setVisible(false);
            menu_date_button.setVisible(false);
            menu_food_button.setVisible(false);
            menu_open_button.setVisible(false);
            menu_RP_button.setVisible(false);
            menu_rule_button.setVisible(false);
            menu_use_button.setVisible(false);
        });

        menu_notice_button.setLocation(0, 0); //840 * 480
        menu_notice_button.setSize(840, 480);
        menu_notice_button.setVisible(false);
        menu_notice_button.setBorderPainted(false);
        menu_notice_button.setContentAreaFilled(false);
        menu_notice_button.setFocusPainted(false);

        menu_date_button.setLocation(0, 0);
        menu_date_button.setSize(840, 480);
        menu_date_button.setVisible(false);
        menu_date_button.setBorderPainted(false);
        menu_date_button.setContentAreaFilled(false);
        menu_date_button.setFocusPainted(false);

        menu_food_button.setLocation(0, 0);
        menu_food_button.setSize(840, 480);
        menu_food_button.setVisible(false);
        menu_food_button.setBorderPainted(false);
        menu_food_button.setContentAreaFilled(false);
        menu_food_button.setFocusPainted(false);

        menu_RP_button.setLocation(0, 0);
        menu_RP_button.setSize(840, 480);
        menu_RP_button.setVisible(false);
        menu_RP_button.setBorderPainted(false);
        menu_RP_button.setContentAreaFilled(false);
        menu_RP_button.setFocusPainted(false);

        menu_use_button.setLocation(0, 0);
        menu_use_button.setSize(840, 480);
        menu_use_button.setVisible(false);
        menu_use_button.setBorderPainted(false);
        menu_use_button.setContentAreaFilled(false);
        menu_use_button.setFocusPainted(false);

        menu_rule_button.setLocation(0, 0);
        menu_rule_button.setSize(840, 480);
        menu_rule_button.setVisible(false);
        menu_rule_button.setBorderPainted(false);
        menu_rule_button.setContentAreaFilled(false);
        menu_rule_button.setFocusPainted(false);



        /*컨테이너에 등록*/
        container.add(menu_cancel_button);
        container.add(menu_notice_button);
        container.add(menu_date_button);
        container.add(menu_food_button);
        container.add(menu_open_button);
        container.add(menu_RP_button);
        container.add(menu_use_button);


        container.add(mask);
        container.add(clock_text);
        container.add(menu_open_button);


        container.add(background_image);

    }


    public Object Add_image(String image, int width, int height) { //이미지 등록 메소드 (경로, 폭, 높이)
        ImageIcon imageIcon = new ImageIcon(image);
        Image originImg = imageIcon.getImage();
        Image changedImg = originImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(changedImg);
    }

    public void MenuOpen(Action e) {

    }


}


class clock_thread extends Thread {

    JLabel clock_text;

    clock_thread(JLabel clock_text) {
        this.clock_text = clock_text;
    }

    @Override
    public void run() {

        while (true) {

            String yy = new java.text.SimpleDateFormat("HH").format(new java.util.Date());
            String mm_ss = new java.text.SimpleDateFormat("mm:ss").format(new java.util.Date());

            if (Integer.parseInt(yy) > 12) {
                //12시 넘을경우

                yy = String.valueOf(Integer.parseInt(yy) - 12);

                clock_text.setText("PM " + yy + ":" + mm_ss);
                //PM으로

            } else {
                //12시 안일경우

                clock_text.setText("AM " + yy + ":" + mm_ss);
                //AM으로

            }

        }

    }

}
