import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.*;


public class MAIN_GUI extends JFrame {

    int i = 1;
    int menu_info = 0;

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
    JLabel morning;
    JLabel lunch;
    JLabel dinner;
    JLabel food_morning;
    JLabel food_lunch;
    JLabel food_dinner;
    JButton menu_RP_button;
    JLabel RP_number;
    JLabel RP_result;
    JButton RP_enter;
    JButton RP_del;
    JButton numpad[] = new JButton[10];
    JButton menu_rule_button;
    JLabel rule_txt;
    JScrollPane rule_scroll;
    JButton menu_use_button;
    JButton use_shower;
    JLabel shower_image1;
    JLabel shower_image2;
    JLabel shower_1;
    JLabel shower_2;
    JButton use_wm;
    JLabel wm_1;
    JLabel wm_2;
    JLabel wm_3;
    JLabel wm_4;
    JLabel wm_image1;
    JLabel wm_image2;
    JLabel wm_image3;
    JLabel wm_image4;
    JLabel menu_text;
    JButton up_button;
    JButton down_button;
    JLabel floor;

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
        path = "C:\\Users\\stck6\\Documents\\DCMS\\USER\\src\\Media\\";
        mask = new JLabel();
        clock_text = new JLabel("PM 12:00");
        background_image = new JLabel((ImageIcon) Add_image(path + "background.jpg", 800, 480));
        menu_cancel_button = new JButton(((ImageIcon) Add_image(path + "cacel_button.png", 30, 30)));
        menu_open_button = new JButton(((ImageIcon) Add_image(path + "menu_button.png", 100, 100)));
        menu_notice_button = new JButton(((ImageIcon) Add_image(path + "notice_button.png", 180, 180)));
        notice_1 = new JLabel("불러오는중...");
        notice_2 = new JLabel("불러오는중...");
        notice_3 = new JLabel("불러오는중...");
        menu_date_button = new JButton(((ImageIcon) Add_image(path + "date_button.png", 180, 180)));
        menu_food_button = new JButton(((ImageIcon) Add_image(path + "food_button.png", 180, 180)));
        morning = new JLabel("조식");
        lunch = new JLabel("중식");
        dinner = new JLabel("석식");
        food_morning = new JLabel("불러오는중...");
        food_lunch = new JLabel("불러오는중...");
        food_dinner = new JLabel("불러오는중...");
        menu_RP_button = new JButton(((ImageIcon) Add_image(path + "RP_button.png", 180, 180)));
        RP_number = new JLabel("");
        RP_del = new JButton("<--");
        RP_enter = new JButton("조회");
        RP_result = new JLabel("조회를 해주세요!");
        for(int i = 0; i < 10; i ++)
            numpad[i] = new JButton(String.valueOf(i));
        menu_rule_button = new JButton(((ImageIcon) Add_image(path + "rule_button.png", 180, 180)));
        menu_use_button = new JButton(((ImageIcon) Add_image(path + "use_button.png", 180, 180)));
        rule_txt = new JLabel();
        rule_scroll = new JScrollPane(rule_txt);
        use_shower = new JButton(((ImageIcon) Add_image(path + "use_shower.png", 300, 300)));
        shower_image1 = new JLabel(((ImageIcon) Add_image(path + "shower_image.png", 250, 250)));
        shower_image2 = new JLabel(((ImageIcon) Add_image(path + "shower_image.png", 250, 250)));
        shower_1 = new JLabel("null");
        shower_2 = new JLabel("null");
        use_wm = new JButton(((ImageIcon) Add_image(path + "use_wm.png", 300, 300)));
        wm_image1 = new JLabel(((ImageIcon) Add_image(path + "wm_image.png", 120, 120)));
        wm_image2 = new JLabel(((ImageIcon) Add_image(path + "wm_image.png", 120, 120)));
        wm_image3 = new JLabel(((ImageIcon) Add_image(path + "wm_image.png", 120, 120)));
        wm_image4 = new JLabel(((ImageIcon) Add_image(path + "wm_image.png", 120, 120)));
        wm_1 = new JLabel("null");
        wm_2 = new JLabel("null");

        wm_3 = new JLabel("null");
        wm_4 = new JLabel("null");

