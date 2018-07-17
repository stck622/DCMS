import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DCMS_USER extends Thread { // 포트 8001
    int getnum(byte data[], int num) {
        return Integer.parseInt(new String(data, num, 1));
    }

    @Override
    public void run() {
        super.run();
        try {
            ServerSocket server1 = new ServerSocket(8001);

            while (true) {
                Socket socket1 = server1.accept();
                InputStream input1 = socket1.getInputStream();
                OutputStream output1 = socket1.getOutputStream();
                byte data[] = new byte[100];

                input1.read(data);

                String data1 = new String(data);
                System.out.println(data1);
                if (data[0] == '1') {                                                                         //세탁기일경우
                    output1.write((String.valueOf(DATA.wm[getnum(data, 1) - 1][0]) + String.valueOf(DATA.wm[getnum(data, 1) - 1][1]) + String.valueOf(DATA.wm[getnum(data, 1) - 1][2]) + String.valueOf(DATA.wm[getnum(data, 1) - 1][3])).getBytes());
                } else if (data[0] == '2') {                                                                  //샤워실일경우
                    output1.write((String.valueOf(DATA.sh[getnum(data, 1) - 1][0]) + String.valueOf(DATA.sh[getnum(data, 1) - 1][1])).getBytes());
                } else if (data[0] == '3') {                                                                   //공지사항일경우
                    byte trash[] = new byte[3];
                    for (int i = 0; i < 3; i++) {
                        output1.write(DATA.notice[i].getBytes());                                               //DATA클래스에있는 notice 전송
                        input1.read(trash);
                    }
                } else if (data[0] == '4') {
                    Connection con = null;
                    Statement sta = null;
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");

                        con = DriverManager.getConnection("jdbc:mysql://10.80.162.66:3306/dcms_db?serverTimezone=UTC", "test2", "1234");
                        sta = con.createStatement();

                        PreparedStatement pstmt = con.prepareStatement("select * from topic where id = "+ new String(data).substring(1,5)+";");
                        ResultSet rs = pstmt.executeQuery();
                        while(rs.next()) {
                            System.out.print(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + "\n");
                            byte trash[] = new byte[3];
                            output1.write(rs.getString(1).getBytes());
                            input1.read(trash);
                            output1.write(rs.getString(2).getBytes());
                            input1.read(trash);
                            output1.write(rs.getString(4).getBytes());                                               //DATA클래스에있는 notice 전송
                            input1.read(trash);
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
