import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class DCMS_ADMIN extends Thread { // 포트 8002
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
                byte data[] = new byte[10];

                input1.read(data);

                String data1 = new String(data);
                System.out.println(data1);
                if (data[0] == '1') {                                                                         //세탁기일경우
                    output1.write((String.valueOf(DATA.wm[getnum(data, 1) - 1][0]) + String.valueOf(DATA.wm[getnum(data, 1) - 1][1]) + String.valueOf(DATA.wm[getnum(data, 1) - 1][2]) + String.valueOf(DATA.wm[getnum(data, 1) - 1][3])).getBytes());
                } else if (data[0] == '2') {                                                                  //샤워실일경우
                    output1.write((String.valueOf(DATA.sh[getnum(data, 2) - 1][0]) + String.valueOf(DATA.sh[getnum(data, 2) - 1][1]) + String.valueOf(DATA.sh[getnum(data, 2) - 1][2]) + String.valueOf(DATA.sh[getnum(data, 2) - 1][3])).getBytes());
                } else if (data[0] == '3')                                                                    //공지사항일경우
                    output1.write(DATA.data.getBytes());                                                    //DATA클래스에있는 data 전송

            }
        } catch (IOException e) {
        }

    }
}

