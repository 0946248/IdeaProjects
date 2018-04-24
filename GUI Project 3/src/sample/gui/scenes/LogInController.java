package sample;

import cz.kamenitxan.sceneswitcher.SceneSwitcher;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class LogInController implements Initializable {
    DebugModus debug = new DebugModus();

    @FXML
    private Label RejectedPin;

    @FXML
    private Label PinInvoer;

    public void setText(String text) {
        Text = text;
    }
    public void setInvoer(String invoer){ Invoer = invoer;}

    private static String Text = "";
    private static String Invoer ="_ _ _ _ _ _";


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (debug.isDEBUG()) System.out.println("Log In Loaded");

        RejectedPin(Text);
        PinInvoer(Invoer);


    }

    @FXML
    public void RejectedPin(String s) {
        RejectedPin.setText(s);
    }

    @FXML
    public void PinInvoer(String s){PinInvoer.setText(s);}

    @FXML
    public void GeblokkeerdeKaart() {
        RejectedPin.setText("Kaart Geblokkeerd");
    }



}

