import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class DCMS_CLIENT extends Thread { // 포트 8000
   int getnum(byte data[],int num){
        return  Integer.parseInt(new String(data,num,1));
    }
    @Override

    public void run() {
        super.run();
        try {
            ServerSocket server1 = new ServerSocket(8000);

            while (true) {
                Socket socket1 = server1.accept();                          //서버 설정
                InputStream input1 = socket1.getInputStream();

                byte data[] = new byte[10];
                input1.read(data);                                 //값 입력받기
                 if (data[0] == '1') {
                    DATA.wm[getnum(data,1)-1][getnum(data,2)-1] = getnum(data,3);
                    System.out.println(DATA.wm[getnum(data,1)-1][getnum(data,2)-1]);
                }else{
                     DATA.sh[getnum(data,1)-1][getnum(data,2)-1] = getnum(data,3);
                     System.out.println(DATA.sh[getnum(data,1)-1][getnum(data,2)-1]);
                 }
            }
        } catch (IOException e) {
        }
    }
}
