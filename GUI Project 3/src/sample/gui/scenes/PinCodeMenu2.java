package sample;

import cz.kamenitxan.sceneswitcher.SceneSwitcher;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class PinCodeMenu2 implements Initializable {
    DebugModus debug = new DebugModus();
    @FXML
    private Label OnScreenPin;

    public static void setInvoer(String invoer) {
        Invoer = invoer;
    }

    private static String Invoer="_ _ _ _ _ _ ";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (debug.isDEBUG()) System.out.println("Pin Invoer Menu Loaded");
        OnScreenPin(Invoer);

    }

    public void TerugNaarGebruikersMenu() {
        SceneSwitcher.getInstance().loadScene("GebruikersMenu", "cs");
    }

    @FXML
    public void OnScreenPin(String s) {
        OnScreenPin.setText(s);
    }
}
