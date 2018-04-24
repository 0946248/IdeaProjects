package cz.kamenitxan.sceneswitcher.demo.gui.scenes;

import cz.kamenitxan.sceneswitcher.SceneSwitcher;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import java.net.URL;
import java.util.ResourceBundle;

public class Opnemen implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resrouces) {
        System.out.println("Opnemen geladen");
    }


    public static void TerugNaarStart3() {
        SceneSwitcher.getInstance().loadScene("Start", "cs");
    }

    public static void EuroOpnemen5() {
        SceneSwitcher.getInstance().loadScene("Groeten", "cs");
    }

    public static void EuroOpnemen10() {
        SceneSwitcher.getInstance().loadScene("Groeten", "cs");
    }

    public static void EuroOpnemen25() {
        SceneSwitcher.getInstance().loadScene("Groeten", "cs");
    }

    public static void EuroOpnemen50() {
        SceneSwitcher.getInstance().loadScene("Groeten", "cs");
    }
}



