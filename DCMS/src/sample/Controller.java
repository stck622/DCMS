package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Text clock_text;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        clock clock = new clock(clock_text);
        clock.start();



    }
}
