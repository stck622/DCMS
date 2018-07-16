import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class MAIN_GUI extends JFrame {
    JLabel clock_text;
    JLabel mask;
    JLabel background_image;
    JButton menu_cancel_button;
    JButton menu_notice_button;
    JButton menu_open_button;
    JButton menu_date_button;
    JButton menu_food_button;
    JLabel food_morning;
    JLabel food_lunch;
    JLabel food_dinner;
    JButton menu_RP_button;
    JButton menu_rule_button;
    JButton menu_use_button;
    JLabel menu_text;

    public MAIN_GUI() {


        /*전체 화면*/
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        //setUndecorated(true);
        gd.setFullScreenWindow(this);


        /*컨테이너 기본 설정*/
        Container container = getContentPane();
        container.setLayout(null);
        setBounds(100, 100, 800, 480);
        setDefaultCloseOperation(3);
        setVisible(true);

        String path = "/home/pi/Desktop/New/";
        //path = "C:\\Users\\stck6\\Documents\\DCMS\\USER\\src\\Media\\";
        mask = new JLabel();
        clock_text = new JLabel("PM 12:00");
        background_image = new JLabel((ImageIcon) Add_image(path+"background.jpg", 800, 480));
        menu_cancel_button = new JButton(((ImageIcon) Add_image(path +"cacel.png", 30, 30)));
        menu_open_button = new JButton(((ImageIcon) Add_image(path +"menu.png", 100, 100)));
        menu_notice_button = new JButton(((ImageIcon) Add_image(path +"notice.png", 180, 180)));
        menu_date_button = new JButton(((ImageIcon) Add_image(path +"date.png", 180, 180)));
        menu_food_button = new JButton(((ImageIcon) Add_image(path +"food.png", 180, 180)));
        menu_RP_button = new JButton(((ImageIcon) Add_image(path +"RP.png", 180, 180)));
        menu_rule_button = new JButton(((ImageIcon) Add_image(path +"rule.png", 180, 180)));
        menu_use_button = new JButton(((ImageIcon) Add_image(path +"shower.png", 180, 180)));
        food_morning = new JLabel("null");
        food_lunch = new JLabel("null");
        food_dinner = new JLabel("null");
        menu_text = new JLabel("null");


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
        menu_text.setSize(300,100);
        menu_text.setForeground(Color.white);
        menu_text.setFont(menu_text.getFont().deriveFont(Font.BOLD,50f));

        /*공지사항 버튼*/
        menu_notice_button.setLocation(320, 15); //840 * 480
        menu_notice_button.setSize(180, 180);
        menu_notice_button.setVisible(false);
        menu_notice_button.setBorderPainted(false);
        menu_notice_button.setContentAreaFilled(false);
        menu_notice_button.setFocusPainted(false);

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
            try {
                menu_text.setVisible(true);
                menu_text.setLocation(270,-10);
                menu_text.setText("오늘의 급식");
                food_morning.setVisible(true);
                food_morning.setText("<html>"+get_Food(1).replaceAll("[.]","").replaceAll("[0-9]","").trim().replaceAll("\\n","<br/>")+"<html/>");
                food_lunch.setVisible(true);
                food_lunch.setText("<html>"+get_Food(2).replaceAll("[.]","").replaceAll("[0-9]","").trim().replaceAll("\\n","<br/>")+"<html/>");
                food_dinner.setVisible(true);
                food_dinner.setText("<html>"+get_Food(3).replaceAll("[.]","").replaceAll("[0-9]","").trim().replaceAll("\\n","<br/>")+"<html/>");
            } catch (IOException e1){e1.printStackTrace();}
        });

        food_morning.setLocation(50,0);
        food_morning.setSize(300, 500);
        food_morning.setForeground(Color.white);
        food_morning.setFont(food_morning.getFont().deriveFont(Font.BOLD, 15f));
        food_morning.setVisible(false);

        food_lunch.setLocation(200,0);
        food_lunch.setSize(300,500);
        food_lunch.setForeground(Color.white);
        food_lunch.setFont(food_lunch.getFont().deriveFont(Font.BOLD, 15f));
        food_lunch.setVisible(false);

        food_dinner.setLocation(400,0);
        food_dinner.setSize(300,500);
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
        container.add(menu_date_button);
        container.add(menu_food_button);
        container.add(menu_open_button);
        container.add(menu_RP_button);
        container.add(menu_use_button);
        container.add(menu_rule_button);
        container.add(food_morning);
        container.add(food_lunch);
        container.add(food_dinner);
        container.add(menu_text);

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
        menu_notice_button.setVisible(false);
        menu_date_button.setVisible(false);
        menu_food_button.setVisible(false);
        menu_RP_button.setVisible(false);
        menu_rule_button.setVisible(false);
        menu_use_button.setVisible(false);
        food_morning.setVisible(false);
        food_lunch.setVisible(false);
        food_dinner.setVisible(false);
        menu_text.setVisible(false);
    }


    public Object Add_image(String image, int width, int height) { //이미지 등록 메소드 (경로, 폭, 높이)
        ImageIcon imageIcon = new ImageIcon(image);
        Image originImg = imageIcon.getImage();
        Image changedImg = originImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(changedImg);
    }

    public String get_Food(int i) throws IOException {
        Document doc;

        doc = Jsoup.connect("http://www.dgsw.hs.kr/user/carte/list.do?menuCd=").get();

        Elements el = doc.select("div.meals_today_top");

        String html = el.toString();

        String html_morning = html.substring(html.indexOf("alt=\"조식\"") + 10, html.indexOf("</li>")) + " end";

        String morning = "";

        try {

            while (true) {

                int num = html_morning.indexOf(" ");

                morning += "\n" + html_morning.substring(0, num);

                html_morning = html_morning.substring(num + 1, html_morning.length());

            }

        } catch (StringIndexOutOfBoundsException e) {
        }

        html = html.substring(html.indexOf("</li>") + 7, html.length());
        String html_lunch = html.substring(html.indexOf("alt=\"중식\"") + 10, html.indexOf("</li>")) + "end";

        String lunch = "";

        try {

            while (true) {

                int num = html_lunch.indexOf(" ");

                lunch += "\n" + html_lunch.substring(0, num);

                html_lunch = html_lunch.substring(num + 1, html_lunch.length());

            }

        } catch (StringIndexOutOfBoundsException e) {
        }


        html = html.substring(html.indexOf("</li>") + 7, html.length());
        String html_dinner = html.substring(html.indexOf("alt=\"석식\"") + 10, html.indexOf("</li>")) + "end";

        String dinner = "";

        try {

            while (true) {

                int num = html_dinner.indexOf(" ");

                dinner += "\n" + html_dinner.substring(0, num);

                html_dinner = html_dinner.substring(num + 1, html_dinner.length());

            }

        } catch (StringIndexOutOfBoundsException e) {
        }

        switch (i) {
            case 1:
                return morning.trim();
            case 2:
                return lunch;
            case 3:
                return dinner;
        }

        return null;
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
