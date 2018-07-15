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
                            input1.read();
                            DATA.notice[2] = notice;
                            break;
                        case 3:
                            input1.read();
                            DATA.notice[3] = notice;
                            break;
                        case 4:
                            input1.read();
                            DATA.notice[4] = notice;
                            break;
                        case 5:
                            input1.read();
                            DATA.notice[5] = notice;
                            break;
                    }
                } else if (data[0] == '2') {                            //전체소등

                }
            }
        } catch (IOException e) {
        }

    }
}

