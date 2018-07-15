import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

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
                } else if (data[0] == '3') {                                                                    //공지사항일경우
                byte trash[]=new byte[5];
                 for(int i=0;i<5;i++) {
                     output1.write(DATA.notice[i].getBytes());                                               //DATA클래스에있는 notice 전송
                     input1.read(trash);
                     }
                }
    }
} catch (IOException e) {
        }

    }
}
