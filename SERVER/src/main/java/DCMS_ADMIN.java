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

    @Override

    public void run() {
        super.run();
        try {
            ServerSocket server1 = new ServerSocket(8002);


            while (true) {
                Socket socket1 = server1.accept();
                InputStream input1 = socket1.getInputStream();
                OutputStream output1 = socket1.getOutputStream();
                byte data[] = new byte[300];

                input1.read(data);

                String data1 = new String(data);
                System.out.println(data1);
                if (data[0] == '1') {                                   //공지사항
                    input1.read(data);
                    String notice = new String(data).trim();
                    switch (data[1]) {
                        case 1:
                            DATA.notice[1] = notice;
                            break;
                        case 2:
                            DATA.notice[2] = notice;
                            break;
                        case 3:
                            DATA.notice[3] = notice;
                            break;
                    }
                } else if (data[0] == '2') {                            //전체소등

                } else if (data[0] == '3') {                             //벌점부여
                    Connection con = null;
                    Statement sta = null;
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");

                        con = DriverManager.getConnection("jdbc:mysql://10.80.162.66:3306/dcms_db?serverTimezone=UTC", "test2", "1234");
                        sta = con.createStatement();

                        PreparedStatement pstmt = con.prepareStatement("select * from topic where id = " + new String(data).substring(1, 5) + ";");
                        ResultSet rs = pstmt.executeQuery();
                        while (rs.next()) {
                            int result = Integer.parseInt(rs.getString(4)) + getnum(data, 5);
                            StringBuilder sb = new StringBuilder();
                            String str1 = sb.append("update topic set Demerit = " + "'"+result +"'" + "where ID = " + new String(data).substring(1, 5) + ";").toString();
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

