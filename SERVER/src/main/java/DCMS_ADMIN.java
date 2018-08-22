import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;

public class DCMS_ADMIN extends Thread { // 포트 8002
    int getnum(byte data[], int num) {
        return Integer.parseInt(new String(data, num, 1));
    }

    int i = 0;
    byte data[] = new byte[300];

    @Override

    public void run() {
        super.run();
        try {
            ServerSocket server1 = new ServerSocket(8002);


            while (true) {
                Socket socket1 = server1.accept();
                InputStream input1 = socket1.getInputStream();
                OutputStream output1 = socket1.getOutputStream();

                input1.read(data);

                String data1 = new String(data);
                System.out.println(data1);
                if (data[0] == '1') {                                   //공지사항
                    byte data2[][] = new byte[3][300];
                    output1.write("1".getBytes());
                    input1.read(data2[0]);
                    DATA.notice[0] = new String(data2[0]).trim();
                    output1.write("1".getBytes());
                    input1.read(data2[1]);
                    DATA.notice[1] = new String(data2[1]).trim();
                    output1.write("1".getBytes());
                    input1.read(data2[2]);
                    DATA.notice[2] = new String(data2[2]).trim();
                    output1.write("1".getBytes());

                } else if (data[0] == '2') {                          //전체소등
                    socket1.close();
                    System.out.println(new String(data).trim());
                    for (i = 0; i < DATA.ip.length; i++) {
                        new Thread() {
                            @Override
                            public void run() {
                                super.run();
                                try {
                                    Socket socket = new Socket(DATA.ip[i - 1], 8010);
                                    OutputStream outputStream = socket.getOutputStream();
                                    outputStream.write(new String(data).substring(1, 2).trim().getBytes());
                                    socket.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }.start();
                    }

                } else if (data[0] == '3') {                             //벌점부여
                    Connection con = null;
                    Statement sta = null;
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");

                        con = DriverManager.getConnection("jdbc:mysql://192.168.137.47:3306/dcms_db?serverTimezone=UTC", "test2", "1234");
                        sta = con.createStatement();

                        PreparedStatement pstmt = con.prepareStatement("select * from topic where id = " + new String(data).substring(1, 5) + ";");
                        ResultSet rs = pstmt.executeQuery();
                        while (rs.next()) {
                            int result = Integer.parseInt(rs.getString(4)) + getnum(data, 5);
                            StringBuilder sb = new StringBuilder();
                            String str1 = sb.append("update topic set Demerit = " + "'" + result + "'" + "where ID = " + new String(data).substring(1, 5) + ";").toString();
                            sta.executeUpdate(str1);
                        }
                    } catch (SQLException ex) {
                        System.out.println("SQLException:" + ex);
                    } catch (Exception ex) {
                        System.out.println("Exception:" + ex);
                    } finally {
                        try {
                            if (con != null) {
                                con.close();
                            }
                        } catch (Exception e) {
                        }
                    }
                }
            }
        } catch (IOException e) {
        }

    }
}

