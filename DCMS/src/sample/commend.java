package sample;

import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class commend extends Thread {
    Stage pr;

    public commend(Stage pr){
        this.pr = pr;
    }

    @Override
    public void run() {
        super.run();
        pr.setFullScreen(true);
    }
}
