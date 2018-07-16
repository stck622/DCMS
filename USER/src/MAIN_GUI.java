import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


public class MAIN_GUI extends JFrame {

    int i = 1;

    JLabel clock_text;
    JLabel mask;
    JLabel background_image;
    JButton menu_cancel_button;
    JButton menu_notice_button;
    JLabel notice_1;
    JLabel notice_2;
    JLabel notice_3;
    JButton menu_open_button;
    JButton menu_date_button;
    JButton menu_food_button;
    JLabel food_morning;
    JLabel food_lunch;
    JLabel food_dinner;
    JButton menu_RP_button;
    JButton menu_rule_button;
    JButton menu_use_button;
    JButton use_shower;
    JButton use_wm;
    JLabel wm_1;
    JLabel wm_2;
    JLabel wm_3;
    JLabel wm_4;
    JLabel menu_text;
    JLabel shower_image;
    JLabel up_button;
    JLabel down_button;


    public MAIN_GUI(String ip) {


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

        String path = "/home/pi/Desktop/New/";
        path = "C:\\Users\\user\\Documents\\DCMS\\USER\\src\\Media\\";
        mask = new JLabel();
        clock_text = new JLabel("PM 12:00");
        background_image = new JLabel((ImageIcon) Add_image(path + "background.jpg", 800, 480));
        menu_cancel_button = new JButton(((ImageIcon) Add_image(path + "cacel_button.png", 30, 30)));
        menu_open_button = new JButton(((ImageIcon) Add_image(path + "menu_button.png", 100, 100)));
        menu_notice_button = new JButton(((ImageIcon) Add_image(path + "notice_button.png", 180, 180)));
        notice_1 = new JLabel("null");
        notice_2 = new JLabel("null");
        notice_3 = new JLabel("null");
        menu_date_button = new JButton(((ImageIcon) Add_image(path + "date_button.png", 180, 180)));
        menu_food_button = new JButton(((ImageIcon) Add_image(path + "food_button.png", 180, 180)));
        food_morning = new JLabel("null");
        food_lunch = new JLabel("null");
        food_dinner = new JLabel("null");
        menu_RP_button = new JButton(((ImageIcon) Add_image(path + "RP_button.png", 180, 180)));
        menu_rule_button = new JButton(((ImageIcon) Add_image(path + "rule_button.png", 180, 180)));
        menu_use_button = new JButton(((ImageIcon) Add_image(path + "use_button.png", 180, 180)));
        use_shower = new JButton(((ImageIcon) Add_image(path + "use_shower.png", 180, 180)));
        shower_image = new JLabel(((ImageIcon) Add_image(path + "shower_image.png", 180, 180)));
        use_wm = new JButton(((ImageIcon) Add_image(path + "use_wm.png", 180, 180)));
        wm_1 = new JLabel("null");
        wm_2 = new JLabel("null");
        wm_3 = new JLabel("null");
        wm_4 = new JLabel("null");

        menu_text = new JLabel("null");
        up_button = new JLabel(((ImageIcon) Add_image(path + "up_button.png", 180, 180)));
        down_button = new JLabel(((ImageIcon) Add_image(path + "dowm_button.png", 180, 180)));

        /*백그라운드 이미지*/
        background_image.setLocation(0, 0);
        background_image.setSize(800, 480);


        /*백그라운드 마스크*/
        mask.setBackground(new Color(50, 50, 50, 200));
        mask.setOpaque(true);
        mask.setVisible(false);
        mask.setLocation(0, 0);
        mask.setSize(800, 480);


        /*시간 텍스트*/
        clock_text.setLocation(100, -30);
        clock_text.setSize(1000, 500);
        clock_text.setForeground(Color.white);
        clock_text.setFont(clock_text.getFont().deriveFont(Font.BOLD, 90f));
        /*스레드 실행*/
        clock_thread clock_thread = new clock_thread(clock_text);
        clock_thread.start();

        up_button.setLocation(100, 50);
        up_button.setSize(100, 100);
        up_button.setVisible(false);

        down_button.setLocation(100, 100);
        down_button.setSize(100, 100);
        down_button.setVisible(false);


        /*메뉴 버튼*/
        menu_open_button.setLocation(640, 330);
        menu_open_button.setSize(100, 100);
        menu_open_button.setBorderPainted(false);
        menu_open_button.setContentAreaFilled(false);
        menu_open_button.setFocusPainted(false);
        menu_open_button.addActionListener(e -> {
            menu_open_button.setVisible(false);
            mask.setVisible(true);
            menu_cancel_button.setVisible(true);
            Menu_visible();

        });

        /*메뉴 닫기*/
        menu_cancel_button.setLocation(769, 450);
        menu_cancel_button.setSize(30, 30);
        menu_cancel_button.setVisible(false);
        menu_cancel_button.setBorderPainted(false);
        menu_cancel_button.setContentAreaFilled(false);
        menu_cancel_button.setFocusPainted(false);
        menu_cancel_button.addActionListener(e -> {
            menu_open_button.setVisible(true);
            mask.setVisible(false);
            menu_cancel_button.setVisible(false);
            Menu_unvisible();
        });

        menu_text.setVisible(false);
        menu_text.setSize(300, 100);
        menu_text.setForeground(Color.white);
        menu_text.setFont(menu_text.getFont().deriveFont(Font.BOLD, 50f));

        /*공지사항 버튼*/
        menu_notice_button.setLocation(320, 15); //840 * 480
        menu_notice_button.setSize(180, 180);
        menu_notice_button.setVisible(false);
        menu_notice_button.setBorderPainted(false);
        menu_notice_button.setContentAreaFilled(false);
        menu_notice_button.setFocusPainted(false);
        menu_notice_button.addActionListener(e -> {

            Menu_unvisible();
            menu_text.setVisible(true);
            menu_text.setText("공지사항");
            notice_1.setVisible(true);
            notice_2.setVisible(true);
            notice_3.setVisible(true);

            new Thread() {
                @Override
                public void run() {
                    super.run();
                    try

                    {
                        Socket socket = new Socket(ip, 8001);

                        InputStream inputStream = socket.getInputStream();
                        OutputStream outputStream = socket.getOutputStream();

                        byte data[] = new byte[300];

                        outputStream.write("3".getBytes());

                        inputStream.read(data);

                        notice_1.setText(new String(data).trim());

                        outputStream.write("1".getBytes());

                        inputStream.read(data);

                        notice_2.setText(new String(data).trim());

                        outputStream.write("1".getBytes());

                        inputStream.read(data);

                        notice_3.setText(new String(data).trim());

                        outputStream.write("1".getBytes());

                        socket.close();

                    } catch (
                            IOException e1)

                    {
                        e1.printStackTrace();
                    }
                }
            }.start();
        });

        notice_1.setLocation(100, 15);
        notice_1.setSize(180, 180);
        notice_1.setVisible(false);

        notice_2.setLocation(200, 15);
        notice_2.setSize(180, 180);
        notice_2.setVisible(false);

        notice_3.setLocation(340, 15);
        notice_3.setSize(180, 180);
        notice_3.setVisible(false);



        /*학사일정 버튼*/
        menu_date_button.setLocation(320, 262);
        menu_date_button.setSize(180, 180);
        menu_date_button.setVisible(false);
        menu_date_button.setBorderPainted(false);
        menu_date_button.setContentAreaFilled(false);
        menu_date_button.setFocusPainted(false);

        /*오늘의 급식 버튼*/
        menu_food_button.setLocation(30, 273);
        menu_food_button.setSize(180, 180);
        menu_food_button.setVisible(false);
        menu_food_button.setBorderPainted(false);
        menu_food_button.setContentAreaFilled(false);
        menu_food_button.setFocusPainted(false);
        menu_food_button.addActionListener(e -> {
            Menu_unvisible();
            menu_text.setVisible(true);
            menu_text.setLocation(270, -10);
            menu_text.setText("오늘의 급식");
            food_morning.setVisible(true);
            food_lunch.setVisible(true);
            food_dinner.setVisible(true);
        });

        food_morning.setLocation(50, 0);
        food_morning.setSize(300, 500);
        food_morning.setForeground(Color.white);
        food_morning.setFont(food_morning.getFont().deriveFont(Font.BOLD, 15f));
        food_morning.setVisible(false);

        food_lunch.setLocation(200, 0);
        food_lunch.setSize(300, 500);
        food_lunch.setForeground(Color.white);
        food_lunch.setFont(food_lunch.getFont().deriveFont(Font.BOLD, 15f));
        food_lunch.setVisible(false);

        food_dinner.setLocation(400, 0);
        food_dinner.setSize(300, 500);
        food_dinner.setForeground(Color.white);
        food_dinner.setFont(food_dinner.getFont().deriveFont(Font.BOLD, 15f));
        food_dinner.setVisible(false);


        /*벌점 확인 버튼*/
        menu_RP_button.setLocation(600, 262);
        menu_RP_button.setSize(180, 180);
        menu_RP_button.setVisible(false);
        menu_RP_button.setBorderPainted(false);
        menu_RP_button.setContentAreaFilled(false);
        menu_RP_button.setFocusPainted(false);

        /*사용현황 버튼*/
        menu_use_button.setLocation(30, 17);
        menu_use_button.setSize(180, 180);
        menu_use_button.setVisible(false);
        menu_use_button.setBorderPainted(false);
        menu_use_button.setContentAreaFilled(false);
        menu_use_button.setFocusPainted(false);
        menu_use_button.addActionListener(e -> {
            Menu_unvisible();
            use_shower.setVisible(true);
            use_wm.setVisible(true);
        });

        use_wm.setLocation(30, 17);
        use_wm.setSize(180, 180);
        use_wm.setBorderPainted(false);
        use_wm.setContentAreaFilled(false);
        use_wm.setFocusPainted(false);
        use_wm.setVisible(false);
        use_wm.addActionListener(e -> {
            i = 0;
            Menu_unvisible();
            wm_1.setVisible(true);
            wm_2.setVisible(true);
            wm_3.setVisible(true);
            wm_4.setVisible(true);
            up_button.setVisible(true);
            down_button.setVisible(true);
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    try{
                        Socket socket = new Socket(ip,8001);
                        OutputStream outputStream = socket.getOutputStream();
                        InputStream inputStream = socket.getInputStream();
                        byte data[] = new byte[10];
                        outputStream.write("11".getBytes());
                        inputStream.read(data);
                        wm_1.setText(new String(data).substring(0,1));
                        wm_2.setText(new String(data).substring(1,2));
                        wm_3.setText(new String(data).substring(2,3));
                        wm_4.setText(new String(data).substring(3,4));
                        socket.close();
                    } catch (IOException e){}
                }
            }.start();
        });

        wm_1.setLocation(100, 15);
        wm_1.setSize(180, 180);
        wm_1.setVisible(false);

        wm_2.setLocation(130, 15);
        wm_2.setSize(180, 180);
        wm_2.setVisible(false);

        wm_3.setLocation(160, 15);
        wm_3.setSize(180, 180);
        wm_3.setVisible(false);

        wm_4.setLocation(190, 15);
        wm_4.setSize(180, 180);
        wm_4.setVisible(false);

        use_shower.setLocation(200, 17);
        use_shower.setSize(180, 180);
        use_shower.setBorderPainted(false);
        use_shower.setContentAreaFilled(false);
        use_shower.setFocusPainted(false);
        use_shower.setVisible(false);

        /*기숙사 규칙 버튼*/
        menu_rule_button.setLocation(600, 17);
        menu_rule_button.setSize(180, 180);
        menu_rule_button.setVisible(false);
        menu_rule_button.setBorderPainted(false);
        menu_rule_button.setContentAreaFilled(false);
        menu_rule_button.setFocusPainted(false);


        /*컨테이너에 등록*/
        container.add(menu_cancel_button);
        container.add(menu_notice_button);
        container.add(notice_1);
        container.add(notice_2);
        container.add(notice_3);
        container.add(menu_date_button);
        container.add(menu_food_button);
        container.add(food_morning);
        container.add(food_lunch);
        container.add(food_dinner);
        container.add(menu_open_button);
        container.add(menu_RP_button);
        container.add(menu_use_button);
        container.add(use_shower);
        container.add(use_wm);
        container.add(wm_1);
        container.add(wm_2);
        container.add(wm_3);
        container.add(wm_4);
        container.add(menu_rule_button);
        container.add(menu_text);


        container.add(down_button);
        container.add(up_button);
        container.add(mask);
        container.add(clock_text);
        container.add(menu_open_button);


        container.add(background_image);


    }

    public void Menu_visible() {
        menu_notice_button.setVisible(true);
        menu_date_button.setVisible(true);
        menu_food_button.setVisible(true);
        menu_RP_button.setVisible(true);
        menu_rule_button.setVisible(true);
        menu_use_button.setVisible(true);
    }

    public void Menu_unvisible() {
        up_button.setVisible(false);
        down_button.setVisible(false);
        menu_notice_button.setVisible(false);
        notice_1.setVisible(false);
        notice_2.setVisible(false);
        notice_3.setVisible(false);
        menu_date_button.setVisible(false);
        menu_food_button.setVisible(false);
        food_morning.setVisible(false);
        food_lunch.setVisible(false);
        food_dinner.setVisible(false);
        menu_text.setVisible(false);
        menu_RP_button.setVisible(false);
        menu_rule_button.setVisible(false);
        menu_use_button.setVisible(false);
        use_shower.setVisible(false);
        use_wm.setVisible(false);
        wm_1.setVisible(false);
        wm_2.setVisible(false);
        wm_3.setVisible(false);
        wm_4.setVisible(false);

    }

    public Object Add_image(String image, int width, int height) { //이미지 등록 메소드 (경로, 폭, 높이)
        ImageIcon imageIcon = new ImageIcon(image);
        Image originImg = imageIcon.getImage();
        Image changedImg = originImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(changedImg);
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
