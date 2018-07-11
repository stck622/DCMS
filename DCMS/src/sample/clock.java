package sample;

import javafx.scene.text.Text;

public class clock extends Thread {

    Text text;

    public clock(Text text){
        this.text = text;
    }

    @Override
    public void run() {
        super.run();
        while(true){
            String time = new java.text.SimpleDateFormat("HH:mm:ss").format(new java.util.Date());
            if(Integer.valueOf(time.substring(0,2)) >= 12){
                text.setText("PM "+time);
            } else {
                text.setText("AM "+time);
            }

        }
    }
}