        floor = new JLabel("null");
        menu_text = new JLabel("null");
        up_button = new JButton(((ImageIcon) Add_image(path + "up_button.png", 180, 180)));
        down_button = new JButton(((ImageIcon) Add_image(path + "dowm_button.png", 180, 180)));

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

        up_button.setLocation(360, 110);
        up_button.setSize(100, 100);
        up_button.setVisible(false);
        up_button.setBorderPainted(false);
        up_button.setContentAreaFilled(false);
        up_button.setFocusPainted(false);
        up_button.addActionListener(e -> {
            if (i != 3) {
                i++;
                floor.setText(String.valueOf(i));
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        try {
                            Socket socket = new Socket(ip, 8001);
                            OutputStream outputStream = socket.getOutputStream();
                            InputStream inputStream = socket.getInputStream();
                            byte data[] = new byte[10];
                            outputStream.write((String.valueOf(menu_info) + (String.valueOf(i))).getBytes());
                            inputStream.read(data);
                            if (menu_info == 2) {
                                shower_1.setText(new String(data).substring(0, 1));
                                shower_2.setText(new String(data).substring(1, 2));
                            }

                            if (new String(data).substring(0, 1).equals("0") && menu_info == 1) {
                                wm_1.setText("사용안함");
                            } else if (new String(data).substring(0, 1).equals("1") && menu_info == 1) {
                                wm_1.setText("사용중");
                            }
                            if (new String(data).substring(1, 2).equals("0") && menu_info == 1) {
                                wm_2.setText("사용안함");
                            } else if (new String(data).substring(1, 2).equals("1") && menu_info == 1) {
                                wm_2.setText("사용중");
                            }
                            if (new String(data).substring(2, 3).equals("0") && menu_info == 1) {
                                wm_3.setText("사용안함");
                            } else if (new String(data).substring(2, 3).equals("1") && menu_info == 1) {
                                wm_3.setText("사용중");
                            }
                            if (new String(data).substring(3, 4).equals("0") && menu_info == 1) {
                                wm_4.setText("사용안함");
                            } else if (new String(data).substring(3, 4).equals("1") && menu_info == 1) {
                                wm_4.setText("사용중");
                            }
                            socket.close();
                        } catch (IOException e) {
                        }
                    }
                }.start();

            }
        });

        floor.setLocation(380, 0);
        floor.setSize(1000, 500);
        floor.setForeground(Color.white);
        floor.setFont(clock_text.getFont().deriveFont(Font.BOLD, 90f));
        floor.setVisible(false);


        down_button.setLocation(360, 300);
        down_button.setSize(100, 100);
        down_button.setVisible(false);
        down_button.setBorderPainted(false);
        down_button.setContentAreaFilled(false);
        down_button.setFocusPainted(false);
        down_button.addActionListener(e -> {
            if (i != 1) {
                i--;
                floor.setText(String.valueOf(i));
                new Thread() {
                    @Override
                    public void run() {

                        super.run();
                        try {
                            Socket socket = new Socket(ip, 8001);
                            OutputStream outputStream = socket.getOutputStream();
                            InputStream inputStream = socket.getInputStream();
                            byte data[] = new byte[10];
                            outputStream.write((String.valueOf(menu_info) + (String.valueOf(i))).getBytes());
                            inputStream.read(data);

                            if (menu_info == 2) {
                                shower_1.setText(new String(data).substring(0, 1) + "명 사용중");
                                shower_2.setText(new String(data).substring(1, 2) + "명 사용중");
                            }

                            if (new String(data).substring(0, 1).equals("0") && menu_info == 1) {
                                wm_1.setText("사용안함");
                            } else if (new String(data).substring(0, 1).equals("1") && menu_info == 1) {
                                wm_1.setText("사용중");
                            }
                            if (new String(data).substring(1, 2).equals("0") && menu_info == 1) {
                                wm_2.setText("사용안함");
                            } else if (new String(data).substring(1, 2).equals("1") && menu_info == 1) {
                                wm_2.setText("사용중");
                            }
                            if (new String(data).substring(2, 3).equals("0") && menu_info == 1) {
                                wm_3.setText("사용안함");
                            } else if (new String(data).substring(2, 3).equals("1") && menu_info == 1) {
                                wm_3.setText("사용중");
                            }
                            if (new String(data).substring(3, 4).equals("0") && menu_info == 1) {
                                wm_4.setText("사용안함");
                            } else if (new String(data).substring(3, 4).equals("1") && menu_info == 1) {
                                wm_4.setText("사용중");
                            }
                            socket.close();
                        } catch (IOException e) {
                        }
                    }
                }.start();

            }
        });




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
        menu_text.setSize(600, 100);
        menu_text.setForeground(Color.white);
        menu_text.setFont(menu_text.getFont().deriveFont(Font.BOLD, 50f));

        /*공지사항 버튼*/
        menu_notice_button.setLocation(320, 15);
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

            menu_info = 3;

            new Thread() {
                @Override
                public void run() {
                    super.run();
                    try

                    {
                        Socket socket = new Socket(ip, 8001);

                        InputStream inputStream = socket.getInputStream();
                        OutputStream outputStream = socket.getOutputStream();

                        byte data[][] = new byte[3][300];

                        outputStream.write(String.valueOf(menu_info).getBytes());

                        inputStream.read(data[0]);

                        notice_1.setText(new String(data[0]).trim());

                        outputStream.write("1".getBytes());

                        inputStream.read(data[1]);

                        notice_2.setText(new String(data[1]).trim());

                        outputStream.write("1".getBytes());

                        inputStream.read(data[2]);

                        notice_3.setText(new String(data[2]).trim());

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

        notice_1.setLocation(10, 45);
        notice_1.setSize(230, 300);
        notice_1.setVisible(false);
        notice_1.setForeground(Color.white);
        notice_1.setFont(notice_1.getFont().deriveFont(Font.BOLD, 30f));

        notice_2.setLocation(10, 155);
        notice_2.setSize(230, 300);
        notice_2.setVisible(false);
        notice_2.setForeground(Color.white);
        notice_2.setFont(notice_2.getFont().deriveFont(Font.BOLD, 30f));

        notice_3.setLocation(10, 265);
        notice_3.setSize(230, 300);
        notice_3.setVisible(false);
        notice_3.setForeground(Color.white);
        notice_3.setFont(notice_3.getFont().deriveFont(Font.BOLD, 30f));



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
            morning.setVisible(true);
            lunch.setVisible(true);
            dinner.setVisible(true);
            food_morning.setVisible(true);
            food_lunch.setVisible(true);
            food_dinner.setVisible(true);
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    try {
                        Document doc = Jsoup.connect("http://www.dgsw.hs.kr/user/carte/list.do?menuCd=").get();

                        Elements el = doc.select("div.meals_today_top");

                        String html = el.toString();

                        html = html.substring(html.indexOf("<ul class=\"meals_today_list\">"), html.length());

                        String morning = html.substring(html.indexOf("alt=\"조식\">") + 10, html.indexOf("</li>"));
                        html = html.substring(html.indexOf("</li>") + 5, html.length());

                        morning = morning.replaceAll("[.]", "");
                        morning = morning.replaceAll("[0-9]", "");
                        morning = morning.trim().replaceAll("  ", "<br>");

                        System.out.println(morning);
                        System.out.println();

                        String lunch = html.substring(html.indexOf("alt=\"중식\">") + 10, html.indexOf("</li>"));
                        html = html.substring(html.indexOf("</li>") + 5, html.length());

                        lunch = lunch.replaceAll("[.]", "");
                        lunch = lunch.replaceAll("[0-9]", "");
                        lunch = lunch.trim().replaceAll("  ", "<br>");

                        System.out.println(lunch);
                        System.out.println();

                        String dinner = html.substring(html.indexOf("alt=\"석식\">") + 10, html.indexOf("</li>"));
                        html = html.substring(html.indexOf("</li>") + 5, html.length());

                        dinner = dinner.replaceAll("[.]", "");
                        dinner = dinner.replaceAll("[0-9]", "");
                        dinner = dinner.replaceAll("  ", "<br>");
                        dinner = dinner.trim().replaceAll("ㆍ", "");

                        System.out.println(dinner);
                        System.out.println();

                        food_morning.setText("<html>" + morning + "</html>");
                        food_lunch.setText("<html>" + lunch + "</html>");
                        food_dinner.setText("<html>" + dinner + "</html>");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }.start();
        });
        morning.setLocation(0,60);
        morning.setSize(600,100);
        morning.setForeground(Color.white);
        morning.setFont(morning.getFont().deriveFont(Font.BOLD,30f));
        morning.setVisible(false);

        lunch.setLocation(250,60);
        lunch.setSize(600,100);
        lunch.setForeground(Color.white);
        lunch.setFont(morning.getFont().deriveFont(Font.BOLD,30f));
        lunch.setVisible(false);

        dinner.setLocation(500,60);
        dinner.setSize(600,100);
        dinner.setForeground(Color.white);
        dinner.setFont(morning.getFont().deriveFont(Font.BOLD,30f));
        dinner.setVisible(false);

        food_morning.setLocation(0, 0);
        food_morning.setSize(300, 500);
        food_morning.setForeground(Color.white);
        food_morning.setFont(food_morning.getFont().deriveFont(Font.BOLD, 25f));
        food_morning.setVisible(false);

        food_lunch.setLocation(250, 0);
        food_lunch.setSize(300, 500);
        food_lunch.setForeground(Color.white);
        food_lunch.setFont(food_lunch.getFont().deriveFont(Font.BOLD, 25f));
        food_lunch.setVisible(false);

        food_dinner.setLocation(500, 0);
        food_dinner.setSize(300, 500);
        food_dinner.setForeground(Color.white);
        food_dinner.setFont(food_dinner.getFont().deriveFont(Font.BOLD, 25f));
        food_dinner.setVisible(false);


        /*벌점 확인 버튼*/
        menu_RP_button.setLocation(600, 262);
        menu_RP_button.setSize(180, 180);
        menu_RP_button.setVisible(false);
        menu_RP_button.setBorderPainted(false);
        menu_RP_button.setContentAreaFilled(false);
        menu_RP_button.setFocusPainted(false);
        menu_RP_button.addActionListener(e -> {
            Menu_unvisible();
            for(int i = 0; i < 10; i ++)
                numpad[i].setVisible(true);
            RP_enter.setVisible(true);
            RP_del.setVisible(true);
            RP_result.setVisible(true);
            RP_number.setVisible(true);
        });

        RP_enter.setLocation(700,400);
        RP_enter.setSize(75,75);
        RP_enter.setVisible(false);
        RP_enter.addActionListener(e -> {
            menu_text.setText("벌점 조회");
            new Thread(){
                @Override
                public void run() {
                    Connection con = null;
                    Statement sta = null;
                    super.run();
                    try{
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        con= DriverManager.getConnection("jdbc:mysql://192.168.0.16:3306/dcms_db?serverTimezone=UTC","test2","1234");
                        sta = con.createStatement();
                        PreparedStatement pstmt = con.prepareStatement("select * from topic where id = '"+ RP_number.getText()+"';");
                        ResultSet rs = pstmt.executeQuery();
                        while(rs.next())
                            RP_result.setText(rs.getString(4)+"점입니다.");
                    }
                    catch(SQLException ex){
                        System.out.println("SQLException:"+ex);
                    }
                    catch(Exception ex){
                        System.out.println("Exception:"+ex);
                    }finally{
                        try{
                            //데이터베이스 Close 해주기
                            if ( con != null){ con.close(); }
                        }catch(Exception e){}
                    }
                }
            }.start();
        });

        RP_del.setLocation(625,400);
        RP_del.setSize(75,75);
        RP_del.setVisible(false);
        RP_del.addActionListener(e -> {
            RP_number.setText(RP_number.getText().substring(0,RP_number.getText().length()-1));
        });

        RP_number.setLocation(100, 150);
        RP_number.setSize(600, 100);
        RP_number.setVisible(false);
        RP_number.setForeground(Color.white);
        RP_number.setFont(RP_number.getFont().deriveFont(Font.BOLD, 50f));

        RP_result.setLocation(0,0);
        RP_result.setSize(600,100);
        RP_result.setVisible(false);
        RP_result.setForeground(Color.white);
        RP_result.setFont(RP_result.getFont().deriveFont(Font.BOLD, 50f));

        numpad[0].setLocation(550, 400);
        numpad[0].setSize(75, 75);
        numpad[0].addActionListener(e  -> {
            RP_number.setText(RP_number.getText()+"0");
        });

        numpad[1].setLocation(550, 325);
        numpad[1].setSize(75, 75);
        numpad[1].addActionListener(e -> {
            RP_number.setText(RP_number.getText()+"1");
        });

        numpad[2].setLocation(625, 325);
        numpad[2].setSize(75, 75);


        numpad[2].addActionListener(e -> {
            RP_number.setText(RP_number.getText()+"2");
        });

        numpad[3].setLocation(700, 325);
        numpad[3].setSize(75, 75);
        numpad[3].addActionListener(e -> {
            RP_number.setText(RP_number.getText()+"3");
        });

        numpad[4].setLocation(550, 250);
        numpad[4].setSize(75, 75);
        numpad[4].addActionListener(e -> {
            RP_number.setText(RP_number.getText()+"4");
        });

        numpad[5].setLocation(625, 250);
        numpad[5].setSize(75, 75);
        numpad[5].addActionListener(e -> {
            RP_number.setText(RP_number.getText()+"5");
        });

        numpad[6].setLocation(700, 250);
        numpad[6].setSize(75, 75);
        numpad[6].addActionListener(e -> {
            RP_number.setText(RP_number.getText()+"6");
        });

        numpad[7].setLocation(550, 175);
        numpad[7].setSize(75, 75);
        numpad[7].addActionListener(e -> {
            RP_number.setText(RP_number.getText()+"7");
        });

        numpad[8].setLocation(625, 175);
        numpad[8].setSize(75, 75);
        numpad[8].addActionListener(e -> {
            RP_number.setText(RP_number.getText()+"8");
        });

        numpad[9].setLocation(700, 175);
        numpad[9].setSize(75, 75);
        numpad[9].addActionListener(e -> {
            RP_number.setText(RP_number.getText()+"9");
        });

        for(int i = 0; i < 10; i++)
            numpad[i].setVisible(false);

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
            menu_text.setText("사용 여부");
        });

        use_wm.setLocation(80, 114);
        use_wm.setSize(300, 300);
        use_wm.setBorderPainted(false);
        use_wm.setContentAreaFilled(false);
        use_wm.setFocusPainted(false);
        use_wm.setVisible(false);
        use_wm.addActionListener(e -> {
            i = 1;
            menu_info = 1;
            Menu_unvisible();
            wm_1.setVisible(true);
            wm_2.setVisible(true);
            wm_3.setVisible(true);
            wm_4.setVisible(true);
            wm_image1.setVisible(true);
            wm_image2.setVisible(true);
            wm_image3.setVisible(true);
            wm_image4.setVisible(true);
            up_button.setVisible(true);
            down_button.setVisible(true);
            floor.setVisible(true);
            floor.setText(String.valueOf(i));
            menu_text.setVisible(true);
            menu_text.setText("세탁기 사용 현황");
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    try {
                        Socket socket = new Socket(ip, 8001);
                        OutputStream outputStream = socket.getOutputStream();
                        InputStream inputStream = socket.getInputStream();
                        byte data[] = new byte[10];
                        outputStream.write((String.valueOf(menu_info) + (String.valueOf(i))).getBytes());
                        inputStream.read(data);
                        if (menu_info == 2) {
                            shower_1.setText(new String(data).substring(0, 1) + "명 사용중");
                            shower_2.setText(new String(data).substring(1, 2) + "명 사용중");
                        }

                        if (new String(data).substring(0, 1).equals("0") && menu_info == 1) {
                            wm_1.setText("사용안함");
                        } else if (new String(data).substring(0, 1).equals("1") && menu_info == 1) {
                            wm_1.setText("사용중");
                        }
                        if (new String(data).substring(1, 2).equals("0") && menu_info == 1) {
                            wm_2.setText("사용안함");
                        } else if (new String(data).substring(1, 2).equals("1") && menu_info == 1) {
                            wm_2.setText("사용중");
                        }
                        if (new String(data).substring(2, 3).equals("0") && menu_info == 1) {
                            wm_3.setText("사용안함");
                        } else if (new String(data).substring(2, 3).equals("1") && menu_info == 1) {
                            wm_3.setText("사용중");
                        }
                        if (new String(data).substring(3, 4).equals("0") && menu_info == 1) {
                            wm_4.setText("사용안함");
                        } else if (new String(data).substring(3, 4).equals("1") && menu_info == 1) {
                            wm_4.setText("사용중");
                        }
                        socket.close();
                    } catch (IOException e) {
                    }
                }
            }.start();
        });

        wm_1.setLocation(80, 60);
        wm_1.setSize(600, 100);
        wm_1.setVisible(false);
        wm_1.setForeground(Color.white);
        wm_1.setFont(wm_1.getFont().deriveFont(Font.BOLD, 50f));

        wm_2.setLocation(530, 60);
        wm_2.setSize(600, 100);
        wm_2.setVisible(false);
        wm_2.setForeground(Color.white);
        wm_2.setFont(wm_2.getFont().deriveFont(Font.BOLD, 50f));

        wm_3.setLocation(80, 400);
        wm_3.setSize(600, 100);
        wm_3.setVisible(false);
        wm_3.setForeground(Color.white);
        wm_3.setFont(wm_3.getFont().deriveFont(Font.BOLD, 50f));

        wm_4.setLocation(530, 400);
        wm_4.setSize(600, 100);
        wm_4.setVisible(false);
        wm_4.setForeground(Color.white);
        wm_4.setFont(wm_4.getFont().deriveFont(Font.BOLD, 50f));

        wm_image1.setLocation(120, 140);
        wm_image1.setSize(120, 120);
        wm_image1.setVisible(false);

        wm_image2.setLocation(570, 140);
        wm_image2.setSize(120, 120);
        wm_image2.setVisible(false);

        wm_image3.setLocation(120, 300);
        wm_image3.setSize(120, 120);
        wm_image3.setVisible(false);

        wm_image4.setLocation(570, 300);
        wm_image4.setSize(120, 120);
        wm_image4.setVisible(false);

        use_shower.setLocation(400, 120);
        use_shower.setSize(300, 300);
        use_shower.setBorderPainted(false);
        use_shower.setContentAreaFilled(false);
        use_shower.setFocusPainted(false);
        use_shower.setVisible(false);
        use_shower.addActionListener(e -> {
            Menu_unvisible();
            shower_image1.setVisible(true);
            shower_image2.setVisible(true);
            menu_text.setVisible(true);
            menu_text.setText("샤워실 사용 현황");
            up_button.setVisible(true);
            down_button.setVisible(true);
            floor.setVisible(true);
            floor.setText(String.valueOf(i));
            shower_1.setVisible(true);
            shower_2.setVisible(true);
            i = 1;
            menu_info = 2;
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    try {
                        Socket socket = new Socket(ip, 8001);
                        OutputStream outputStream = socket.getOutputStream();
                        InputStream inputStream = socket.getInputStream();
                        byte data[] = new byte[10];
                        outputStream.write((String.valueOf(menu_info) + (String.valueOf(i))).getBytes());
                        inputStream.read(data);
                        if (menu_info == 2) {
                            shower_1.setText(new String(data).substring(0, 1) + "명 사용중");
                            shower_2.setText(new String(data).substring(1, 2) + "명 사용중");
                        }

                        if (new String(data).substring(0, 1).equals("0") && menu_info == 1) {
                            wm_1.setText("사용안함");
                        } else if (new String(data).substring(0, 1).equals("1") && menu_info == 1) {
                            wm_1.setText("사용중");
                        }
                        if (new String(data).substring(1, 2).equals("0") && menu_info == 1) {
                            wm_2.setText("사용안함");
                        } else if (new String(data).substring(1, 2).equals("1") && menu_info == 1) {
                            wm_2.setText("사용중");
                        }
                        if (new String(data).substring(2, 3).equals("0") && menu_info == 1) {
                            wm_3.setText("사용안함");
                        } else if (new String(data).substring(2, 3).equals("1") && menu_info == 1) {
                            wm_3.setText("사용중");
                        }
                        if (new String(data).substring(3, 4).equals("0") && menu_info == 1) {
                            wm_4.setText("사용안함");
                        } else if (new String(data).substring(3, 4).equals("1") && menu_info == 1) {
                            wm_4.setText("사용중");
                        }
                        socket.close();
                    } catch (IOException e) {
                    }
                }
            }.start();


        });

        shower_1.setLocation(110, 330);
        shower_1.setSize(600, 100);
        shower_1.setVisible(false);
        shower_1.setForeground(Color.white);
        shower_1.setFont(shower_1.getFont().deriveFont(Font.BOLD, 50f));

        shower_2.setLocation(500, 330);
        shower_2.setSize(600, 100);
        shower_2.setVisible(false);
        shower_2.setForeground(Color.white);
        shower_2.setFont(shower_2.getFont().deriveFont(Font.BOLD, 50f));

        shower_image1.setLocation(30, 100);
        shower_image1.setSize(250, 250);
        shower_image1.setVisible(false);

        shower_image2.setLocation(500, 100);
        shower_image2.setSize(250, 250);
        shower_image2.setVisible(false);

        /*기숙사 규칙 버튼*/
        menu_rule_button.setLocation(600, 17);
        menu_rule_button.setSize(180, 180);
        menu_rule_button.setVisible(false);
        menu_rule_button.setBorderPainted(false);
        menu_rule_button.setContentAreaFilled(false);
        menu_rule_button.setFocusPainted(false);
        menu_rule_button.addActionListener(e -> {
            Menu_unvisible();
            rule_scroll.setVisible(true);
            menu_text.setVisible(true);
            menu_text.setText("기숙사 규정");
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    try {
                        Document doc = Jsoup.connect("http://xn--ok0b85yy2iq1a.xn--h32bi4v.xn--3e0b707e/").get();
                        rule_txt.setText(doc.getAllElements().toString());
                    } catch (IOException e1) {
                    }
                }
            }.start();
        });

        rule_scroll.setLocation(0, 0);
        rule_scroll.setSize(800, 480);
        rule_scroll.setVisible(false);
        rule_scroll.setOpaque(true);
        rule_txt.setOpaque(true);


        /*컨테이너에 등록*/
        container.add(menu_cancel_button);
        container.add(menu_notice_button);
        container.add(notice_1);
        container.add(notice_2);
        container.add(notice_3);
        container.add(menu_date_button);
        container.add(menu_food_button);
        container.add(morning);
        container.add(lunch);
        container.add(dinner);
        container.add(food_morning);
        container.add(food_lunch);
        container.add(food_dinner);
        container.add(menu_open_button);
        container.add(menu_RP_button);
        container.add(RP_number);
        container.add(RP_result);
        container.add(RP_enter);
        container.add(RP_del);
        for(int i = 0; i < 10; i ++)
            container.add(numpad[i]);
        container.add(menu_use_button);
        container.add(use_shower);
        container.add(shower_image1);
        container.add(shower_image2);
        container.add(shower_1);
        container.add(shower_2);
        container.add(use_wm);
        container.add(wm_1);
        container.add(wm_2);
        container.add(wm_3);
        container.add(wm_4);
        container.add(wm_image1);
        container.add(wm_image2);
        container.add(wm_image3);
        container.add(wm_image4);
        container.add(menu_rule_button);
        container.add(rule_scroll);
        container.add(menu_text);


        container.add(floor);
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
        floor.setVisible(false);
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
        RP_result.setVisible(false);
        RP_del.setVisible(false);
        RP_enter.setVisible(false);
        RP_number.setVisible(false);
        for (int i = 0; i < 10; i ++)
            numpad[i].setVisible(false);
        menu_rule_button.setVisible(false);
        rule_scroll.setVisible(false);
        menu_use_button.setVisible(false);
        use_shower.setVisible(false);
        shower_image1.setVisible(false);
        shower_image2.setVisible(false);
        shower_1.setVisible(false);
        shower_2.setVisible(false);
        use_wm.setVisible(false);
        wm_1.setVisible(false);
        wm_2.setVisible(false);
        wm_3.setVisible(false);
        wm_4.setVisible(false);
        wm_image1.setVisible(false);
        wm_image2.setVisible(false);
        wm_image3.setVisible(false);
        wm_image4.setVisible(false);
        morning.setVisible(false);
        lunch.setVisible(false);
        dinner.setVisible(false);
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
